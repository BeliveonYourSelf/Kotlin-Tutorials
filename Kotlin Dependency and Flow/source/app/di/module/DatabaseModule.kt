package com.learn.app.kotlins.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learn.app.kotlins.apputills.Constants
import com.learn.app.kotlins.database.ContactDao
import com.learn.app.kotlins.database.ContactDatabase
import com.learn.app.kotlins.database.ContactEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(
            context, ContactDatabase::
            class.java, Constants.CONTACT_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db : ContactDatabase): ContactDao
    {
        return db.contactDao()

    }

    @Provides
    @Singleton
    fun provideEntity(): ContactEntity
    {
        return ContactEntity()
    }
}