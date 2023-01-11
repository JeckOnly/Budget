package com.jeckonly.choosetype

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.choosetype.ui.state.KeyboardState
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.init.getInitTypeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChooseTypeViewModel @Inject constructor(
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
    val chooseTypeUiStateFlow: StateFlow<ChooseTypeUiState> = combine(
        databaseInitStateFlow, expenseTypeFlow, incomeTypeFlow
    ) { hasInit, expenseTypeUIList, incomeTypeUIList ->
        ChooseTypeUiState(
            isLoading = !hasInit,
            expenseTypeList = expenseTypeUIList.sortedBy { it.order },
            incomeTypeList = incomeTypeUIList.sortedBy { it.order }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChooseTypeUiState()
    )

    /**
     * keyboard state
     */
    val keyboardState: KeyboardState by lazy {
        KeyboardState(app = app) { chooseTypeFinishState, popBackStack ->
            viewModelScope.launch {
                databaseRepo.insertRecord(chooseTypeFinishState.toRecordEntity(), onSuccess = {
                    Timber.d("成功插入")
                    popBackStack()
                }, onFail = {
                    Timber.d("插入失败" + it.message)
                })
            }
        }
    }

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
}