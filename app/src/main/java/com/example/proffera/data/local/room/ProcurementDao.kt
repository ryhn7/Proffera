package com.example.proffera.data.local.room

import androidx.room.*
import com.example.proffera.data.local.entity.ProcurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProcurementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(procurementEntity: ProcurementEntity)

    @Update
    suspend fun update(procurementEntity: ProcurementEntity)

    @Delete
    suspend fun delete(procurementEntity: ProcurementEntity)

    @Query("SELECT * FROM procurement ORDER BY id ASC")
    fun getAllProcurements(): Flow<List<ProcurementEntity>>

    @Query("SELECT EXISTS(SELECT * FROM procurement WHERE id = :id AND is_bookmarked = 1)")
    fun isBookmarkedProcurement(id: String): Flow<Boolean>
}