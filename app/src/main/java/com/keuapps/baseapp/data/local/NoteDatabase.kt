package com.keuapps.baseapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keuapps.baseapp.data.local.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}