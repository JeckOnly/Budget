package com.jeckonly.chart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.chart.ui.state.PieChartSegmentUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun PieChartArea(
    pieChartSegmentUIList: List<PieChartSegmentUI>,
    middleIconId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(modifier = Mdf.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            PieChart(
                pieChartSegmentUIList = pieChartSegmentUIList,
                middleIconId = middleIconId,
                modifier = Mdf
                    .weight(1f)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Mdf.width(15.dp))
            PieChartColorItemList(pieChartSegmentUIList = pieChartSegmentUIList)
            Spacer(modifier = Mdf.width(15.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewPieChartArea() {
    PieChartArea(
        listOf(
            PieChartSegmentUI(
                typeName = "清洁",
                color = Color.Blue,
                startDegree = -90.0,
                sweepDegree = 120.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                color = Color.Red,
                startDegree = 30.0,
                sweepDegree = 90.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                color = Color.Green,
                startDegree = 120.0,
                sweepDegree = 30.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                color = Color.Black,
                startDegree = 150.0,
                sweepDegree = 60.0
            ),
            PieChartSegmentUI(
                typeName = "清洁",
                color = Color.Yellow,
                startDegree = 210.0,
                sweepDegree = 60.0
            )
        ),
        R.drawable.category_e_home_stroke,
        Modifier
            .fillMaxWidth()
    )
}