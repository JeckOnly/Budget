package com.jeckonly.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
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
        val themeItemList = listOf<ThemeItem>(
            ThemeItem(
                number = 0,
                color = MaroonFlush,
                colorDescribedText = "Maroon Flush"
            ),
            ThemeItem(
                number = 1,
                color = TallPoppy,
                colorDescribedText = "Tall Poppy"
            ),
            ThemeItem(
                number = 2,
                color = Fire,
                colorDescribedText = "Fire"
            ),
            ThemeItem(
                number = 3,
                color = Brown,
                colorDescribedText = "Brown"
            ),
            ThemeItem(
                number = 4,
                color = Cinnamon,
                colorDescribedText = "Cinnamon"
            ),
            ThemeItem(
                number = 5,
                color = Verdun,
                colorDescribedText = "Verdun"
            ),
            ThemeItem(
                number = 6,
                color = JapaneseLaurel,
                colorDescribedText = "Japanese Laurel"
            ),
            ThemeItem(
                number = 7,
                color = FunGreen,
                colorDescribedText = "Fun Green"
            ),
            ThemeItem(
                number = 8,
                color = TropicalRainForest,
                colorDescribedText = "Tropical Rain Forest"
            ),
            ThemeItem(
                number = 9,
                color = Mosque,
                colorDescribedText = "Mosque"
            ),
            ThemeItem(
                number = 10,
                color = BahamaBlue,
                colorDescribedText = "Bahama Blue"
            ),
            ThemeItem(
                number = 11,
                color = Endeavour,
                colorDescribedText = "Endeavour"
            ),
            ThemeItem(
                number = 12,
                color = RoyalBlue,
                colorDescribedText = "Royal Blue"
            ),
            ThemeItem(
                number = 13,
                color = PurpleHeart,
                colorDescribedText = "Purple Heart"
            ),
            ThemeItem(
                number = 14,
                color = PurpleHeart2,
                colorDescribedText = "Purple Heart 2"
            ),
            ThemeItem(
                number = 15,
                color = VioletEggplant,
                colorDescribedText = "Violet Eggplant"
            ),
        )
    }
}
