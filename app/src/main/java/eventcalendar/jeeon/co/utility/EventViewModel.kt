package eventcalendar.jeeon.co.utility
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class EventViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: EventRepository
    val allEvents: LiveData<List<Event>>

    init {
        val eventsDao = EventDatabase.getDatabase(application, scope).eventDAO()
        repository = EventRepository(eventsDao)
        allEvents = repository.allEvents
    }

    fun insert(event: Event) = scope.launch(Dispatchers.IO) {
        repository.insert(event)
    }

    fun update(event: Event) = scope.launch(Dispatchers.IO) {
        repository.update(event)
    }

    fun delete(event: Event) = scope.launch(Dispatchers.IO) {
        repository.delete(event)
    }
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}