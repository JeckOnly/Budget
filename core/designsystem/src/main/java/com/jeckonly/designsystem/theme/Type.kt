package com.jeckonly.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.designsystem.Mdf

// Material 3 typography
val budgetTypography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewTypography() {
    Column() {
        Text(text = "你好吗，世界, headlineLarge", style = budgetTypography.headlineLarge)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, headlineMedium", style = budgetTypography.headlineMedium)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, headlineSmall", style = budgetTypography.headlineSmall)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, titleLarge", style = budgetTypography.titleLarge)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, titleMedium", style = budgetTypography.titleMedium)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, titleSmall", style = budgetTypography.titleSmall)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, bodyLarge", style = budgetTypography.bodyLarge)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, bodyMedium", style = budgetTypography.bodyMedium)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, bodySmall", style = budgetTypography.bodySmall)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, labelLarge", style = budgetTypography.labelLarge)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, labelMedium", style = budgetTypography.labelMedium)
        Spacer(modifier = Mdf.height(10.dp))
        Text(text = "你好吗，世界, labelSmall", style = budgetTypography.labelSmall)
        Spacer(modifier = Mdf.height(10.dp))
    }
}