package com.pghaz.yavintest.database

import androidx.room.*
import com.pghaz.yavintest.model.TicketWithQuantity
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao {

    @Query("SELECT * FROM tickets_table ORDER BY id ASC")
    fun getAll(): Flow<List<TicketWithQuantity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticket: TicketWithQuantity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tickets: List<TicketWithQuantity>)

    @Update
    suspend fun update(ticket: TicketWithQuantity)

    @Update
    suspend fun update(tickets: List<TicketWithQuantity>)
}