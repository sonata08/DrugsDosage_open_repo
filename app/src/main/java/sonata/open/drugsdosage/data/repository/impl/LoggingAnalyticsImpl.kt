package sonata.open.drugsdosage.data.repository.impl

import sonata.open.drugsdosage.data.repository.AnalyticsRepository


/**
 * LoggingAnalyticsImpl is a simple implementation of [AnalyticsRepository] that logs events to the console.
 * It is used in LocalFirebaseAnalyticsRepository by default
 */
class LoggingAnalyticsImpl: AnalyticsRepository {
    override fun logEvent(event: String) {
        println(event)
    }
}