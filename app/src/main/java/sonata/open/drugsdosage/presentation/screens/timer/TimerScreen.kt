package sonata.open.drugsdosage.presentation.screens.timer

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.presentation.screens.timer.components.ShowEmptyScreenText
import sonata.open.drugsdosage.presentation.screens.timer.components.TimeDiffRow
import sonata.open.drugsdosage.presentation.screens.timer.components.TimeRow
import sonata.open.drugsdosage.presentation.screens.timer.model.TimeWithStringFormat
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun TimerScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = hiltViewModel()
) {
    val allTimes by viewModel.timeList.collectAsStateWithLifecycle(initialValue = emptyList())
    val timeDiffState by viewModel.timeState.collectAsStateWithLifecycle(initialValue = TimeDiffState())

    TimerScreen(
        times = allTimes,
        timeDiffState = timeDiffState,
        onAddTime = { viewModel.addCurrentTime() },
        onDeleteTime = { viewModel.delRow(it) },
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerScreen(
    times: List<TimeWithStringFormat>,
    timeDiffState: TimeDiffState,
    onAddTime: () -> Unit,
    onDeleteTime: (Time) -> Unit,
    modifier: Modifier = Modifier
) {
    // remember list state to scroll to the beginning when adding a new item
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddTime()
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_time))
            }
        }
    ) { innerPadding ->
        ShowEmptyScreenText(
            list = times, modifier = modifier
                .padding(innerPadding)
        )
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_small))
        ) {
            stickyHeader {
                if (times.isNotEmpty()) {
                    TimeDiffRow(
                        timeDiffState = timeDiffState,
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.margin_small)),
                    )
                }
            }
            items(times, key = { it.id }) {
                TimeRow(
                    time = it,
                    onDeleteTime = onDeleteTime,
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TimeScreenPreview() {
    DrugsDosageTheme {
        Surface {
            TimerScreen(
                times = listOf(TimeWithStringFormat(1, 0L, "time_1"), TimeWithStringFormat(2, 0L, "time_1")),
                timeDiffState = TimeDiffState(22, 3, 122),
                onAddTime = {},
                onDeleteTime = {},
//                onUpdateDiff = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeScreenEmptyPreview() {
    DrugsDosageTheme {
        Surface {
            TimerScreen(
                times = emptyList(),
                timeDiffState = TimeDiffState(22, 3, 122),
                onAddTime = {},
                onDeleteTime = {},
//                onUpdateDiff = {},
            )
        }
    }
}