package com.learn.app.kotlins.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learn.app.kotlins.apputills.Constants

@Entity(tableName = Constants.CONTACT_TABLE)
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String ="",
    var phone: String=""
)
