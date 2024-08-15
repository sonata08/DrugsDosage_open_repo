package sonata.open.drugsdosage.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun AppBarAction(
    currentScreen: Screen,
    onAction: (Screen) -> Unit,
) {
    if (currentScreen == Screen.BottomTab.Child) {
        IconButton(
            onClick = { onAction(Screen.Timer) }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_timer),
                contentDescription = stringResource(id = R.string.timer)
            )
        }
    }
    if (currentScreen == Screen.BottomTab.Suspension) {
        IconButton(
            onClick = { onAction(Screen.Info) }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = stringResource(id = R.string.instruction)
            )
        }
    }

}


@Preview
@Composable
private fun AppBarActionPreview() {
    val currentScreen = Screen.BottomTab.Child
    DrugsDosageTheme {
        DrugDosageTopBar(
            currentScreen = currentScreen,
            onNavigate = {},
            action = {
                AppBarAction(
                    currentScreen = currentScreen,
                    onAction = {}
                )
            }
        )
    }
}