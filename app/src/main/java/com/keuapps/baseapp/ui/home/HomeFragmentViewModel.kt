package com.keuapps.baseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keuapps.baseapp.data.remote.model.CRUDResponse
import com.keuapps.baseapp.data.remote.model.NoteResponse
import com.keuapps.baseapp.domain.usecase.AddNewPersonUseCase
import com.keuapps.baseapp.domain.usecase.GetContactListUseCase
import com.keuapps.baseapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getContactListUseCase: GetContactListUseCase,
    private val addNewPersonUseCase: AddNewPersonUseCase

) : ViewModel() {

    private var _toDoList = MutableLiveData<Resource<NoteResponse>>()
    var toDoList: LiveData<Resource<NoteResponse>> = _toDoList

    private var _addNewContact = MutableLiveData<Resource<CRUDResponse>>()
    var addNewContact: LiveData<Resource<CRUDResponse>> = _addNewContact

    init {
        getKisiler()
    }

    fun getKisiler() {
        _toDoList.value = Resource.Loading()
        viewModelScope.launch {
                 getContactListUseCase.invoke().collect {
                     _toDoList.value = it
                 }
        }
    }

    fun addNewPerson(name : String, phone : String) {
        _addNewContact.value = Resource.Loading()
        viewModelScope.launch{
            addNewPersonUseCase.invoke(name,phone).collect(){
                _addNewContact.value = it
            }
        }

    }
}