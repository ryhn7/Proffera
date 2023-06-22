package com.example.proffera.di

import android.content.Context
import androidx.room.Room
import com.example.proffera.data.local.room.ProcurementDao
import com.example.proffera.data.local.room.ProcurementDatabase
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
    fun provideProcurementDao(procurementDatabase: ProcurementDatabase): ProcurementDao = procurementDatabase.procurementDao()

    @Provides
    @Singleton
    fun provideProcurementDatabase(@ApplicationContext context: Context): ProcurementDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProcurementDatabase::class.java,
            "procurement_database"
        ).build()
    }
}