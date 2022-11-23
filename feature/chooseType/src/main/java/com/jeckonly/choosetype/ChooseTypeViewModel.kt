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
            expenseTypeList = expenseTypeUIList.sortedBy { it.order },
            incomeTypeList = incomeTypeUIList.sortedBy { it.order }
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
        iconId = R.drawable.category_e_catering_stroke,
        order = 0,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.snack),
        iconId = R.drawable.category_e_snack_stroke,
        order = 1,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.fruit),
        iconId = R.drawable.category_e_fruite_stroke,
        order = 2,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.shopping),
        iconId = R.drawable.category_e_shopping_stroke,
        order = 3,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.daily_use),
        iconId = R.drawable.category_e_commodity_stroke,
        order = 4,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.transportation),
        iconId = R.drawable.category_e_traffic_stroke,
        order = 5,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.vegetable),
        iconId = R.drawable.category_e_vegetable_stroke,
        order = 6,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.sport),
        iconId = R.drawable.category_e_sport_stroke,
        order = 7,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.contract),
        iconId = R.drawable.category_e_communicate_stroke,
        order = 8,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.clothes),
        iconId = R.drawable.category_e_dress_stroke,
        order = 9,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.cosmetic),
        iconId = R.drawable.category_e_beauty_stroke,
        order = 10,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.housing),
        iconId = R.drawable.category_e_house_stroke,
        order = 11,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.at_home),
        iconId = R.drawable.category_e_home_stroke,
        order = 12,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.kids),
        iconId = R.drawable.category_e_child_stroke,
        order = 13,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.elder),
        iconId = R.drawable.category_e_elder_stroke,
        order = 14,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.social),
        iconId = R.drawable.category_e_social_stroke,
        order = 15,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.trip),
        iconId = R.drawable.category_e_travel_stroke,
        order = 16,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.alcohol),
        iconId = R.drawable.category_e_smoke_stroke,
        order = 17,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.digital),
        iconId = R.drawable.category_e_digital_stroke,
        order = 18,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.car),
        iconId = R.drawable.category_e_car_stroke,
        order = 19,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.healing),
        iconId = R.drawable.category_e_medical_stroke,
        order = 20,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.books),
        iconId = R.drawable.category_e_books_stroke,
        order = 21,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.learn),
        iconId = R.drawable.category_e_study_stroke,
        order = 22,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.gift_money),
        iconId = R.drawable.category_e_money_stroke,
        order = 23,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.gift),
        iconId = R.drawable.category_e_gift_stroke,
        order = 24,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.office),
        iconId = R.drawable.category_e_office_stroke,
        order = 25,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.lottery),
        iconId = R.drawable.category_e_lottery_stroke,
        order = 26,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.express),
        iconId = R.drawable.category_e_express_stroke,
        order = 27,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.repair),
        iconId = R.drawable.category_e_repair_stroke,
        order = 28,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.entertainment),
        iconId = R.drawable.category_e_entertainmente_stroke,
        order = 29,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.relatives),
        iconId = R.drawable.category_e_friend_stroke,
        order = 30,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.pet),
        iconId = R.drawable.category_e_pet_stroke,
        order = 31,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.donate),
        iconId = R.drawable.category_e_donate_stroke,
        order = 32,
        expenseOrIncome = ExpenseOrIncome.Expense,
        isCustomise = false,
        shouldShow = true
    ),

    // ----
    TypeEntity(
        name = app.getString(R.string.salary),
        iconId = R.drawable.category_i_wage_stroke,
        order = 0,
        expenseOrIncome = ExpenseOrIncome.Income,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.part_time),
        iconId = R.drawable.category_i_parttimework_stroke,
        order = 1,
        expenseOrIncome = ExpenseOrIncome.Income,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.money_gift_2),
        iconId = R.drawable.category_i_money_stroke,
        order = 2,
        expenseOrIncome = ExpenseOrIncome.Income,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.other),
        iconId = R.drawable.category_i_other_stroke,
        order = 3,
        expenseOrIncome = ExpenseOrIncome.Income,
        isCustomise = false,
        shouldShow = true
    ),

    TypeEntity(
        name = app.getString(R.string.money_management),
        iconId = R.drawable.category_i_finance_stroke,
        order = 4,
        expenseOrIncome = ExpenseOrIncome.Income,
        isCustomise = false,
        shouldShow = true
    ),
)
