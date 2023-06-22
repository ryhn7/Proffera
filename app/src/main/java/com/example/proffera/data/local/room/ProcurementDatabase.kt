package com.example.proffera.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proffera.data.local.entity.ProcurementEntity

@Database(entities = [ProcurementEntity::class], version = 1, exportSchema = false)
abstract class ProcurementDatabase: RoomDatabase() {

    abstract fun procurementDao(): ProcurementDao

    companion object {
        private var INSTANCE: ProcurementDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): ProcurementDatabase {
            if (INSTANCE == null) {
                synchronized(ProcurementDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProcurementDatabase::class.java, "procurement_database"
                    )
                        .build()
                }
            }
            return INSTANCE as ProcurementDatabase
        }
    }
}