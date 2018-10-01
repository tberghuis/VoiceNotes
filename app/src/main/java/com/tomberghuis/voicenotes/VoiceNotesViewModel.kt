package com.tomberghuis.voicenotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class VoiceNotesViewModel(application: Application) : AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: VoiceNotesRepository

    val allNotes: LiveData<List<Note>>

    var editNoteId: Long?


    init {
        val notesDao = VoiceNotesDatabase.getDatabase(application, scope).notesDao()
        repository = VoiceNotesRepository(notesDao)
        allNotes = repository.allNotes
        editNoteId = null
    }

    fun insert(note: Note) = scope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
