package com.pghaz.yavintest.ui.history.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pghaz.yavintest.model.Receipt
import com.pghaz.yavintest.repo.ReceiptsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    application: Application,
    private val receiptsRepository: ReceiptsRepository
) : AndroidViewModel(application) {

    val receiptsLiveData = receiptsRepository.localReceiptsSource.getAllReceipts.asLiveData()

    fun saveReceipt(receipt: Receipt) =
        viewModelScope.launch(Dispatchers.IO) {
            receiptsRepository.localReceiptsSource.insertReceipt(receipt)
        }
}