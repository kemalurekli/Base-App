package com.keuapps.baseapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keuapps.baseapp.data.local.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote (note: Note)

    @Delete
    suspend fun deleteNote (note: Note)

    @Query("SELECT * FROM notes")
    fun observeNote() : LiveData<List<Note>>
}