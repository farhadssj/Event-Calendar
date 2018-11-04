package eventcalendar.jeeon.co.utility
import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class EventRepository(private val eventDao: EventDAO) {

    val allEvents: LiveData<List<Event>> = eventDao.allEvent

    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    @WorkerThread
    suspend fun update(event: Event) {
        eventDao.update(event)
    }

    @WorkerThread
    suspend fun delete(event: Event) {
        eventDao.delete(event)
    }
}
