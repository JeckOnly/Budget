package com.jeckonly.changetheme.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.noIndicationClickable

@Composable
fun ThemeItem(
    selectedNumber: Int,
    number: Int,
    text: String,
    color: Color,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var animateStartHelper by remember {
        mutableStateOf(0)
    }
    val scaleAnimated = remember {
        Animatable(1f)
    }
    LaunchedEffect(key1 = animateStartHelper, block = {
        if (animateStartHelper > 0) {
            scaleAnimated.animateTo(0.8f)
            scaleAnimated.animateTo(1f, animationSpec = SpringSpec(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy))
        }
    })
    Box(modifier = Mdf.scale(scaleAnimated.value)) {
        Surface(
            modifier = modifier.noIndicationClickable {
                animateStartHelper++
                onClick(number)
            },
            shape = RoundedCornerShape(20),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = if (selectedNumber == number) 1.dp else 0.dp,
            border = if (selectedNumber == number) BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.primary
            ) else BorderStroke(0.dp, Color.Transparent)
        ) {
            Row(modifier = Mdf.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedNumber == number, onClick = {
                        onClick(number)
                    },
                    enabled = false,
                    colors = RadioButtonDefaults.colors(
                        disabledSelectedColor = MaterialTheme.colorScheme.primary,
                        disabledUnselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start,
                    modifier = Mdf
                        .weight(1f)
                        .zIndex(1f)
                )
                Box(
                    modifier = Mdf
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .height(30.dp)
                        .width(40.dp)
                        .background(color = color, shape = RoundedCornerShape(20))
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewThemeItem() {
    Column {
        ThemeItem(
            selectedNumber = 0,
            number = 1,
            text = "Dfdfd dfdfd",
            color = Color.Green,
            onClick = {

            },
            modifier = Mdf.fillMaxWidth()
        )
        ThemeItem(
            selectedNumber = 0,
            number = 1,
            text = "Dfdfd dfdfd",
            color = Color.Green,
            onClick = {

            },
            modifier = Mdf.fillMaxWidth()
        )
        ThemeItem(
            selectedNumber = 1,
            number = 1,
            text = "Dfdfd dfdfd",
            color = Color.Green,
            onClick = {

            },
            modifier = Mdf.fillMaxWidth()
        )
    }
}