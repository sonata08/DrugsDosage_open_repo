package sonata.open.drugsdosage.presentation.common

import androidx.compose.runtime.staticCompositionLocalOf
import sonata.open.drugsdosage.data.repository.AnalyticsRepository
import sonata.open.drugsdosage.data.repository.impl.LoggingAnalyticsImpl

/**
 * CompositionLocal for providing a global instance of AnalyticsRepository interface.
 * By default, it uses LoggingAnalyticsImpl which logs events to the console.
 */
val LocalFirebaseAnalytics = staticCompositionLocalOf<AnalyticsRepository> { LoggingAnalyticsImpl() }