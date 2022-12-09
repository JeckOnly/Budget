package com.jeckonly.designsystem.composable.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.designsystem.Mdf

class CurvedBottomShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width: Float = size.width
        val height: Float = size.height
        val rectHalfWidth: Float = width / 7.5f
        val path = Path().apply {
            lineTo(0f, height - rectHalfWidth)
            lineTo(width, height - rectHalfWidth)
            lineTo(width, 0f)
            lineTo(0f, 0f)
            addArc(
                oval = Rect(
                    left = 0f,
                    top = height - 2 * rectHalfWidth,
                    right = width,
                    bottom = height
                ), startAngleDegrees = 0f, sweepAngleDegrees = 180f
            )
        }
        return Outline.Generic(path)
    }
}

class CurvedBottomByCubicShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width: Float = size.width
        val height: Float = size.height
        val unitLength: Float = size.width / 16
        val cubicHeightToBottom: Float = unitLength * 2
        val path = Path().apply {
            lineTo(0f, height - cubicHeightToBottom)
            lineTo(width, height - cubicHeightToBottom)
            lineTo(width, 0f)
            lineTo(0f, 0f)
            moveTo(0f, height - cubicHeightToBottom)
            cubicTo(x3 = width, y3 = height - cubicHeightToBottom, x1 = 4 * unitLength, y1 = height, x2 = 12 * unitLength, y2 = height)
        }
        return Outline.Generic(path)
    }
}

@Composable
fun ColorAndTextBackground(
    color: Color,
    title: String,
    hint: String,
    modifier: Modifier = Modifier,
    shape: Shape = CurvedBottomByCubicShape()
) {
    Surface(modifier = modifier, shape = shape, color = color) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Mdf.padding(top = 55.dp, start = 20.dp, end = 20.dp, bottom = 100.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                color = contentColorFor(backgroundColor = color),
            )
            Spacer(modifier = Mdf.height(10.dp))
            Text(
                text = hint,
                style = MaterialTheme.typography.bodySmall,
                color = contentColorFor(backgroundColor = color),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewBezierBackground() {
    Box(
        modifier = Mdf
            .background(Color.Red, shape = CurvedBottomShape())
            .size(100.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewColorAndTextBackground1() {
    ColorAndTextBackground(
        color = MaterialTheme.colorScheme.primary,
        shape = CurvedBottomShape(),
        title = "OVERVIEW",
        hint = "Here is your list of your transactions",
        modifier = Mdf.fillMaxWidth().wrapContentHeight()
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewColorAndTextBackground2() {
    ColorAndTextBackground(
        color = MaterialTheme.colorScheme.primary,
        shape = CurvedBottomByCubicShape(),
        title = "OVERVIEW",
        hint = "Here is your list of your transactions",
        modifier = Mdf.fillMaxWidth().wrapContentHeight()
    )
}