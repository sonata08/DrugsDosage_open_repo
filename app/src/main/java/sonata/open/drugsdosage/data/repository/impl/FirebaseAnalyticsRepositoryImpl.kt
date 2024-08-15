package sonata.open.drugsdosage.data.repository.impl

import com.google.firebase.analytics.FirebaseAnalytics
import sonata.open.drugsdosage.data.repository.AnalyticsRepository
import javax.inject.Inject

/**
 * FirebaseAnalyticsRepositoryImpl is an implementation of [AnalyticsRepository]
 * that logs events to Firebase AnalyticsRepository.
 *
 * @property analytics The FirebaseAnalytics instance used to log events.
 */
class FirebaseAnalyticsRepositoryImpl @Inject constructor(private val analytics: FirebaseAnalytics):
    AnalyticsRepository {
     override fun logEvent(event: String) {
       analytics.logEvent(event, null)
    }
}