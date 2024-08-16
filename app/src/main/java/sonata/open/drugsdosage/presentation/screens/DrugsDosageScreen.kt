package sonata.open.drugsdosage.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.BuildConfig
import sonata.open.drugsdosage.presentation.common.components.AdaptiveBannerAd
import sonata.open.drugsdosage.presentation.navigation.AppBarAction
import sonata.open.drugsdosage.presentation.navigation.BottomNavigationBar
import sonata.open.drugsdosage.presentation.navigation.Drawer
import sonata.open.drugsdosage.presentation.navigation.DrugDosageTopBar
import sonata.open.drugsdosage.presentation.navigation.Screen
import sonata.open.drugsdosage.presentation.navigation.ScreenRoutes
import sonata.open.drugsdosage.presentation.navigation.bottomNavScreens
import sonata.open.drugsdosage.presentation.screens.child_suspension.ChildSuspensionScreenWithViewModel
import sonata.open.drugsdosage.presentation.screens.contact_us.ContactUsScreen
import sonata.open.drugsdosage.presentation.screens.disclaimer.DisclaimerScreen
import sonata.open.drugsdosage.presentation.screens.info_suspension.InfoSuspension
import sonata.open.drugsdosage.presentation.screens.suspension.SuspensionScreenWithViewModel
import sonata.open.drugsdosage.presentation.screens.tablet.TabletScreenWithViewModel
import sonata.open.drugsdosage.presentation.screens.tablet_weight.TabletWeightScreenWithViewModel
import sonata.open.drugsdosage.presentation.screens.timer.TimerScreenWithViewModel
import sonata.open.drugsdosage.presentation.utils.DoubleBackPressHandler


@Composable
fun DrugsDosageScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val currentBackStack by navController.currentBackStackEntryAsState()

    val currentDestination = currentBackStack?.destination?.route
    val currentScreen = ScreenRoutes.routeToScreen(currentDestination)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Drawer(
        onItemClick = { navController.navigate(it) },
        drawerState = drawerState,
        scope = scope
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                DrugDosageTopBar(
                    currentScreen = currentScreen,
                    onNavigate = {
                        if (currentScreen in bottomNavScreens) {
                            scope.launch { drawerState.open() }
                        } else {
                            navController.navigateUp()
                        }
                    },
                    action = {
                        AppBarAction(
                            currentScreen = currentScreen,
                            onAction = { navController.navigate(it) }
                        )
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    onTabSelected = { newScreen ->
                        onNavigate(navController, newScreen)
                    },
                    currentScreen = currentScreen
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                DrugsDosageNavHost(
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
                AdaptiveBannerAd(BuildConfig.AD_UNIT_ID)
            }
        }
    }
}


@Composable
fun DrugsDosageNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BottomTab.Child,
        modifier = modifier
    ) {
        composable<Screen.BottomTab.Child> {
            DoubleBackPressHandler()
            ChildSuspensionScreenWithViewModel()
        }
        composable<Screen.BottomTab.Suspension> {
            // on system back press go to the ChildScreen saving the state of the SuspensionScreen
            BackHandler(true) {
                onNavigate(navController, Screen.BottomTab.Child)
            }
            SuspensionScreenWithViewModel()
        }
        composable<Screen.BottomTab.TabletsByWeight> {
            BackHandler(true) {
                onNavigate(navController, Screen.BottomTab.Child)
            }
            TabletWeightScreenWithViewModel()
        }
        composable<Screen.BottomTab.Tablets> {
            BackHandler(true) {
                onNavigate(navController, Screen.BottomTab.Child)
            }
            TabletScreenWithViewModel()
        }
        composable<Screen.DrawerItem.ContactUs> { ContactUsScreen() }
        composable<Screen.DrawerItem.Disclaimer> { DisclaimerScreen() }
        composable<Screen.Timer> { TimerScreenWithViewModel() }
        composable<Screen.Info> { InfoSuspension() }
    }
}

private fun onNavigate(navController: NavHostController, screen: Screen) {
    navController.navigate(screen) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        launchSingleTop = true
        restoreState = true
    }
}

