package com.jeckonly.choosetype.ui.state

import androidx.compose.runtime.*
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.R
import com.jeckonly.util.FormatNumberUtil
import java.text.DecimalFormat
import java.time.LocalDate
import kotlin.math.roundToInt


private const val NUMBER_COUNT_BEFORE_POINT = 8
private const val NUMBER_COUNT_AFTER_POINT = 2

@Stable
class KeyboardState(private val doWhenFinish: (ChooseTypeFinishState, (() -> Unit)) -> Unit) {


    // Button type
    private val numberButton0 = ButtonType.NumberButton("0")
    private val numberButton1 = ButtonType.NumberButton("1")
    private val numberButton2 = ButtonType.NumberButton("2")
    private val numberButton3 = ButtonType.NumberButton("3")
    private val numberButton4 = ButtonType.NumberButton("4")
    private val numberButton5 = ButtonType.NumberButton("5")
    private val numberButton6 = ButtonType.NumberButton("6")
    private val numberButton7 = ButtonType.NumberButton("7")
    private val numberButton8 = ButtonType.NumberButton("8")
    private val numberButton9 = ButtonType.NumberButton("9")

    private val plus = ButtonType.OperatorButtonType.Plus()
    private val minus = ButtonType.OperatorButtonType.Minus()
    private val point = ButtonType.Point()
    private val delete = ButtonType.Delete(R.drawable.keyboard_delete)

    fun numberButton0() = numberButton0
    fun numberButton1() = numberButton1
    fun numberButton2() = numberButton2
    fun numberButton3() = numberButton3
    fun numberButton4() = numberButton4
    fun numberButton5() = numberButton5
    fun numberButton6() = numberButton6
    fun numberButton7() = numberButton7
    fun numberButton8() = numberButton8
    fun numberButton9() = numberButton9

    fun plus() = plus
    fun minus() = minus
    fun point() = point
    fun delete() = delete
    fun finish(typeUI: TypeUI, popBackStack: (() -> Unit)) = ButtonType.Finish(typeUI, popBackStack)

    // state ---------------------------------------------------------------------------------------------------
    /**
     * 操作数1
     */
    private val number1: MutableState<String> = mutableStateOf("")

    /**
     * 操作符
     */
    private var operator: ButtonType.OperatorButtonType? by mutableStateOf(null)

    /**
     * 操作数2
     */
    private val number2: MutableState<String> = mutableStateOf("")

    /**
     * 当前操作数1是否已添加小数点
     */
    private var hasAddPoint1: Boolean = false

    /**
     * 当前操作数2是否已添加小数点
     */
    private var hasAddPoint2: Boolean = false

    /**
     * 是否可以显示完成字符串了（中间运算已结束）
     */
    val canShowDoneText: MutableState<Boolean> = mutableStateOf(true)

    /**
     * 展示的字符串
     */
    val showText: String by derivedStateOf {
        canShowDoneText.value = !(number1.value != "" && operator != null && number2.value != "")
        val temp: String = number1.value + (operator?.text ?: "") + number2.value
        if (temp != "") temp else "0"
    }

    /**
     * 备注
     */
    val remark: MutableState<String> = mutableStateOf("")

    /**
     * 用户选择的日期
     */
    var chooseLocalDate: LocalDate? by mutableStateOf(null)
        private set

    /**
     * 日历Button应展示的字符串
     */
    val calendarString: String by derivedStateOf {
        when (val date = chooseLocalDate) {
            null -> ""
            LocalDate.now() -> ""
            else ->
                "${date.year}/${date.monthValue}/${date.dayOfMonth}"

        }
    }

