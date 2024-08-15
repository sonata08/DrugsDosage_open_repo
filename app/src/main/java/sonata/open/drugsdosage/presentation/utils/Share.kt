package sonata.open.drugsdosage.presentation.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import sonata.open.drugsdosage.BuildConfig
import sonata.open.drugsdosage.R


fun shareApp(context: Context) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND

        putExtra(Intent.EXTRA_TEXT, BuildConfig.GOOGLE_PLAY_URL)
        putExtra(Intent.EXTRA_TITLE, context.getString(R.string.app_name))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, context.getString(R.string.share_app))
    startActivity(context, shareIntent, null)
}