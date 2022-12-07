package com.jeckonly.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.HomeRecordItemUI
import com.jeckonly.home.ui.HomeRecordCardUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import timber.log.Timber
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    /**
     * 格式化数字，使6.00显示为6
     */
    private val decimalFormat = DecimalFormat("#.##")

    /**
     * 用户选择的N年N月，默认是当前年当前月
     */
    private val _localDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())

    /**
     * 这个月所有的记录
      */
    val recordsThisMonth: StateFlow<List<HomeRecordCardUI>> = _localDate.map {
        getHomeRecordCardUIs(databaseRepo.getAllHomeRecordItemUIByYearAndMonth(it.year, it.monthValue))
    }.stateIn(
        scope = viewModelScope.plus(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /**
     * 这个月所有的支出
     */
    val totalExpenseThisMonth: StateFlow<String> = _localDate.map {
        decimalFormat.format(databaseRepo.getTotalExpenseByYearAndMonth(it.year, it.monthValue))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    /**
     * 这个月所有的收入
     */
    val totalIncomeThisMonth: StateFlow<String> = _localDate.map {
        decimalFormat.format(databaseRepo.getTotalIncomeByYearAndMonth(it.year, it.monthValue))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    /**
     * 这个月的平衡
     */
    val totalBalanceThisMonth: StateFlow<String> = combine(totalExpenseThisMonth, totalIncomeThisMonth) {totalExpense, totalIncome ->
        decimalFormat.format(totalIncome.toDouble() - totalExpense.toDouble())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
    )

    /**
     * 展示的UI: 月份 年
     */
    val dateText: StateFlow<String> = _localDate.map {
        Timber.tag("Jeck").d("getDisplayName")
        "${it.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())} ${it.year}"
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ""
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
}

fun getHomeRecordCardUIs(homeRecordItemUIs: List<HomeRecordItemUI>): List<HomeRecordCardUI> {
    if (homeRecordItemUIs.isEmpty()) return emptyList()

    val result: MutableList<HomeRecordCardUI> = mutableListOf()

    val firstItem = homeRecordItemUIs.first()

    // 临时变量，以第一个元素初始化第一个分组
    var mutableHomeRecordItemUIs: MutableList<HomeRecordItemUI> = mutableListOf()
    var nowTotalBalance: Double = 0.0
    var nowLocalDate: LocalDate = LocalDate.of(firstItem.year, firstItem.month, firstItem.dayOfMonth)

    fun doInSameGroup(homeRecordItemUI: HomeRecordItemUI) {
        mutableHomeRecordItemUIs.add(homeRecordItemUI)
        when(homeRecordItemUI.expenseOrIncome) {
            is ExpenseOrIncome.Expense -> {
                nowTotalBalance -= homeRecordItemUI.number
            }
            is ExpenseOrIncome.Income -> {
                nowTotalBalance += homeRecordItemUI.number
            }
        }
    }

    homeRecordItemUIs.forEach { homeRecordItemUI ->
        if (homeRecordItemUI.dayOfMonth == nowLocalDate.dayOfMonth) {
            // 代表是同一个分组
            doInSameGroup(homeRecordItemUI)
        } else {
            // 开启一个新的分组

            // 先保存
            result.add(
                HomeRecordCardUI(
                    localDate = nowLocalDate,
                    totalBalance = nowTotalBalance,
                    recordList = mutableHomeRecordItemUIs
                )
            )
            // 创建新变量
            mutableHomeRecordItemUIs = mutableListOf()
            nowTotalBalance = 0.0
            nowLocalDate = LocalDate.of(homeRecordItemUI.year, homeRecordItemUI.month, homeRecordItemUI.dayOfMonth)

            // 在当前分组操作
            doInSameGroup(homeRecordItemUI)
        }
    }

    // 结束之后保存最后一个分组
    result.add(
        HomeRecordCardUI(
            localDate = nowLocalDate,
            totalBalance = nowTotalBalance,
            recordList = mutableHomeRecordItemUIs
        )
    )

    return result
}

fun main() {
    val date = LocalDate.now()
    println(date.month.name)
    println(date.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()))
    println(date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()))
}