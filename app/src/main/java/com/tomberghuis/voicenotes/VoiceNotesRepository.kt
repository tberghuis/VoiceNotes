package com.tomberghuis.voicenotes

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class VoiceNotesRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
}
