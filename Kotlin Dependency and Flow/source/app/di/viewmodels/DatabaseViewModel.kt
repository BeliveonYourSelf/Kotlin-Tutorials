package com.learn.app.kotlins.di.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.app.kotlins.apputills.DataStatus
import com.learn.app.kotlins.database.ContactEntity
import com.learn.app.kotlins.di.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repository: DatabaseRepository) :
    ViewModel() {

    private val _contactsList = MutableLiveData<DataStatus<List<ContactEntity>>>()
    val contactList: LiveData<DataStatus<List<ContactEntity>>>
        get() {
            return _contactsList
        }


    fun saveContacts(entity: ContactEntity) {
        viewModelScope.launch {
            repository.saveContacts(entity)
        }
    }

    fun getAllContacts() {
        viewModelScope.launch {
            repository.getAllContacts()
                .catch {
                    _contactsList.postValue(DataStatus.error(it.message.toString()))
                }
                .collect {
                    _contactsList.postValue(DataStatus.success(it, it.isEmpty()))
                }

        }
    }

}