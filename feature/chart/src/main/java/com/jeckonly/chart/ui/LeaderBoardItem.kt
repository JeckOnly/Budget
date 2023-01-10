package com.jeckonly.chart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.chart.ui.state.LeaderBoardItemUI
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.util.FormatNumberUtil
import com.jeckonly.util.material_util.TonalUtil

@Composable
fun LeaderBoardItem(
    leaderBoardItemUI: LeaderBoardItemUI,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val numberText = remember(leaderBoardItemUI) {
        when (leaderBoardItemUI.expenseOrIncome) {
            ExpenseOrIncome.Expense -> {
                "-" + FormatNumberUtil.format(leaderBoardItemUI.number)
            }
            ExpenseOrIncome.Income -> {
                "+" + FormatNumberUtil.format(leaderBoardItemUI.number)
            }
        }
    }
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        ConstraintLayout(
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (icon, typeName, number, percent, progressBackground) = createRefs()

            Surface(
                color = Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), leaderBoardItemUI.colorOrder)).copy(alpha = 0.8f),
                modifier = Mdf.constrainAs(progressBackground) {
                    start.linkTo(parent.start)
                    linkTo(top = parent.top, bottom = parent.bottom)
                    width = Dimension.percent(leaderBoardItemUI.percent.toFloat())
                    height = Dimension.fillToConstraints
                },
                shape = RoundedCornerShape(10.dp)
            ) {

            }
            Surface(
                shadowElevation = 1.dp,
                shape = CircleShape,
                color = Color(TonalUtil.argbToTonalSpecific(primaryColor.toArgb(), leaderBoardItemUI.colorOrder)),
                modifier = Mdf
                    .constrainAs(icon) {
                        start.linkTo(parent.start, margin = 15.dp)
                        top.linkTo(parent.top, margin = 15.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }) {
                Icon(
                    painter = painterResource(id = leaderBoardItemUI.iconId),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                    modifier = Mdf
                        .size(40.dp)
                        .padding(4.dp)
                )
            }

            Text(
                text = leaderBoardItemUI.typeName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Mdf.constrainAs(typeName) {
                    start.linkTo(icon.end, 15.dp)
                    top.linkTo(icon.top)
                }
            )

            Text(
                text = "${FormatNumberUtil.format(leaderBoardItemUI.percent * 100)}%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Mdf.constrainAs(percent) {
                    start.linkTo(typeName.start)
                    top.linkTo(typeName.bottom)
                }
            )
            Text(
                text = numberText,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Mdf.constrainAs(number) {
                    end.linkTo(parent.end, 15.dp)
                    top.linkTo(typeName.top)
                    bottom.linkTo(percent.bottom)
                }
            )

        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewLeaderBoardItem() {
    LeaderBoardItem(
        leaderBoardItemUI = LeaderBoardItemUI(
            "Shopping",
            R.drawable.category_e_home_stroke,
            percent = 0.333,
            number = 855.55,
            colorOrder = 1,
            expenseOrIncome = ExpenseOrIncome.Expense
        ),
        modifier = Mdf
            .width(300.dp)
            .wrapContentHeight()
    )
}
