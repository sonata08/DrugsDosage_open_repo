package sonata.open.drugsdosage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.data.database.TimeDao

@Database(entities = [Time::class], version = 3,
    exportSchema = true)
abstract class TimeDatabase : RoomDatabase() {
    abstract fun timeDAO(): TimeDao
}
