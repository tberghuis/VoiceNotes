package com.tomberghuis.voicenotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//import android.arch.lifecycle.LiveData
//import android.arch.persistence.room.Dao
//import android.arch.persistence.room.Insert
//import android.arch.persistence.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes order by created_time")
    fun getAll(): LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAll()

    // do i need a getNote?????
}
