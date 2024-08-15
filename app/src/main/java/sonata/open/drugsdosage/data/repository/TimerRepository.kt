package sonata.open.drugsdosage.data.repository

import kotlinx.coroutines.flow.Flow
import sonata.open.drugsdosage.data.database.Time

interface TimerRepository {
    fun getAllTimes(): Flow<List<Time>>
    suspend fun insert(time: Time)
    suspend fun delete(time: Time)
//    suspend fun deleteAll()
}