package com.pghaz.yavintest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pghaz.yavintest.model.Receipt
import com.pghaz.yavintest.model.TicketWithQuantity

@Database(
    entities = [
        TicketWithQuantity::class,
        Receipt::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TicketsDatabase : RoomDatabase() {

    abstract fun ticketsDao(): TicketsDao
    abstract fun receiptsDao(): ReceiptsDao
}