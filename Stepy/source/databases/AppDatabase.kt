package com.steps.tracker.machine.analyzer.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quanpham.db.Dao.StepDao
import com.example.quanpham.db.Dao.WeightDao
import com.steps.tracker.machine.analyzer.databases.models.Steps
import com.steps.tracker.machine.analyzer.databases.models.Weights

@Database(entities = [Steps::class, Weights::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stepDao(): StepDao
    abstract fun weightDao(): WeightDao

}