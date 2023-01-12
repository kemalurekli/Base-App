package com.keuapps.baseapp.domain.usecase

import com.keuapps.baseapp.data.remote.model.CRUDResponse
import com.keuapps.baseapp.data.remote.model.NoteResponse
import com.keuapps.baseapp.domain.repository.BaseRepository
import com.keuapps.baseapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNewPersonUseCase @Inject constructor(private val repo: BaseRepository) {
    suspend operator fun invoke(name : String, phone : String): Flow<Resource<CRUDResponse>> = repo.addNewPerson(name,phone)
}