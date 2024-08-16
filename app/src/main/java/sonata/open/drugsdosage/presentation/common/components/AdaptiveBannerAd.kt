package sonata.open.drugsdosage.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.window.layout.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * A composable function that displays a Google AdMob adaptive banner ad.
 *
 * This function uses [AndroidView] to embed a native Android `AdView` in a Jetpack Compose layout.
 * It dynamically calculates the ad size based on the current window metrics to ensure it fits
 * the width of the screen.
 *
 * @param unitId The AdMob ad unit ID for the banner ad.
 * @param modifier The [Modifier] to be applied to the AdView. Defaults to [Modifier].
 */
@Composable
fun AdaptiveBannerAd(unitId: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            val windowMetrics: WindowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
            val bounds = windowMetrics.bounds

            var adWidthPixels: Float = context.resources.displayMetrics.widthPixels.toFloat()

            if (adWidthPixels == 0f) {
                adWidthPixels = bounds.width().toFloat()
            }

            val density: Float = context.resources.displayMetrics.density
            val adWidth = (adWidthPixels / density).toInt()

            AdView(context).apply {
                setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth))
                adUnitId = unitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}