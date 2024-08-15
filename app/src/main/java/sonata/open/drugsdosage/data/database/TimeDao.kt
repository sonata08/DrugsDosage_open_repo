package sonata.open.drugsdosage.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import sonata.open.drugsdosage.data.database.Time

@Dao
interface TimeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTime(time: Time)

    @Query("SELECT * FROM times ORDER BY id DESC")
    fun getAllTimes(): Flow<List<Time>>

    @Delete
    suspend fun delRow(time: Time)

    @Query("DELETE FROM times")
    suspend fun delAllTimes()
}
