package com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantastika.PlayerSelection.Domain.GetPlayersUseCase
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.LoadTeamUseCase
import com.example.fantastika.PlayerSelection.Domain.TeamSelection.SaveTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference

sealed interface SideBarUiEvent {
    data class Success(val message: String) : SideBarUiEvent
    data class Error(val message: String) : SideBarUiEvent
    data class Message(val message: String) : SideBarUiEvent
}
@HiltViewModel
class SideBarViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase,
    private val saveTeamUseCase: SaveTeamUseCase,
    private val loadTeamUseCase: LoadTeamUseCase
) : ViewModel() {

    companion object {
        private const val INITIAL_BUDGET = 150.0
        private const val NUM_ROSTER_SLOTS = 5
        private const val USER_TEAM_NAME = "MyFantasyTeam"
    }

    private val _playersState = MutableStateFlow(PlayersState())
    val playersState = _playersState.asStateFlow()

    private val _droppedZones = MutableStateFlow(
        List<String?>(NUM_ROSTER_SLOTS) { null }
    )
    val droppedZones: StateFlow<List<String?>> = _droppedZones.asStateFlow()


    val usedItems: StateFlow<List<String>> = droppedZones.map { zones ->
        zones.filterNotNull().distinct()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _remainingBudget = MutableStateFlow(INITIAL_BUDGET)
    val remainingBudget: StateFlow<Double> = _remainingBudget.asStateFlow()

    private val _uiEvent = Channel<SideBarUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val isTeamComplete: StateFlow<Boolean> = droppedZones.map { zones ->
        zones.filterNotNull().size == NUM_ROSTER_SLOTS
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val userIdRef = AtomicReference<String?>(null)
    fun setUserId(userId: String?) {
        if (userIdRef.get() == null && userId != null) {
            userIdRef.set(userId)
            loadUserTeam()
        }
    }

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            _playersState.update { it.copy(isLoading = true, error = null) } // Reset error
            try {
                _playersState.update { it.copy(
                    players = getPlayersUseCase.execute(),
                    isLoading = false,
                    error = null
                ) }
            } catch (e: Exception) {
                Log.e("PlayerVM", "Error fetching players: ${e.message}", e)
                _playersState.update { it.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Unknown error fetching players" // Update error state
                ) }
            }
        }
    }

    private fun getPlayerByName(playerName: String): SimplePlayer? {
        return _playersState.value.players.firstOrNull { it.name == playerName }
    }

    private fun getPlayerPrice(playerName: String): Double {
        return getPlayerByName(playerName)?.price ?: 0.0
    }

    fun onItemDropped(zoneIndex: Int, newItemName: String): Boolean {
        val newItemPrice = getPlayerPrice(newItemName)
        val currentRoster = _droppedZones.value
        val oldItemName = currentRoster[zoneIndex]

        if (oldItemName == null && currentRoster.contains(newItemName)) {
            return false
        }

        val oldItemPrice = getPlayerPrice(oldItemName ?: "")
        val priceDifference = newItemPrice - oldItemPrice

        if (priceDifference > _remainingBudget.value) {
            return false
        }

        _remainingBudget.update { it - priceDifference }

        _droppedZones.update { currentList ->
            currentList.toMutableList().apply { this[zoneIndex] = newItemName }
        }
        return true
    }

    fun onItemRemoved(zoneIndex: Int, removedItemName: String) {
        val removedItemPrice = getPlayerPrice(removedItemName)

        _remainingBudget.update { it + removedItemPrice }

        _droppedZones.update { currentList ->
            currentList.toMutableList().apply { this[zoneIndex] = null }
        }
    }

    fun saveTeam() {
        val userId = userIdRef.get()
        if (userId == null) {
            viewModelScope.launch {
                _uiEvent.send(SideBarUiEvent.Error("User ID not found"))
            }
            return
        }

        val selectedPlayers = droppedZones.value.mapNotNull { zone ->
            _playersState.value.players.firstOrNull { it.name == zone }
        }

        viewModelScope.launch {
            val success = saveTeamUseCase.execute(
                userId = userId,
                teamName = USER_TEAM_NAME,
                selectedPlayers = selectedPlayers
            )

            if (success) {
                _uiEvent.send(SideBarUiEvent.Success("Team saved successfully"))
            } else {
                _uiEvent.send(SideBarUiEvent.Error("Failed to save team"))
            }
        }
    }


    private fun loadUserTeam() {
        val userId = userIdRef.get() ?: return

        viewModelScope.launch {
            if (_playersState.value.players.isEmpty()) {
                // Ensure players are loaded first so prices/details are available
                fetchPlayers()
            }

            try {
                val userTeam = loadTeamUseCase.execute(userId)
                userTeam?.let { team ->
                    val newDroppedZones = MutableList<String?>(NUM_ROSTER_SLOTS) { null }
                    var newBudget = INITIAL_BUDGET

                    // Restore the players to the correct slots and update the budget
                    team.players.forEach { teamPlayer ->
                        if (teamPlayer.playerPositionIndex in newDroppedZones.indices) {
                            newDroppedZones[teamPlayer.playerPositionIndex] = teamPlayer.playerName
                            newBudget -= teamPlayer.playerPrice
                        }
                    }

                    _droppedZones.update { newDroppedZones }
                    _remainingBudget.update { newBudget }
                    Log.d("SideBarVM", "Team loaded successfully.")
                    _uiEvent.send(SideBarUiEvent.Success("Welcome back! Your team, ${team.name}, has been loaded."))
                }
            } catch (e: Exception) {
                Log.e("SideBarVM", "Error loading team: ${e.message}")
                _uiEvent.send(SideBarUiEvent.Error("Could not load your saved team."))
            }
        }
    }

    data class PlayersState(
        val players: List<SimplePlayer> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
