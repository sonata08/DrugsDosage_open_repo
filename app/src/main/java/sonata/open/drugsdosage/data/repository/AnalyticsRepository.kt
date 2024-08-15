package sonata.open.drugsdosage.data.repository

/**
 * AnalyticsRepository interface defines a contract for logging events.
 * Implementations of this interface can log events to different backends.
 */
interface AnalyticsRepository {
    fun logEvent(event: String)
}