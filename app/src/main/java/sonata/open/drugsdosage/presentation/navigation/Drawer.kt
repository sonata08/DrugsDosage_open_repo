package sonata.open.drugsdosage.presentation.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme
import sonata.open.drugsdosage.presentation.utils.rateApp
import sonata.open.drugsdosage.presentation.utils.shareApp

@Composable
fun Drawer(
    onItemClick: (Screen) -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Column(modifier = Modifier.padding(dimensionResource(R.dimen.margin_medium))) {
                            Text(
                                stringResource(R.string.app_name),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_medium))
                            )
                            Image(
                                painter = painterResource(R.mipmap.ic_launcher_foreground),
                                contentDescription = null
                            )
                        }
                    }
                }
                drawerItems.forEachIndexed { index, screen ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = screen.titleId),
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(screen.iconId),
                                contentDescription = stringResource(id = screen.titleId)
                            )
                        },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            when (screen) {
                                is Screen.DrawerItem.Share -> {
                                    shareApp(context)
                                }
                                is Screen.DrawerItem.Rate -> {
                                    rateApp(context)
                                }
                                else -> {
                                    onItemClick(screen)
                                }
                            }
                        }
                    )
                    if (index == 2) { // Insert divider after the third item (index 2)
                        HorizontalDivider()
                    }
                }
            }
        }
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreview() {
    DrugsDosageTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        val scope = rememberCoroutineScope()
        Drawer(onItemClick = {}, drawerState = drawerState, scope = scope) {

        }
    }
}