    fun onEvent(buttonType: ButtonType) {
        when (buttonType) {
            is ButtonType.NumberButton -> {
                if (number1.value == "") {
                    // 第一个操作数为空
                    doOnNumberType(number1, hasAddPoint1, buttonType)
                    return
                }
                if (number1.value != "" && operator == null) {
                    doOnNumberType(number1, hasAddPoint1, buttonType)
                    return
                }
                if (number1.value != "" && operator != null && number2.value == "") {
                    doOnNumberType(number2, hasAddPoint2, buttonType)
                    return
                }
                if (number1.value != "" && operator != null && number2.value != "") {
                    doOnNumberType(number2, hasAddPoint2, buttonType)
                    return
                }
            }

            is ButtonType.Point -> {
                if (number1.value == "") {
                    // 第一个操作数为空
                    doOnPointButtonType(number1, hasAddPoint1, 1, buttonType)
                    return
                }
                if (number1.value != "" && operator == null) {
                    doOnPointButtonType(number1, hasAddPoint1, 1, buttonType)
                    return
                }
                if (number1.value != "" && operator != null && number2.value == "") {
                    doOnPointButtonType(number2, hasAddPoint2, 2, buttonType)
                    return
                }
                if (number1.value != "" && operator != null && number2.value != "") {
                    doOnPointButtonType(number2, hasAddPoint2, 2, buttonType)
                    return
                }
            }

            is ButtonType.OperatorButtonType.Plus -> {
                doOnOperatorButtonType(buttonType)
            }
            is ButtonType.OperatorButtonType.Minus -> {
                doOnOperatorButtonType(buttonType)
            }
            is ButtonType.Delete -> {
                if (number1.value == "") {
                    // 第一个操作数为空
                    return
                }
                if (number1.value != "" && operator == null) {
                    if (number1.value.last() == '.') {
                        hasAddPoint1 = false
                    }
                    number1.value = number1.value.dropLast(1)
                    return
                }
                if (number1.value != "" && operator != null && number2.value == "") {
                    operator = null
                    return
                }
                if (number1.value != "" && operator != null && number2.value != "") {
                    if (number2.value.last() == '.') {
                        hasAddPoint2 = false
                    }
                    number2.value = number2.value.dropLast(1)
                    return
                }
            }
            is ButtonType.Finish -> {
                if (number1.value == "") {
                    // 第一个操作数为空
                    return
                }
                if (number1.value != "" && operator == null) {
                    val numberTemp = number1.value.toDouble()
                    if (numberTemp == 0.0) return

                    var year = 0
                    var month = 0
                    var dayOfMonth = 0
                    if (chooseLocalDate == null) {
                        val localDateNow = LocalDate.now()
                        year = localDateNow.year
                        month = localDateNow.monthValue
                        dayOfMonth = localDateNow.dayOfMonth
                    } else {
                        val temp = chooseLocalDate as LocalDate
                        year = temp.year
                        month = temp.monthValue
                        dayOfMonth = temp.dayOfMonth
                    }
                    doWhenFinish(
                        ChooseTypeFinishState(
                            year = year,
                            month = month,
                            dayOfMonth = dayOfMonth,
                            number = numberTemp,
                            typeId = buttonType.typeUI.typeId,
                            remark = remark.value
                        ),
                        buttonType.popBackStack
                    )
                    return
                }
                if (number1.value != "" && operator != null && number2.value == "") {
                    operator = null
                    return
                }
                if (number1.value != "" && operator != null && number2.value != "") {
                    val oldSum: Double = if (operator is ButtonType.OperatorButtonType.Plus) {
                        number1.value.toDouble() + number2.value.toDouble()
                    } else {
                        number1.value.toDouble() - number2.value.toDouble()
                    }
                    if (checkDoubleHasDecimal(oldSum)) {
                        hasAddPoint1 = true
                        hasAddPoint2 = false
                    } else {
                        hasAddPoint1 = false
                        hasAddPoint2 = false
                    }
                    number1.value = FormatNumberUtil.format(oldSum)
                    number2.value = ""
                    operator = null
                }
            }
        }
    }

    fun onDatePicked(localDate: LocalDate) {
        chooseLocalDate = localDate
    }

