package com.keuapps.baseapp.data.remote.repository

import com.keuapps.baseapp.data.local.NoteDao
import com.keuapps.baseapp.data.remote.RetrofitAPI
import com.keuapps.baseapp.data.remote.model.CRUDResponse
import com.keuapps.baseapp.data.remote.model.NoteResponse
import com.keuapps.baseapp.domain.repository.BaseRepository
import com.keuapps.baseapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class BaseRepositoryImpl
@Inject constructor(
    private val api: RetrofitAPI,
    private val dao: NoteDao
    ) : BaseRepository {
    override suspend fun getKisilerList(
    ): Flow<Resource<NoteResponse>> = flow {
                try {
                    val response = api.getContact()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(Resource.Success(it))
                        } ?: emit(Resource.Error("Error",null))
                    } else {
                        emit(Resource.Error("Error",null))
                    }
                } catch (e : Exception){
                    emit(Resource.Error("No Data",null))
                }


    }

    override suspend fun addNewPerson(
        name: String,
        phone: String
    ): Flow<Resource<CRUDResponse>> = flow {
        try {
            val response = api.addNewContact(name,phone)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }?: emit(Resource.Error("Error",null))
            } else {
                emit(Resource.Error("Error",null))
            }
        } catch (e : Exception){
            emit(Resource.Error("Something Wrong!",null))
        }
    }
}