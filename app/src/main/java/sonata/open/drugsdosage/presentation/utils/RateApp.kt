package sonata.open.drugsdosage.presentation.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import sonata.open.drugsdosage.BuildConfig


// rate app by clicking on drawer menu item
fun rateApp(context: Context) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.GOOGLE_MARKET_URL)))
    } catch (e: ActivityNotFoundException) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.GOOGLE_PLAY_URL)))
    }
}