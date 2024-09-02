package com.learn.app.kotlins.database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learn.app.kotlins.apputills.Constants
import com.learn.app.kotlins.apputills.Constants.CONTACT_DATABASE
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM ${Constants.CONTACT_TABLE}")
    fun getAllContacts(): Flow<MutableList<ContactEntity>>
}