package com.jeckonly.updatetype

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.update.TypeOrderUpdate
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.init.getInitTypeEntity
import com.jeckonly.updatetype.ui.state.UpdateTypeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UpdateTypeViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    /**
     * 默认类型是否已初始化
     */
    private val databaseInitStateFlow: StateFlow<Boolean> =
        userPrefsRepo.getTypeDatabaseInitState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    /**
     * 类型为支出的列表
     */
    private val expenseTypeFlow: StateFlow<List<TypeUI>> =
        databaseRepo.getAllExpenseType().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    /**
     * 类型为收入的列表
     */
    private val incomeTypeFlow: StateFlow<List<TypeUI>> =
        databaseRepo.getAllIncomeType().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    /**
     * 界面的UI state
     */
    val updateTypeUiStateFlow: StateFlow<UpdateTypeUiState> = combine(
        databaseInitStateFlow, expenseTypeFlow, incomeTypeFlow
    ) { hasInit, expenseTypeUIList, incomeTypeUIList ->
        UpdateTypeUiState(
            isLoading = !hasInit,
            expenseTypeList = expenseTypeUIList.sortedBy { it.order },
            incomeTypeList = incomeTypeUIList.sortedBy { it.order }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UpdateTypeUiState()
    )


    init {
        viewModelScope.launch {
            if (!userPrefsRepo.nowTypeDatabaseInitState()) {
                databaseRepo.initTypeInDatabase(list = getInitTypeEntity(app), onSuccess = {
                    userPrefsRepo.updateTypeDatabaseInitStateToFinished()
                }, onFail = {
                    // Do nothing
                })
            }
        }
    }

    fun onDragEnd(newList: List<TypeUI>, startIndex: Int, endIndex: Int) {
        viewModelScope.launch(context = Dispatchers.Default) {
            Timber.d("startIndex: ${startIndex}, endIndex: $endIndex")
            if (startIndex == endIndex) return@launch

            val itemNeedUpdate: MutableList<TypeOrderUpdate> = mutableListOf()
            for (i in newList.indices) {
                itemNeedUpdate.add(
                    TypeOrderUpdate(
                        typeId = newList[i].typeId,
                        order = i
                    )
                )
            }
            databaseRepo.updateTypeOrder(itemNeedUpdate)
        }
    }


    /**
     * [onTypeHasRecord]只有在[forceDelete]为[false]的时候才有意义
     */
    fun onClickDelete(typeId: Int, forceDelete: Boolean, onTypeHasRecord: (Int) -> Unit) {
        viewModelScope.launch {
            if (forceDelete) {
                databaseRepo.deleteTypeById(typeId)
            } else {
                if (databaseRepo.checkTypeHasRecordOrNot(typeId)) {
                    onTypeHasRecord(typeId)
                } else {
                    databaseRepo.deleteTypeById(typeId)
                }
            }
        }
    }

}



