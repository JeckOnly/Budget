package com.jeckonly.chart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.chart.ui.state.ChartHeaderUI
import com.jeckonly.chart.ui.state.ChartScreenState
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.util.FormatNumberUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    private var onResumeCount = 0

    /**
     * 用户选择的N年N月，默认是当前年当前月
     */
    private val _localDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())

    private val _expenseOrIncome: MutableStateFlow<ExpenseOrIncome> = MutableStateFlow(
        ExpenseOrIncome.Expense
    )

    private val _chartHeaderUI: StateFlow<ChartHeaderUI> = _localDate.map {
        val totalExpense = databaseRepo.getTotalExpenseByYearAndMonth(it.year, it.monthValue)
        val totalIncome = databaseRepo.getTotalIncomeByYearAndMonth(it.year, it.monthValue)
        val totalBalance = totalIncome - totalExpense
        val monthYearText =
            "${it.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())} ${it.year}"
        ChartHeaderUI(
            monthYearText = monthYearText,
            totalExpense = FormatNumberUtil.format(totalExpense),
            totalIncome = FormatNumberUtil.format(totalIncome),
            totalBalance = FormatNumberUtil.format(totalBalance)
        )
    }.stateIn(
        scope = viewModelScope.plus(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChartHeaderUI()
    )

    val chartScreenState: StateFlow<ChartScreenState> = combine(_chartHeaderUI, _expenseOrIncome) { chartHeaderUI, expenseOrIncome ->
        ChartScreenState(
            expenseOrIncome = expenseOrIncome,
            chartHeaderUI = chartHeaderUI
        )
    }.stateIn(
        scope = viewModelScope.plus(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChartScreenState(
            expenseOrIncome = ExpenseOrIncome.Expense,
            chartHeaderUI = ChartHeaderUI()
        )
    )


    fun minusLocalDateByMonth(monthsToSubtract: Long = 1) {
        _localDate.update {
            it.minusMonths(monthsToSubtract)
        }
    }

    fun addLocalDateByMonth(monthsToAdd: Long = 1) {
        _localDate.update {
            it.plusMonths(monthsToAdd)
        }
    }

    fun updateExpenseOrIncome(expenseOrIncome: ExpenseOrIncome) {
        _expenseOrIncome.update {
            expenseOrIncome
        }
    }


    fun onActivityResume() {
       // TODO 刷新数据
    }
}