package com.example.fantastika.SideBar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SideBarViewModel: ViewModel() {

    private val _droppedZones = MutableStateFlow(
        listOf<String?>(null, null, null, null, null)
    )
    val droppedZones = _droppedZones.asStateFlow()

    private val _usedItems = MutableStateFlow<List<String>>(emptyList())
    val usedItems = _usedItems.asStateFlow()

    fun onItemDropped(zoneIndex: Int, newItem: String) {
        val oldItem = _droppedZones.value[zoneIndex]
        val updatedZones = _droppedZones.value.toMutableList()
        updatedZones[zoneIndex] = newItem
        _droppedZones.value = updatedZones

        oldItem?.let {
            _usedItems.value = _usedItems.value - it
        }
        _usedItems.value = _usedItems.value + newItem
    }

    fun onItemRemoved(zoneIndex: Int, removedItem: String) {
        val updatedZones = _droppedZones.value.toMutableList()
        updatedZones[zoneIndex] = null
        _droppedZones.value = updatedZones

        _usedItems.value = _usedItems.value - removedItem
    }
}