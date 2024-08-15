package sonata.open.drugsdosage.presentation.navigation

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun BottomNavigationBar(
    onTabSelected: (Screen) -> Unit,
    currentScreen: Screen,
    modifier: Modifier = Modifier,
) {
    // No bottom navigation bar for drawer items
    if (currentScreen !in bottomNavScreens) {
        return
    }
    NavigationBar(modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        bottomNavScreens.forEach { screen ->
            NavigationBarItem(
                selected = currentScreen == screen,
                onClick = { onTabSelected(screen) },
                icon = {
                    Icon(
                        painter = painterResource(screen.menuItemId),
                        contentDescription = stringResource(screen.titleId)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavPreview() {
    DrugsDosageTheme {
        BottomNavigationBar(
            onTabSelected = {},
            currentScreen = Screen.BottomTab.Suspension
        )
    }
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavDarkSchemePreview() {
    DrugsDosageTheme {
        BottomNavigationBar(
            onTabSelected = {},
            currentScreen = Screen.BottomTab.Suspension
        )
    }
}

