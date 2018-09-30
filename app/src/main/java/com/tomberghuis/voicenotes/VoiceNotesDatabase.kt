package com.tomberghuis.voicenotes

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Note::class], version = 1)
abstract class VoiceNotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: VoiceNotesDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): VoiceNotesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        VoiceNotesDatabase::class.java,
                        "voicenotes_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(VoiceNotesDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class VoiceNotesDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.notesDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(notesDao: NotesDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            notesDao.deleteAll()

            var note = Note(1,"Hello",System.currentTimeMillis())
            notesDao.insert(note)
            note = Note(2,"World!", System.currentTimeMillis())
            notesDao.insert(note)
        }
    }

}
