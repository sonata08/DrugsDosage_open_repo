package sonata.open.drugsdosage.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugDosageTopBar(
    currentScreen: Screen,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    action: @Composable (RowScope.() -> Unit) = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.titleId),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            SetNavigationIcon(
                currentScreen = currentScreen,
                onNavigate = onNavigate
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = modifier,
        actions = action,
    )
}

@Composable
fun SetNavigationIcon(currentScreen: Screen, onNavigate: () -> Unit) {
    if (currentScreen in bottomNavScreens) {
        IconButton(
            onClick = onNavigate
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "menu"
            )
        }
    } else {
        IconButton(
            onClick = onNavigate
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "menu"
            )
        }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    DrugsDosageTheme {
        DrugDosageTopBar(
            currentScreen = Screen.BottomTab.Child,
            onNavigate = {}
        )
    }
}