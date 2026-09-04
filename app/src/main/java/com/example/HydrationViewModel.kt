package com.example

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class WaterEntry(
    val id: Long = System.currentTimeMillis(),
    val amountMl: Int,
    val timeFormatted: String
)

data class HydrationUiState(
    val currentIntakeMl: Int = 0,
    val targetGoalMl: Int = 2000,
    val history: List<WaterEntry> = emptyList(),
    val celebrationTrigger: Long = 0L
) {
    val progress: Float
        get() = if (targetGoalMl > 0) (currentIntakeMl.toFloat() / targetGoalMl.toFloat()) else 0f

    val percentage: Int
        get() = (progress * 100).toInt()

    val isGoalReached: Boolean
        get() = currentIntakeMl >= targetGoalMl

    val remainingMl: Int
        get() = (targetGoalMl - currentIntakeMl).coerceAtLeast(0)
}

class HydrationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HydrationUiState())
    val uiState: StateFlow<HydrationUiState> = _uiState.asStateFlow()

    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun addWater(amountMl: Int = 250) {
        if (amountMl <= 0) return
        val now = timeFormatter.format(Date())
        val newEntry = WaterEntry(
            id = System.currentTimeMillis(),
            amountMl = amountMl,
            timeFormatted = now
        )

        _uiState.update { current ->
            val newTotal = current.currentIntakeMl + amountMl
            val previouslyReached = current.isGoalReached
            val newlyReached = newTotal >= current.targetGoalMl
            val trigger = if (!previouslyReached && newlyReached) System.currentTimeMillis() else current.celebrationTrigger

            current.copy(
                currentIntakeMl = newTotal,
                history = listOf(newEntry) + current.history,
                celebrationTrigger = trigger
            )
        }
    }

    fun reset() {
        _uiState.update { current ->
            current.copy(
                currentIntakeMl = 0,
                history = emptyList()
            )
        }
    }

    fun removeEntry(entry: WaterEntry) {
        _uiState.update { current ->
            val updatedHistory = current.history.filter { it.id != entry.id }
            val updatedTotal = (current.currentIntakeMl - entry.amountMl).coerceAtLeast(0)
            current.copy(
                currentIntakeMl = updatedTotal,
                history = updatedHistory
            )
        }
    }
}
