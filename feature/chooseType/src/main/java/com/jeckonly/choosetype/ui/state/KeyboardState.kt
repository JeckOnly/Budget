package com.jeckonly.choosetype.ui.state

import android.app.Application
import androidx.compose.runtime.*
import com.jeckonly.designsystem.R
import java.text.DecimalFormat
import kotlin.math.roundToInt


private const val NUMBER_COUNT_BEFORE_POINT = 8
private const val NUMBER_COUNT_AFTER_POINT = 2

@Stable
class KeyboardState(private val app: Application) {


    /**
     * 时间戳
     */
    private var timeStamp: Long = 0

    /**
     * 格式化数字，使6.00显示为6
     */
    private val decimalFormat = DecimalFormat("#.##")

    private val doneText = app.getString(R.string.done)

    val numberButton0 = ButtonType.NumberButton("0")
    val numberButton1 = ButtonType.NumberButton("1")
    val numberButton2 = ButtonType.NumberButton("2")
    val numberButton3 = ButtonType.NumberButton("3")
    val numberButton4 = ButtonType.NumberButton("4")
    val numberButton5 = ButtonType.NumberButton("5")
    val numberButton6 = ButtonType.NumberButton("6")
    val numberButton7 = ButtonType.NumberButton("7")
    val numberButton8 = ButtonType.NumberButton("8")
    val numberButton9 = ButtonType.NumberButton("9")

    val plus = ButtonType.OperatorButtonType.Plus()
    val minus = ButtonType.OperatorButtonType.Minus()

    val point = ButtonType.Point()

    val delete = ButtonType.Delete(R.drawable.keyboard_delete)

    val finish = ButtonType.Finish(mutableStateOf(doneText))

    val dateButtonType = ButtonType.DateButtonType(R.drawable.calendar, app.getString(R.string.today))

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
     * 展示的字符串
     */
    val showText: String by derivedStateOf {
        if (number1.value != "" && operator != null && number2.value != "") {
            finish.text.value = "="
        } else {
            finish.text.value = doneText
        }
        val temp: String = number1.value + (operator?.text ?: "") + number2.value
        if (temp != "") temp else "0"
    }

    /**
     * 备注
     */
    val remark: MutableState<String> = mutableStateOf("")

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
                    // TODO 判断值是否为0，不为0就写入记录
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
                    number1.value = decimalFormat.format(oldSum)
                    number2.value = ""
                    operator = null
                }
            }
            is ButtonType.DateButtonType -> {
                // TODO打开日期选择器
            }
        }
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
            number1.value = decimalFormat.format(oldSum)
            number2.value = ""
            operator = buttonType
        }
    }

    fun cleanState() {
        timeStamp = 0
        number1.value = ""
        operator = null
        number2.value = ""
        hasAddPoint1 = false
        hasAddPoint2 = false
        remark.value = ""
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
    class NumberButton(val number: String) : ButtonType

    class Point(val text: String = ".") : ButtonType

    class Delete(val iconId: Int) : ButtonType

    class Finish(val text: MutableState<String>) : ButtonType

    class DateButtonType(val iconId: Int, val text: String) : ButtonType

    sealed class OperatorButtonType(val text: String) : ButtonType {

        class Plus : OperatorButtonType("+")

        class Minus : OperatorButtonType("-")
    }
}

fun main() {
    val decimalFormat = DecimalFormat("#.##")
    println(decimalFormat.format(15882.08))

    val d = 7.99
    println(d.compareTo(d.roundToInt()))
}