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
                else -> {
                    BudgetColorTheme(LightColorsMaroonFlush, DarkColorsMaroonFlush)
                }
            }
        }

        val themeItemList = listOf<ThemeItem>(
            ThemeItem(
                number = 0,
                color = MaroonFlush,
                colorDescribedText = "MaroonFlush"
            ),
            ThemeItem(
                number = 1,
                color = TallPoppy,
                colorDescribedText = "TallPoppy"
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
                colorDescribedText = "JapaneseLaurel"
            ),
        )
    }
}
