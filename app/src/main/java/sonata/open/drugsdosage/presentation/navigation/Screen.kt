package sonata.open.drugsdosage.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import sonata.open.drugsdosage.R

@Serializable
sealed class Screen(@StringRes val titleId: Int) {

    @Serializable
    sealed class BottomTab(
        @StringRes val bottomTabTitleId: Int,
        @DrawableRes val menuItemId: Int,
    ) : Screen(bottomTabTitleId) {

        @Serializable
        data object Child : BottomTab(R.string.child_dosage, R.drawable.ic_child_susp)

        @Serializable
        data object Suspension : BottomTab(R.string.nav_suspension, R.drawable.ic_susp)

        @Serializable
        data object TabletsByWeight : BottomTab(R.string.nav_tablets_by_weight, R.drawable.ic_pill_weight)

        @Serializable
        data object Tablets : BottomTab(R.string.nav_tablets, R.drawable.ic_pill)
    }

    @Serializable
    sealed class DrawerItem(
        @StringRes val drawerItemTitleId: Int,
        @DrawableRes val iconId: Int,
    ) : Screen(drawerItemTitleId) {

        @Serializable
        data object ContactUs : DrawerItem(R.string.email_us, R.drawable.ic_email)

        @Serializable
        data object Share : DrawerItem(R.string.share, R.drawable.ic_share)

        @Serializable
        data object Rate : DrawerItem(R.string.rate, R.drawable.ic_star_rate)

        @Serializable
        data object Disclaimer :
            DrawerItem(R.string.disclaimer, R.drawable.ic_warning)
    }

    @Serializable
    data object Timer : Screen(R.string.timer)

    @Serializable
    data object Info : Screen(R.string.instruction)
}

val bottomNavScreens = listOf(
    Screen.BottomTab.Child,
    Screen.BottomTab.Suspension,
    Screen.BottomTab.TabletsByWeight,
    Screen.BottomTab.Tablets
)

val drawerItems = listOf(
    Screen.DrawerItem.ContactUs,
    Screen.DrawerItem.Share,
    Screen.DrawerItem.Rate,
    Screen.DrawerItem.Disclaimer,
)

