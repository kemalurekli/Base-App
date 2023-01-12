package com.keuapps.baseapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    var note : String,
    var createdDate : String,
    var editDate : String?,
    @PrimaryKey(autoGenerate = true)
    var noteId : Int? = null
)
