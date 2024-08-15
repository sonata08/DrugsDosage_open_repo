package sonata.open.drugsdosage.presentation


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.data.repository.impl.FirebaseAnalyticsRepositoryImpl
import sonata.open.drugsdosage.data.repository.impl.ReviewRepositoryImpl
import sonata.open.drugsdosage.presentation.common.LocalFirebaseAnalytics
import sonata.open.drugsdosage.presentation.screens.DrugsDosageScreen
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme
import javax.inject.Inject


@AndroidEntryPoint
class ComposeActivity: ComponentActivity() {
    @Inject lateinit var reviewRepository: ReviewRepositoryImpl
    @Inject lateinit var firebaseAnalytics: FirebaseAnalyticsRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the Google Mobile Ads SDK
        MobileAds.initialize(this)
        enableEdgeToEdge()
        setContent {
            DrugsDosageTheme {
                CompositionLocalProvider(LocalFirebaseAnalytics provides firebaseAnalytics) {
                    DrugsDosageScreen()
                }
            }
        }
        // request google dialog to rate the app
        lifecycleScope.launch {
            reviewRepository.requestAppReviewDialog(this@ComposeActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FAT_Activity", "onDestroy activity")

    }
}
