package sonata.open.drugsdosage.data.repository.impl

import sonata.open.drugsdosage.data.database.Time
import sonata.open.drugsdosage.data.database.TimeDao
import sonata.open.drugsdosage.data.repository.TimerRepository
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val timeDao: TimeDao
): TimerRepository {

    override fun getAllTimes() = timeDao.getAllTimes()

    override suspend fun insert(time: Time) {
        timeDao.addTime(time)
    }

    override suspend fun delete(time: Time) {
        timeDao.delRow(time)
    }

//    override suspend fun deleteAll() {
//        timeDao.delAllTimes()
//    }
}