package com.jeckonly.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.RecordEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    val recordsThisMonth: StateFlow<List<RecordEntity>> = _localDate.map {
        databaseRepo.getAllRecordByYearAndMonth(it.year, it.monthValue)
    }.stateIn(
        scope = viewModelScope,
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

fun main() {
    val date = LocalDate.now()
    println(date.month.name)
    println(date.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()))
    println(date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()))
}