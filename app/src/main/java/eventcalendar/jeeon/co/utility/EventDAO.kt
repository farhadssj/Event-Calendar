package eventcalendar.jeeon.co.utility

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.arch.lifecycle.LiveData
@Dao
interface EventDAO {

    @get:Query("SELECT * FROM event ")
    val allEvent: LiveData<List<Event>>

    @Insert
    fun insert(event: Event)

    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)
}
