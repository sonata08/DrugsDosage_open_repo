package sonata.open.drugsdosage.presentation.navigation

/**
 * Object for holding the screen route names and converting routes from the navController
 * into Screen instances to get the current screen for [BottomNavigationBar].
 * (Temporary decision as the navigation library doesn't support getting actual screen
 * from the navController)
 */
object ScreenRoutes {
    private const val CHILD = "Child"
    private const val SUSPENSION = "Suspension"
    private const val TABLETS_BY_WEIGHT = "TabletsByWeight"
    private const val TABLETS = "Tablets"

    private const val CONTACT_US = "ContactUs"
    private const val SHARE = "Share"
    private const val RATE = "Rate"
    private const val DISCLAIMER = "Disclaimer"

    private const val TIMER = "Timer"
    private const val INFO = "Info"

    fun routeToScreen(route: String?): Screen {
        val screenName = extractScreenName(route)
        return when(screenName) {
            CHILD -> Screen.BottomTab.Child
            SUSPENSION -> Screen.BottomTab.Suspension
            TABLETS_BY_WEIGHT -> Screen.BottomTab.TabletsByWeight
            TABLETS -> Screen.BottomTab.Tablets
            CONTACT_US -> Screen.DrawerItem.ContactUs
            SHARE -> Screen.DrawerItem.Share
            RATE -> Screen.DrawerItem.Rate
            DISCLAIMER -> Screen.DrawerItem.Disclaimer
            TIMER -> Screen.Timer
            INFO -> Screen.Info
            else -> Screen.BottomTab.Child
        }
    }

    private fun extractScreenName(fullString: String?): String? {
        val parts = fullString?.split(".")
        return parts?.get(parts.size-1)
    }
}