    private fun doOnPointButtonType(
        number: MutableState<String>,
        hasAddPoint: Boolean,
        oneOrTwo: Int,
        buttonType: ButtonType.Point
    ) {
        if (hasAddPoint) {
            // 如果已添加小数点
        } else {
            // 未添加小数点
            if (number.value == "") {
                // 小数点前没有任何数字
            } else {
                number.value = number.value + buttonType.text
                if (oneOrTwo == 1) hasAddPoint1 = true else hasAddPoint2 = true
            }
        }
    }

    private fun doOnNumberType(
        number: MutableState<String>,
        hasAddPoint: Boolean,
        buttonType: ButtonType.NumberButton
    ) {
        if (hasAddPoint) {
            // 已添加小数点
            val pointIndex = number.value.indexOfFirst { c: Char ->
                c == '.'
            }
            if (pointIndex == -1) throw IllegalStateException("没有小数点")

            if (number.value.length - 1 - pointIndex >= NUMBER_COUNT_AFTER_POINT) {
                // 已达到小数点后数字长度上限
            } else {
                // 添加数字
                number.value = number.value + buttonType.number
            }
        } else {
            // 未添加小数点
            if (number.value.length >= NUMBER_COUNT_BEFORE_POINT) {
                // 已达到小数点前数字长度上限
            } else {
                if (number.value == "0" && buttonType.number == "0") {
                    // 不能连续两个数字为0
                } else {
                    number.value = number.value + buttonType.number
                }
            }
        }
    }

    private fun doOnOperatorButtonType(buttonType: ButtonType.OperatorButtonType) {
        if (number1.value == "") {
            // 第一个操作数为空
            return
        }
        if (number1.value != "" && operator == null) {
            operator = buttonType
            return
        }
        if (number1.value != "" && operator != null && number2.value == "") {
            operator = buttonType
            return
        }
        if (number1.value != "" && operator != null && number2.value != "") {
            val oldSum: Double = if (operator is ButtonType.OperatorButtonType.Plus) {
                number1.value.toDouble() + number2.value.toDouble()
            } else {
                number1.value.toDouble() - number2.value.toDouble()
            }
            if (checkDoubleHasDecimal(oldSum)) {
                hasAddPoint1 = true
                hasAddPoint2 = false
            } else {
                hasAddPoint1 = false
                hasAddPoint2 = false
            }
            number1.value = FormatNumberUtil.format(oldSum)
            number2.value = ""
            operator = buttonType
        }
    }

    fun init(keyboardStateInit: KeyboardStateInit) {
        number1.value = FormatNumberUtil.format(keyboardStateInit.number)
        hasAddPoint1 = checkDoubleHasDecimal(keyboardStateInit.number)
        remark.value = keyboardStateInit.remark
        chooseLocalDate = keyboardStateInit.localDate
    }

    @Deprecated("没有必要，从导航图的栈中弹出之后ViewModel也消除，重新进入又是一个新的实例")
    fun cleanState() {
        number1.value = ""
        operator = null
        number2.value = ""
        hasAddPoint1 = false
        hasAddPoint2 = false
        remark.value = ""
        chooseLocalDate = null
    }
}

/**
 * 小数
 * @return true 表示[d]这个浮点数有小数部分
 */
fun checkDoubleHasDecimal(d: Double): Boolean {
    return d.compareTo(d.roundToInt()) != 0
}

sealed interface ButtonType {


    /**
     * @property number 0 <= number <= 9
     */
    @Stable
    class NumberButton(val number: String) : ButtonType

    @Stable
    class Point(val text: String = ".") : ButtonType

    @Stable
    class Delete(val iconId: Int) : ButtonType

    @Stable
    data class Finish(val typeUI: TypeUI, val popBackStack: () -> Unit): ButtonType

    sealed class OperatorButtonType(val text: String) : ButtonType {

        @Stable
        class Plus : OperatorButtonType("+")

        @Stable
        class Minus : OperatorButtonType("-")
    }
}

fun main() {
    val decimalFormat = DecimalFormat("#.##")
    println(decimalFormat.format(15882.08))

    val d = 7.99
    println(d.compareTo(d.roundToInt()))
}