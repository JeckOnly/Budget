package com.jeckonly.chart.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.chart.ui.state.PieChartSegmentUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.util.material_util.TonalUtil

@Composable
fun PieChart(
    pieChartSegmentUIList: List<PieChartSegmentUI>,
    middleIconId: Int,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val stokeWidth by remember {
            mutableStateOf(this.maxWidth / 9)
        }
        Box(
            modifier = Mdf
                .fillMaxSize()
                .padding(stokeWidth / 2)
                .padding(20.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // 画一个圆弧
                for (index in pieChartSegmentUIList.size - 1 downTo 0) {
                    val item = pieChartSegmentUIList[index]
                    drawArc(
                        brush = SolidColor(Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), item.colorOrder))),
                        startAngle = item.startDegree.toFloat(),
                        sweepAngle = item.sweepDegree.toFloat(),
                        useCenter = false,
                        topLeft = Offset.Zero,
                        size = this.size,
                        alpha = 1.0f,
                        style = Stroke(
                            width = stokeWidth.toPx(),
                            cap = StrokeCap.Butt,
                            join = StrokeJoin.Round
                        ),
                        colorFilter = null,
                        blendMode = DefaultBlendMode
                    )
                }
            }
        }
        Box(
            modifier = Mdf
                .background(
                    color = Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), pieChartSegmentUIList[0].colorOrder)).copy(0.09f),
                    shape = CircleShape
                )
                .padding(2.dp)){
            Icon(
                painter = painterResource(id = middleIconId),
                contentDescription = null,
                tint = Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), pieChartSegmentUIList[0].colorOrder)),
                modifier = Mdf.size(stokeWidth * 2)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewPieChart() {
    PieChart(
        listOf(
            PieChartSegmentUI(
                typeName = "清洁",
                colorOrder = 1,
                startDegree = -90.0,
                sweepDegree = 120.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                colorOrder = 2,
                startDegree = 30.0,
                sweepDegree = 90.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                colorOrder = 3,
                startDegree = 120.0,
                sweepDegree = 150.0
            )
        ),
        R.drawable.category_e_home_stroke,
        Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}