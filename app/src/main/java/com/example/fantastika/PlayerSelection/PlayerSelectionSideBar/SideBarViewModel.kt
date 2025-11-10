package com.example.fantastika.PlayerSelection.PlayerSelectionSideBar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantastika.PlayerSelection.Data.allPlayers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

class SideBarViewModel : ViewModel() {
    companion object {
        private const val INITIAL_BUDGET = 5000
    }

    private val _droppedZones = MutableStateFlow(
        listOf<String?>(null, null, null, null, null)
    )
    val droppedZones: StateFlow<List<String?>> = _droppedZones.asStateFlow()

    val usedItems: StateFlow<List<String>> = droppedZones.map { zones ->
        zones.filterNotNull().distinct()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _remainingBudget = MutableStateFlow(INITIAL_BUDGET)
    val remainingBudget: StateFlow<Int> = _remainingBudget.asStateFlow()

    private fun getPlayerPrice(playerName: String): Int {
        return allPlayers.firstOrNull { it.name == playerName }?.price ?: 0
    }

    fun onItemDropped(zoneIndex: Int, newItemName: String): Boolean {
        val newItemPrice = getPlayerPrice(newItemName)
        val oldItemName = _droppedZones.value[zoneIndex]

        if (oldItemName == null && _droppedZones.value.contains(newItemName)) {
            return false
        }

        var oldItemPrice = 0
        if (oldItemName != null) {
            oldItemPrice = getPlayerPrice(oldItemName)
        }

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
}