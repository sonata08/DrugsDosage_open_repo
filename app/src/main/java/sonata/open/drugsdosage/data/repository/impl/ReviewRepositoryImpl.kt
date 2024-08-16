package sonata.open.drugsdosage.data.repository.impl

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory
import sonata.open.drugsdosage.data.datastore.DataStorePreferences
import javax.inject.Inject


/**
 * Repository class responsible for managing review logic in the app.
 * This class interacts with DataStore to track the number of app launches
 * and triggers the In-App Review flow when a specific criteria is met.
 */
class ReviewRepositoryImpl @Inject constructor(
    private val dataStore: DataStorePreferences
) {
    /**
     * Checks the launch count stored in [DataStorePreferences], triggers the In-App Review dialog
     * if the launch count equals [NUMBER_OF_TIMES_OPENED] and resets the counter.
     *
     * @param activity The activity context required for launching the In-App Review dialog.
     */
    suspend fun requestAppReviewDialog(activity: Activity) {
        if (dataStore.getCounter() == NUMBER_OF_TIMES_OPENED) {
            dataStore.resetCounter()
            launchInAppReview(activity)
        }
        dataStore.incrementCounter()
    }

    /**
     * Launches the In-App Review flow using the Play Store Review Manager API.
     * Because of a time-bound quota, might not always display a dialog.
     *
     * @param activity The activity context required for launching the In-App Review dialog.
     */
     private fun launchInAppReview(activity: Activity) {
        val manager = ReviewManagerFactory.create(activity)
        manager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) {
                manager.launchReviewFlow(activity, it.result)
            }
        }
    }

    companion object {
        /** number of times the application needs to be opened before the rate request */
        private const val NUMBER_OF_TIMES_OPENED = 3
    }
}