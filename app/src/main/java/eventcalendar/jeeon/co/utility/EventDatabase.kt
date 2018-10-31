package eventcalendar.jeeon.co.utility

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.launch
import kotlin.jvm.java

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDAO(): EventDAO

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EventDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context,
                        EventDatabase::class.java,
                        "event_database"
                ).addCallback(EventDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class EventDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {}
            }
        }
    }
}
