package com.tomberghuis.voicenotes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true) var id: Long?,
                       @ColumnInfo(name = "note") var note: String,
                @ColumnInfo(name = "created_time") var createdTime: Long
                )
