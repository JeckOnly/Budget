package com.jeckonly.chart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeckonly.chart.ui.state.ChartData
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.noIndicationClickable

@Composable
fun LeaderBoardArea(chartData: ChartData, expandOthers: Boolean, expand: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        if (chartData.theOtherLeaderBoardSpecificItem == null) {
            // 项数小于等于5个
            chartData.leaderBoardItemUIList.forEach {
                LeaderBoardItem(
                    leaderBoardItemUI = it,
                    modifier = Mdf
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                Spacer(modifier = Mdf.height(15.dp))
            }
        } else {
            // 有大于5个的项
            for (i in 0..3) {
                LeaderBoardItem(
                    leaderBoardItemUI = chartData.leaderBoardItemUIList[i],
                    modifier = Mdf
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
                Spacer(modifier = Mdf.height(15.dp))
            }
            if (!expandOthers) {
                LeaderBoardItem(
                    leaderBoardItemUI = chartData.theOtherLeaderBoardSpecificItem,
                    modifier = Mdf
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .noIndicationClickable {
                            expand()
                        }
                )
                Spacer(modifier = Mdf.height(15.dp))
            } else {
                for (i in 4 until chartData.leaderBoardItemUIList.size) {
                    LeaderBoardItem(
                        leaderBoardItemUI = chartData.leaderBoardItemUIList[i],
                        modifier = Mdf
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                    Spacer(modifier = Mdf.height(15.dp))
                }
            }
        }
    }
}