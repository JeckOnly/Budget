package com.jeckonly.recorddetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.recorddetail.ui.state.RecordDetailUIState
import com.jeckonly.util.FormatNumberUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

//    private val recordIdFlow: MutableStateFlow<Int> = MutableStateFlow(-1)

    // NOTE StateFlow会用equals()判断值是否相同，相同就不emit，为了达到重组就刷新的效果，改为share flow。
    private val recordIdFlow: MutableSharedFlow<Int> = MutableSharedFlow<Int>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    ).apply {
        tryEmit(-1)
    }

    val recordDetailUIStateFlow: StateFlow<RecordDetailUIState> = recordIdFlow.map {
        // 非法值
        if (it == -1) return@map RecordDetailUIState()

        val recordDetailUI = databaseRepo.getRecordDetailUIById(it)
        val result = RecordDetailUIState(
            iconId = recordDetailUI.iconId,
            typeName = recordDetailUI.typeName,
            expenseOrIncome = if (recordDetailUI.expenseOrIncome is ExpenseOrIncome.Expense) app.getString(
                com.jeckonly.designsystem.R.string.expense) else app.getString(
                com.jeckonly.designsystem.R.string.income),
            showNumber = FormatNumberUtil.format(recordDetailUI.number),
            showDate = RecordDetailUIState.makeShowDate(recordDetailUI.year, recordDetailUI.month, recordDetailUI.dayOfMonth),
            remark = recordDetailUI.remark
        )
        Timber.d(result.toString())
        result
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecordDetailUIState()
    )


    fun initViewModel(recordId: Int) {
        Timber.d("recordId: $recordId")
//        recordIdFlow.update {
//            recordId
//        }
        recordIdFlow.tryEmit(recordId)
    }

    fun onDelete(recordId: Int, callback: () -> Unit) {
        viewModelScope.launch {
            databaseRepo.deleteRecordById(recordId)
            callback()
        }
    }
}



