package com.learn.app.kotlins.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.learn.app.kotlins.apputills.Constants

@Database(entities = [ContactEntity::class], version = Constants.DATABASE_VERSION, exportSchema = Constants.EXPORT_SCHEMA)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun  contactDao() : ContactDao
}