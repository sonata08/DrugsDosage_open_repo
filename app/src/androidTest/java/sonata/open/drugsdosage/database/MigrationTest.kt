package sonata.open.drugsdosage.database

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.data.database.TimeDatabase
import sonata.open.drugsdosage.data.database.migrations.Migration1to2
import sonata.open.drugsdosage.data.database.migrations.Migration2to3
import java.io.IOException
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    // Array of all migrations.
    private val ALL_MIGRATIONS = arrayOf(
        Migration1to2(), Migration2to3()
    )

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TimeDatabase::class.java,
    )
    private val originalLocale: Locale = Locale.getDefault()

    @Before
    fun setUp() {
        Locale.setDefault(Locale("en", "US"))
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    @Throws(IOException::class)
    fun migrateAll_returnNewTableWithTimeInMillis() {
        helper.createDatabase(TEST_DB, 1).apply {
            // Insert some data using SQL queries.
            execSQL("""INSERT INTO times (id, time) VALUES (1, "Mon, 13 May 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (2, "Mon, 13 May 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (3, "Mon, 13 May 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (4, "Mon, 13 May 2024, 11:51") """)
            close()
        }

        // Open latest version of the database. Room validates the schema
        // once all migrations execute.
        val migratedDatabase = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeDatabase::class.java,
            TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase.close() // Force database to open and run migrations
        }

        runTest {
            val times = migratedDatabase.timeDAO().getAllTimes().first()
            assert(times.isNotEmpty())
            assertThat(times.size).isEqualTo(4)
            times.forEach {
                assertThat(it.time).isEqualTo(1715590260000)
            }
        }
    }

    @Test
    @Throws(IOException::class)
    fun migrateAll_wrongLocale_returnEmptyTable() {
        helper.createDatabase(TEST_DB, 1).apply {
            execSQL("""INSERT INTO times (id, time) VALUES (1, "Mon, 13 May 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (2, "сб, 13 травня 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (3, "сб, 13 мая 2024, 11:51") """)
            close()
        }

        val migratedDatabase = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeDatabase::class.java,
            TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase.close()
        }

        runTest {
            val times = migratedDatabase.timeDAO().getAllTimes().first()
            assertThat(times.size).isEqualTo(0)
        }
    }


    @Test
    @Throws(IOException::class)
    fun migrateAll_addRowAfterFailedMigration_returnOneRow() {
        helper.createDatabase(TEST_DB, 1).apply {
            execSQL("""INSERT INTO times (id, time) VALUES (1, "Mon, 13 May 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (2, "сб, 13 травня 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (3, "сб, 13 мая 2024, 11:51") """)
            execSQL("""INSERT INTO times (id, time) VALUES (4, "Sa., 13 Mai 2024, 11:51") """)
            close()
        }

        val migratedDatabase = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeDatabase::class.java,
            TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase.close()
        }

        runTest {
            migratedDatabase.timeDAO().addTime(Time(1, 1715590260000))
            val times = migratedDatabase.timeDAO().getAllTimes().first()
            assertThat(times.size).isEqualTo(1)
            assertThat(times[0].time).isEqualTo(1715590260000)
        }
    }

    @Test
    @Throws(IOException::class)
    fun migrateAll_addRowAfterSuccessfulMigration_returnTwoRows() {
        helper.createDatabase(TEST_DB, 1).apply {
            execSQL("""INSERT INTO times (id, time) VALUES (1, "Mon, 13 May 2024, 11:51") """)
            close()
        }

        val migratedDatabase = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeDatabase::class.java,
            TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase.close() // Force database to open and run migrations
        }

        runTest {
            migratedDatabase.timeDAO().addTime(Time(2, 1715590260000))
            val times = migratedDatabase.timeDAO().getAllTimes().first()
            assertThat(times.size).isEqualTo(2)
            assertThat(times[0].time).isEqualTo(1715590260000)
        }
    }
}
