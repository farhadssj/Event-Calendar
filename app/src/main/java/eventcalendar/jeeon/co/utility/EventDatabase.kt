package eventcalendar.jeeon.co.utility

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlin.jvm.java

@Database(entities = [Event::class], version = 1)
abstract class EventDatabase : RoomDatabase() {

//    abstract val eventDao: EventDAO
//
//    fun cleanUp() {
//        eventDB = null
//    }
//
//    companion object {
//
//        private var eventDB: EventDatabase? = null
//
//        fun getInstance(context: Context): EventDatabase {
//            if (null == eventDB) {
//                eventDB = buildDatabaseInstance(context)
//            }
//            return eventDB
//        }
//
//        private fun buildDatabaseInstance(context: Context): EventDatabase {
//            return Room.databaseBuilder<EventDatabase>(context,
//                    EventDatabase::class.java!!,
//                    Constants.DB_NAME)
//                    .allowMainThreadQueries().build()
//        }
//    }

    abstract fun eventDAO(): EventDAO

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context,
                        EventDatabase::class.java,
                        "event_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}