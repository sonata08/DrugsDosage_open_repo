package sonata.open.drugsdosage.presentation.screens.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.data.repository.TimerRepository
import sonata.open.drugsdosage.presentation.screens.timer.model.TimeWithStringFormat
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTimeDifference
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerRepository: TimerRepository
) : ViewModel() {

    // StateFlow to hold the time difference
    private val _resultState = MutableStateFlow(TimeDiffState())
    val timeState = _resultState.asStateFlow()

    // StateFlow to hold the time list
    val timeList = timerRepository.getAllTimes()
        .map { timeList ->
            timeList.map { TimeWithStringFormat(it) }
        }.stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    private fun updateTimeDifference() {
        viewModelScope.launch {
            timeList.collectLatest {
                val lastTime = it.firstOrNull()?.timeMillis ?: 0
                _resultState.value =
                    if (lastTime > 0) calculateTimeDifference(lastTime) else TimeDiffState()
            }
        }
    }

    init {
        startPeriodicTimeUpdate()
    }

    // Updates time every minute if a user stays on timer screen
    private fun startPeriodicTimeUpdate() {
        viewModelScope.launch {
            while (isActive) {
                updateTimeDifference()
                delay(1.minutes) // Update every minute
            }
        }
    }

    fun addCurrentTime() {
        Log.d("FAT_addTime", "addTime")
        insert(Time(0, Calendar.getInstance().time.time))
    }

    fun delRow(time: Time) {
        viewModelScope.launch {
            timerRepository.delete(time)
        }
    }

    private fun insert(time: Time) {
        viewModelScope.launch {
            timerRepository.insert(time)
        }
    }
}
