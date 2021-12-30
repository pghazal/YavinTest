package com.pghaz.yavintest.database

import androidx.room.*
import com.pghaz.yavintest.model.Receipt
import com.pghaz.yavintest.model.TicketWithQuantity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptsDao {

    @Query("SELECT * FROM receipts_table")
    fun getAll(): Flow<List<Receipt>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receipt: Receipt)
}