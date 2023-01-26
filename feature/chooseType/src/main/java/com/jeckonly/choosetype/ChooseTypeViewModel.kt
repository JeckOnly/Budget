package com.jeckonly.choosetype

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.choosetype.navigation.ChooseTypeNavigation
import com.jeckonly.choosetype.ui.state.ChooseTypeFinishState
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.choosetype.ui.state.KeyboardState
import com.jeckonly.choosetype.ui.state.KeyboardStateInit
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.init.getInitTypeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
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

    private val editOrRecordIdFlow: MutableStateFlow<Int> = MutableStateFlow(-1)

    /**
     * 界面的UI state
     */
    val chooseTypeUiStateFlow: StateFlow<ChooseTypeUiState> = combine(
        editOrRecordIdFlow, databaseInitStateFlow, expenseTypeFlow, incomeTypeFlow
    ) { editOrRecordId, hasInit, expenseTypeUIList, incomeTypeUIList ->
        Timber.d("editOrRecordId: $editOrRecordId, expenseTypeUIListSize: ${expenseTypeUIList.size}, incomeTypeUIListSize: ${incomeTypeUIList.size}")
        if (editOrRecordId == ChooseTypeNavigation.EDIT) {
            ChooseTypeUiState(
                isLoading = !hasInit,
                expenseTypeList = expenseTypeUIList.sortedBy { it.order },
                incomeTypeList = incomeTypeUIList.sortedBy { it.order }
            )
        } else {
            val recordDetailUI = databaseRepo.getRecordDetailUIById(editOrRecordId)
            val typeUI = databaseRepo.getTypeUIById(databaseRepo.getTypeIdByRecordId(editOrRecordId))
            keyboardState.init(
                KeyboardStateInit(
                    number = recordDetailUI.number,
                    remark = recordDetailUI.remark,
                    localDate = LocalDate.of(recordDetailUI.year, recordDetailUI.month, recordDetailUI.dayOfMonth)
                )
            )
            ChooseTypeUiState(
                isLoading = !hasInit,
                nowChooseType = mutableStateOf(typeUI),
                canEnterSetting = mutableStateOf(false),
                expenseTypeList = expenseTypeUIList.sortedBy { it.order },
                incomeTypeList = incomeTypeUIList.sortedBy { it.order }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChooseTypeUiState()
    )

    /**
     * keyboard state
     */
    val keyboardState: KeyboardState =
        KeyboardState { chooseTypeFinishState, popBackStack ->
           doWhenFinished(chooseTypeFinishState, popBackStack)
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

    fun initEditOrRecordId(editOrRecordId: Int) {
        editOrRecordIdFlow.update {
            editOrRecordId
        }
    }

    private fun doWhenFinished(chooseTypeFinishState: ChooseTypeFinishState, popBackStack: (() -> Unit)) {
        viewModelScope.launch {
            if (editOrRecordIdFlow.value == -1) {
                databaseRepo.insertRecord(chooseTypeFinishState.toRecordEntity())
                popBackStack()
            } else {
                databaseRepo.updateRecord(chooseTypeFinishState.toRecordEntity(recordId = editOrRecordIdFlow.value))
                popBackStack()
            }
        }
    }
}