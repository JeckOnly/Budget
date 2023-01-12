package com.jeckonly.chart

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.chart.ui.state.*
import com.jeckonly.core_data.common.repo.interface_.DatabaseRepo
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.R
import com.jeckonly.util.FormatNumberUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import timber.log.Timber
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject

private const val START_DEGREE = -90.0
private const val END_DEGREE = 270.0

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
    private val databaseRepo: DatabaseRepo
) : ViewModel() {

    private var onResumeCount = 0

    var expandOthers by mutableStateOf(false)
        private set

    /**
     * 用户选择的N年N月，默认是当前年当前月
     */
    private val _localDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())

    /**
     * 当前页面选择的是消费还是收入
     */
    private val _expenseOrIncome: MutableStateFlow<ExpenseOrIncome> = MutableStateFlow(
        ExpenseOrIncome.Expense
    )

    private val _chartHeaderUI: StateFlow<ChartHeaderUI> = _localDate.map {
        val totalExpense = databaseRepo.getTotalNUmberByYearAndMonthAndExpenseOrIncome(it.year, it.monthValue, ExpenseOrIncome.Expense)
        val totalIncome = databaseRepo.getTotalNUmberByYearAndMonthAndExpenseOrIncome(it.year, it.monthValue, ExpenseOrIncome.Income)
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

    private val _chartData: StateFlow<ChartData> = combine(_localDate, _expenseOrIncome) { localDate, expenseOrIncome ->
        // 更新折叠的状态
        expandOthers = false
        // 表格数据
        getChartData(localDate, expenseOrIncome)
    }.stateIn(
        scope = viewModelScope.plus(Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChartData()
    )

    private suspend fun getChartData(
        localDate: LocalDate,
        expenseOrIncome: ExpenseOrIncome
    ): ChartData {
        // 从数据库获得基本数据
        val data =
            databaseRepo.getTypeAndTotalNumberByYearAndMonthAndExpenseOrIncome(
                year = localDate.year,
                month = localDate.monthValue,
                expenseOrIncome = expenseOrIncome
            )
        Timber.d(data.toString())

        // 总金额
        val totalNumber = databaseRepo.getTotalNUmberByYearAndMonthAndExpenseOrIncome(
            year = localDate.year,
            month = localDate.monthValue,
            expenseOrIncome = expenseOrIncome
        )
        val leaderBoardItemUIList = mutableListOf<LeaderBoardItemUI>()
        val pieChartSegmentUIList = mutableListOf<PieChartSegmentUI>()
        var theOtherLeaderBoardSpecificItem: LeaderBoardItemUI? = null
        var order = 1
        var startDegreeNow = START_DEGREE
        var theFirstFourTotal = 0.0

        data.forEach { nameNumberIconId ->
            val percent: Double = nameNumberIconId.number / totalNumber
            if (order < 5) {
                // 取前4个
                val pieChartSegmentUI = PieChartSegmentUI(
                    typeName = nameNumberIconId.typeName,
                    colorOrder = order,
                    startDegree = startDegreeNow,
                    sweepDegree = percent * 360,
                )
                startDegreeNow += pieChartSegmentUI.sweepDegree
                theFirstFourTotal += nameNumberIconId.number
                pieChartSegmentUIList.add(pieChartSegmentUI)
            }
            if (order == 5) {
                // 假如有第5个
                val pieChartSegmentUI = PieChartSegmentUI(
                    typeName =
                    if (data.size > 5)
                        app.getString(R.string.type_others)// 还有2个以上
                    else
                        nameNumberIconId.typeName,// 假如刚刚好5个
                    colorOrder = order,
                    startDegree = startDegreeNow,
                    sweepDegree = END_DEGREE - startDegreeNow,// 使用完剩下的角度
                )
                pieChartSegmentUIList.add(pieChartSegmentUI)
            }
            leaderBoardItemUIList.add(
                LeaderBoardItemUI(
                    typeName = nameNumberIconId.typeName,
                    iconId = nameNumberIconId.iconId,
                    percent = percent,
                    number = nameNumberIconId.number,
                    colorOrder = order,
                    expenseOrIncome = expenseOrIncome
                )
            )
            order += 1
        }
        if (data.size > 5) {
            // 在列表中需要一个“其他”项
            theOtherLeaderBoardSpecificItem = LeaderBoardItemUI(
                typeName = app.getString(R.string.type_others),
                iconId = R.drawable.other_item,
                percent = 1 - theFirstFourTotal / totalNumber,
                number = totalNumber - theFirstFourTotal,
                colorOrder = 5,
                expenseOrIncome = expenseOrIncome
            )
        }
        return ChartData(
            leaderBoardItemUIList = leaderBoardItemUIList,
            theOtherLeaderBoardSpecificItem = theOtherLeaderBoardSpecificItem,
            pieChartSegmentUIList = pieChartSegmentUIList
        )
    }

    val chartScreenState: StateFlow<ChartScreenState> =
        combine(_chartHeaderUI, _expenseOrIncome, _chartData) { chartHeaderUI, expenseOrIncome, chartData ->
            ChartScreenState(
                expenseOrIncome = expenseOrIncome,
                chartHeaderUI = chartHeaderUI,
                chartData = chartData
            )
        }.stateIn(
            scope = viewModelScope.plus(Dispatchers.Default),
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChartScreenState(
                expenseOrIncome = ExpenseOrIncome.Expense,
                chartHeaderUI = ChartHeaderUI(),
                chartData = ChartData(),
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

    fun expandOthers() {
        expandOthers = true
    }

    fun onScreenResume() {
        if (onResumeCount > 0) {
            // 不是第一次进入，而是在其他activity回来
            _localDate.update {
                if (it.dayOfMonth >= 15) LocalDate.of(it.year, it.monthValue, 14) else LocalDate.of(
                    it.year,
                    it.monthValue,
                    16
                )
            }
        }
        onResumeCount += 1
    }
}