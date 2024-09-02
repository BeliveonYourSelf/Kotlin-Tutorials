package com.learn.app.kotlins.di.repository

import com.learn.app.kotlins.database.ContactDao
import com.learn.app.kotlins.database.ContactEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: ContactDao) {
    suspend fun saveContacts(entity: ContactEntity) {
        dao.saveContact(entity)
    }

    suspend fun getAllContacts(): Flow<MutableList<ContactEntity>> {
        return dao.getAllContacts()

    }
}