package com.keuapps.baseapp.domain.repository

import com.keuapps.baseapp.data.remote.model.CRUDResponse
import com.keuapps.baseapp.data.remote.model.NoteResponse
import com.keuapps.baseapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BaseRepository {
    //suspend fun save(toDo: ToDo)
    //suspend fun update(toDo: ToDo)
    //suspend fun delete(toDo: ToDo)
    //suspend fun deleteAll()
    suspend fun getKisilerList(): Flow<Resource<NoteResponse>>
    suspend fun addNewPerson(name : String, phone : String): Flow<Resource<CRUDResponse>>

    //fun getToDoListPriority(priority: Int): Flow<List<ToDo>>
    //fun searchToDo(query: String): List<ToDo>
}