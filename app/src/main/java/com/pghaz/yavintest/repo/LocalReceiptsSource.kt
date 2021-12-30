package com.pghaz.yavintest.repo

import com.pghaz.yavintest.database.ReceiptsDao
import com.pghaz.yavintest.model.Receipt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalReceiptsSource @Inject constructor(private val receiptsDao: ReceiptsDao) {

    val getAllReceipts: Flow<List<Receipt>> = receiptsDao.getAll()

    suspend fun insertReceipt(receipt: Receipt) {
        receiptsDao.insert(receipt)
    }
}