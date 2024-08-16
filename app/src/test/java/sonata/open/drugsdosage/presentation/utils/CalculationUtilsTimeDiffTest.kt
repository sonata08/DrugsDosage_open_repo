package sonata.open.drugsdosage.presentation.utils



import com.google.common.truth.Truth.assertThat
import org.junit.Test
import sonata.open.drugsdosage.presentation.utils.CalculationUtils.calculateTimeDifference
import java.util.Calendar
import java.util.concurrent.TimeUnit

class CalculationUtilsTimeDiffTest {


    @Test
    fun `calculateTimeDifference returns correct values for zero difference`() {
        val lastDate = Calendar.getInstance().time.time
        val result = calculateTimeDifference(lastDate)

        assertThat(result.days).isEqualTo(0)
        assertThat(result.hours).isEqualTo(0)
        assertThat(result.minutes).isEqualTo(0)
    }

    @Test
    fun `calculateTimeDifference returns correct values for 1 minute difference`() {
        val oneMinute = TimeUnit.MINUTES.toMillis(1)
        val lastDate = Calendar.getInstance().time.time - oneMinute
        val result = calculateTimeDifference(lastDate)

        assertThat(result.days).isEqualTo(0)
        assertThat(result.hours).isEqualTo(0)
        assertThat(result.minutes).isEqualTo(1)
    }

    @Test
    fun `calculateTimeDifference returns correct values for 1 hour difference`() {
        val oneHour = TimeUnit.HOURS.toMillis(1)
        val lastDate = Calendar.getInstance().time.time - oneHour

        val result = calculateTimeDifference(lastDate)

        assertThat(result.days).isEqualTo(0)
        assertThat(result.hours).isEqualTo(1)
        assertThat(result.minutes).isEqualTo(0)
    }

    @Test
    fun `calculateTimeDifference returns correct values for 1 day difference`() {
        val oneDay = TimeUnit.DAYS.toMillis(1)
        val lastDate = Calendar.getInstance().time.time - oneDay
        val result = calculateTimeDifference(lastDate)

        assertThat(result.days).isEqualTo(1)
        assertThat(result.hours).isEqualTo(0)
        assertThat(result.minutes).isEqualTo(0)
    }

    @Test
    fun `calculateTimeDifference returns correct values for complex difference`() {
        val twoDays = TimeUnit.DAYS.toMillis(2)
        val threeHours = TimeUnit.HOURS.toMillis(3)
        val fortyFiveMinutes = TimeUnit.MINUTES.toMillis(45)

        val lastDate = Calendar.getInstance().time.time - (twoDays + threeHours + fortyFiveMinutes)

        val result = calculateTimeDifference(lastDate)

        assertThat(result.days).isEqualTo(2)
        assertThat(result.hours).isEqualTo(3)
        assertThat(result.minutes).isEqualTo(45)
    }

}