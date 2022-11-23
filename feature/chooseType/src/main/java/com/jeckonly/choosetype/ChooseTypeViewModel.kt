package com.jeckonly.choosetype

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.choosetype.ui.state.ChooseTypeUiState
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.TypeEntity
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import com.jeckonly.designsystem.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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
    private val expenseTypeFlow: StateFlow<List<ChooseTypeTypeUI>> =
        databaseRepo.getAllExpenseTypeAndShouldShown().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    /**
     * 类型为收入的列表
     */
    private val incomeTypeFlow: StateFlow<List<ChooseTypeTypeUI>> =
        databaseRepo.getAllIncomeTypeAndShouldShown().stateIn(
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
            expenseTypeList = expenseTypeUIList,
            incomeTypeList = incomeTypeUIList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChooseTypeUiState()
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
}


fun getInitTypeEntity(app: Application) = listOf<TypeEntity>(
    TypeEntity(
        name = app.getString(R.string.repast),
        iconId = R.drawable.eat,
        order = 0,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.snack),
        iconId = R.drawable.snack,
        order = 1,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.fruit),
        iconId = R.drawable.fruit,
        order = 2,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.shopping),
        iconId = R.drawable.shopping,
        order = 3,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.daily_use),
        iconId = R.drawable.dailything,
        order = 4,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.transportation),
        iconId = R.drawable.transport,
        order = 5,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.vegetable),
        iconId = R.drawable.vegetable,
        order = 6,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.sport),
        iconId = R.drawable.sport,
        order = 7,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.contract),
        iconId = R.drawable.contract,
        order = 8,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.clothes),
        iconId = R.drawable.clothes,
        order = 9,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.cosmetic),
        iconId = R.drawable.cosmetic,
        order = 10,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.housing),
        iconId = R.drawable.live,
        order = 11,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.at_home),
        iconId = R.drawable.sofa,
        order = 12,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.kids),
        iconId = R.drawable.children,
        order = 13,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.elder),
        iconId = R.drawable.oldman,
        order = 14,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.social),
        iconId = R.drawable.social,
        order = 15,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.trip),
        iconId = R.drawable.trip,
        order = 16,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.alcohol),
        iconId = R.drawable.smoke,
        order = 17,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.digital),
        iconId = R.drawable.digital,
        order = 18,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.car),
        iconId = R.drawable.car,
        order = 19,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.healing),
        iconId = R.drawable.healing,
        order = 20,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.books),
        iconId = R.drawable.book,
        order = 21,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.learn),
        iconId = R.drawable.learn,
        order = 22,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.gift_money),
        iconId = R.drawable.giftmoney,
        order = 23,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.gift),
        iconId = R.drawable.gift,
        order = 24,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.office),
        iconId = R.drawable.work,
        order = 25,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.lottery),
        iconId = R.drawable.lottery,
        order = 26,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.express),
        iconId = R.drawable.express,
        order = 27,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.repair),
        iconId = R.drawable.repair,
        order = 28,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.entertainment),
        iconId = R.drawable.happy,
        order = 29,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.relatives),
        iconId = R.drawable.familyfriend,
        order = 30,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.pet),
        iconId = R.drawable.pet,
        order = 31,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.donate),
        iconId = R.drawable.heart,
        order = 32,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    )
)
