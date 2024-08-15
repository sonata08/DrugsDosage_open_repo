package sonata.open.drugsdosage.data.database

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "times")
class Time(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "time")
    val time: Long

)

