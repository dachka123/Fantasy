package com.example.fantastika.PlayerSelection.Presentation.PlayerSelectionSideBar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantastika.PlayerSelection.Domain.GetPlayersUseCase
import com.example.fantastika.PlayerSelection.Domain.SimplePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class SideBarViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {
    companion object {
        private const val INITIAL_BUDGET = 50.0
        private const val NUM_ROSTER_SLOTS = 5
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

    data class PlayersState(
        val players: List<SimplePlayer> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
