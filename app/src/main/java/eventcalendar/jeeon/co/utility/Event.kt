package eventcalendar.jeeon.co.utility

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity (tableName = "event")
class Event(@field:PrimaryKey(autoGenerate = true) @field:ColumnInfo(name = "id") var id: Int,
            @field:ColumnInfo(name = "title") var title: String?,
            @field:ColumnInfo(name = "details") var details: String?,
            @field:ColumnInfo(name = "event_date") var event_date: Long?)
