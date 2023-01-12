package com.keuapps.baseapp.domain.usecase

import com.keuapps.baseapp.data.remote.model.NoteResponse
import com.keuapps.baseapp.data.remote.repository.BaseRepositoryImpl
import com.keuapps.baseapp.domain.repository.BaseRepository
import com.keuapps.baseapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetContactListUseCase @Inject constructor(private val repo: BaseRepository) {
    suspend operator fun invoke(): Flow<Resource<NoteResponse>> = repo.getKisilerList()
}