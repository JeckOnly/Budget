package com.jeckonly.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.chart.ui.state.PieChartSegmentUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.util.material_util.TonalUtil


@Composable
fun PieChartColorItem(
    pieChartSegmentUI: PieChartSegmentUI,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Mdf
                .background(Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), pieChartSegmentUI.colorOrder)), CircleShape)
                .size(15.dp)
        )
        Spacer(modifier = Mdf.width(10.dp))
        Text(
            text = pieChartSegmentUI.typeName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PieChartColorItemList(
    pieChartSegmentUIList: List<PieChartSegmentUI>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.requiredWidthIn(1.dp, 100.dp)) {
        Spacer(modifier = Mdf.height(20.dp))
        pieChartSegmentUIList.forEach {
            PieChartColorItem(pieChartSegmentUI = it)
            Spacer(modifier = Mdf.height(20.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewPieChartItem() {
    PieChartColorItem(
        pieChartSegmentUI = PieChartSegmentUI(
            typeName = "清洁",
            colorOrder = 1,
            startDegree = -90.0,
            sweepDegree = 120.0
        ), modifier = Mdf.width(100.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewPieChartColorItemList() {
    PieChartColorItemList(
        listOf(
            PieChartSegmentUI(
                typeName = "清洁",
                colorOrder = 1,
                startDegree = -90.0,
                sweepDegree = 120.0
            ),
            PieChartSegmentUI(
                typeName = "清洁11",
                colorOrder = 2,
                startDegree = 30.0,
                sweepDegree = 90.0
            ),
            PieChartSegmentUI(
                typeName = "清洁1111",
                colorOrder = 3,
                startDegree = 120.0,
                sweepDegree = 150.0
            )
        )
    )
}