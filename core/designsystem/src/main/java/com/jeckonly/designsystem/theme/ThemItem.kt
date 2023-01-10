package com.jeckonly.designsystem.theme

import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.theme.color.*

@Stable
data class ThemeItem(
    val number: Int,
    val color: Color,
    val colorDescribedText: String,
)

@Stable
data class BudgetColorTheme(
    val lightScheme: ColorScheme,
    val darkScheme: ColorScheme
) {
    companion object {
        @Stable
        fun chooseThemeByNumber(number: Int): BudgetColorTheme {
            return when (number) {
                -1 -> {
                    BudgetColorTheme(LightColorDefault, DarkColorsDefault)
                }
                0 -> {
                    BudgetColorTheme(LightColorsMaroonFlush, DarkColorsMaroonFlush)
                }
                1 -> {
                    BudgetColorTheme(LightColorsTallPoppy, DarkColorsTallPoppy)
                }
                2 -> {
                    BudgetColorTheme(LightColorsFire, DarkColorsFire)
                }
                3 -> {
                    BudgetColorTheme(LightColorsBrown, DarkColorsBrown)
                }
                4 -> {
                    BudgetColorTheme(LightColorsCinnamon, DarkColorsCinnamon)
                }
                5 -> {
                    BudgetColorTheme(LightColorsVerdun, DarkColorsVerdun)
                }
                6 -> {
                    BudgetColorTheme(LightColorsJapaneseLaurel, DarkColorsJapaneseLaurel)
                }
                7 -> {
                    BudgetColorTheme(LightColorsFunGreen, DarkColorsFunGreen)
                }
                8 -> {
                    BudgetColorTheme(LightColorsTropicalRainForest, DarkColorsTropicalRainForest)
                }
                9 -> {
                    BudgetColorTheme(LightColorsMosque, DarkColorsMosque)
                }
                10 -> {
                    BudgetColorTheme(LightColorBahamaBlue, DarkColorsBahamaBlue)
                }
                11 -> {
                    BudgetColorTheme(LightColorEndeavour, DarkColorsEndeavour)
                }
                12 -> {
                    BudgetColorTheme(LightColorRoyalBlue, LightColorRoyalBlue)
                }
                13 -> {
                    BudgetColorTheme(LightColorPurpleHeart, DarkColorsPurpleHeart)
                }
                14 -> {
                    BudgetColorTheme(LightColorPurpleHeart2, DarkColorsPurpleHeart2)
                }
                15 -> {
                    BudgetColorTheme(LightColorVioletEggplant, DarkColorsVioletEggplant)
                }
                else -> {
                    BudgetColorTheme(LightColorsMaroonFlush, DarkColorsMaroonFlush)
                }
            }
        }

        // 主题界面的列表元素
        fun getThemeItemList(context: Context) = listOf(
            ThemeItem(
                number = 0,
                color = MaroonFlush,
                colorDescribedText = context.getString(R.string.MaroonFlush)
            ),
            ThemeItem(
                number = 1,
                color = TallPoppy,
                colorDescribedText = context.getString(R.string.TallPoppy)
            ),
            ThemeItem(
                number = 2,
                color = Fire,
                colorDescribedText = context.getString(R.string.Fire)
            ),
            ThemeItem(
                number = 3,
                color = Brown,
                colorDescribedText = context.getString(R.string.Brown)
            ),
            ThemeItem(
                number = 4,
                color = Cinnamon,
                colorDescribedText = context.getString(R.string.Cinnamon)
            ),
            ThemeItem(
                number = 5,
                color = Verdun,
                colorDescribedText = context.getString(R.string.Verdun)
            ),
            ThemeItem(
                number = 6,
                color = JapaneseLaurel,
                colorDescribedText = context.getString(R.string.JapaneseLaurel)
            ),
            ThemeItem(
                number = 7,
                color = FunGreen,
                colorDescribedText = context.getString(R.string.FunGreen)
            ),
            ThemeItem(
                number = 8,
                color = TropicalRainForest,
                colorDescribedText = context.getString(R.string.TropicalRainForest)
            ),
            ThemeItem(
                number = 9,
                color = Mosque,
                colorDescribedText = context.getString(R.string.Mosque)
            ),
            ThemeItem(
                number = 10,
                color = BahamaBlue,
                colorDescribedText = context.getString(R.string.BahamaBlue)
            ),
            ThemeItem(
                number = 11,
                color = Endeavour,
                colorDescribedText = context.getString(R.string.Endeavour)
            ),
            ThemeItem(
                number = 12,
                color = RoyalBlue,
                colorDescribedText = context.getString(R.string.RoyalBlue)
            ),
            ThemeItem(
                number = 13,
                color = PurpleHeart,
                colorDescribedText = context.getString(R.string.PurpleHeart)
            ),
            ThemeItem(
                number = 14,
                color = PurpleHeart2,
                colorDescribedText = context.getString(R.string.PurpleHeart2)
            ),
            ThemeItem(
                number = 15,
                color = VioletEggplant,
                colorDescribedText = context.getString(R.string.VioletEggplant)
            ),
        )
    }
}
