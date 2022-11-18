package com.jeckonly.util

import androidx.annotation.StringDef
import java.text.SimpleDateFormat
import java.util.*


/**
 * NOTE 这个工具类的时区使用默认时区，就不管是GMT还是UTC了。用户可以在自己手机的设置里面更改，会反映到程序中。
 */
object MyTimeUtil {

    // Time Format
    const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    const val FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
    const val FORMAT_YYYY_MM = "yyyy-MM"
    const val FORMAT_YYYY = "yyyy"
    const val FORMAT_HH_MM = "HH:mm"
    const val FORMAT_HH_MM_SS = "HH:mm:ss"
    const val FORMAT_MM_SS = "mm:ss"
    const val FORMAT_MM_DD_HH_MM = "MM-dd HH:mm"
    const val FORMAT_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss"
    const val FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"
    const val FORMAT_YYYY2MM2DD = "yyyy.MM.dd"
    const val FORMAT_YYYY2MM2DD_HH_MM = "yyyy.MM.dd HH:mm"
    const val FORMAT_MMCDDC_HH_MM = "MM月dd日 HH:mm"
    const val FORMAT_MMCDDC = "MM月dd日"
    const val FORMAT_YYYYCMMCDDC = "yyyy年MM月dd日"

    /**
     * the pattern describing the date and time format
     */
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
        FORMAT_YYYY_MM_DD,
        FORMAT_YYYY_MM_DD_HH_MM_SS,
        FORMAT_YYYY_MM,
        FORMAT_YYYY,
        FORMAT_HH_MM,
        FORMAT_HH_MM_SS,
        FORMAT_MM_SS,
        FORMAT_MM_DD_HH_MM,
        FORMAT_MM_DD_HH_MM_SS,
        FORMAT_YYYY_MM_DD_HH_MM,
        FORMAT_YYYY2MM2DD,
        FORMAT_YYYY2MM2DD_HH_MM,
        FORMAT_MMCDDC_HH_MM,
        FORMAT_MMCDDC,
        FORMAT_YYYYCMMCDDC,
    )
    annotation class TimeFormatString

    private val currentLocal: Locale
        get() {
            return Locale.getDefault()
        }

    private val currentTimeZone: TimeZone
        get() {
            return TimeZone.getDefault()
        }

    /**
     * 格式化字符串模板和格式化器的映射，防止同一个模板不断地构造新的不必要的格式化器对象。
     */
    private val dateFormatMap: MutableMap<String, SimpleDateFormat> = mutableMapOf()

    val currentTimeMillis: Long
        get() {
            return System.currentTimeMillis()
        }

    /**
     * 针对给定模板拿一个格式化器。
     *
     * fixme 当用户更改系统的时区的时候，原来存放的格式化器应无效。
     */
    private fun getSimpleDateFormatByPattern(
        @TimeFormatString dateFormatPattern: String,
        locale: Locale = currentLocal,
        timeZone: TimeZone = currentTimeZone
    ): SimpleDateFormat {
        if (dateFormatMap.containsKey(dateFormatPattern)) {
            return dateFormatMap[dateFormatPattern]!!
        } else {
            val newFormatter =  SimpleDateFormat(dateFormatPattern, locale).apply {
                this.timeZone = timeZone
            }
            dateFormatMap[dateFormatPattern] = newFormatter
            return dateFormatMap[dateFormatPattern]!!
        }
    }

    /**
     * 给一个时间戳，返回格式化后的字符串对象
     */
    fun timeStampToFormattedString(timeStamp: Long, @TimeFormatString dateFormatPattern: String): String {
        if (timeStamp < 0) throw IllegalStateException("时间戳为负数")

        val formatter = getSimpleDateFormatByPattern(dateFormatPattern = dateFormatPattern)
        return formatter.format(Date(timeStamp))
    }

    fun getYearMonthDayWeekDay(timeStamp: Long): IntArray {
        val calendar = Calendar.getInstance(currentTimeZone, currentLocal)
        calendar.timeInMillis = timeStamp
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val d = calendar.get(Calendar.DATE)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        return IntArray(3).apply {
            this[0] = y
            this[1] = m
            this[2] = d
        }
    }
}