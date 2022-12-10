package com.jeckonly.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.HomeRecordItemUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.home.ui.state.HomeRecordCardUI
import java.time.LocalDate

private const val ICON_SIZE = 30

@Composable
fun HomeCardRecordItem(
    homeRecordItemUI: HomeRecordItemUI,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    val numberText by remember(homeRecordItemUI) {
        when (homeRecordItemUI.expenseOrIncome) {
            ExpenseOrIncome.Expense -> {
                mutableStateOf("-" + homeRecordItemUI.showNumber)
            }
            ExpenseOrIncome.Income -> {
                mutableStateOf("+" + homeRecordItemUI.showNumber)
            }
        }
    }

    Surface(color = backgroundColor, contentColor = contentColor, modifier = modifier, tonalElevation = 1.dp) {
        ConstraintLayout(
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (iconRefs, nameAndNumberRefs) = createRefs()
            Box(
                modifier = Mdf
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .padding(2.dp)
                    .constrainAs(iconRefs) {
                        start.linkTo(parent.start, 10.dp)
                        top.linkTo(parent.top, 14.dp)
                        bottom.linkTo(parent.bottom, 14.dp)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = homeRecordItemUI.iconId),
                    contentDescription = null,
                    modifier = Mdf
                        .size(ICON_SIZE.dp),
                    tint = contentColor
                )
            }

            Row(modifier = Mdf.constrainAs(nameAndNumberRefs) {
                end.linkTo(parent.end, 10.dp)
                top.linkTo(parent.top, 14.dp)
                bottom.linkTo(parent.bottom, 14.dp)
                start.linkTo(iconRefs.end, 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }
            ) {
                Text(
                    text = homeRecordItemUI.showText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = contentColor,
                    maxLines = 1,
                    modifier = Mdf.weight(1f),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = numberText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = contentColor,
                )
            }
        }
    }
}

@Composable
fun HomeCard(homeRecordCardUI: HomeRecordCardUI, modifier: Modifier = Modifier) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(10.dp),
        contentColor = contentColor,
        modifier = modifier,
        tonalElevation = 1.dp
    ) {
        Column {
            Row(
                modifier = Mdf
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 10.dp)
            ) {
                Text(
                    text = homeRecordCardUI.showDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor,
                    maxLines = 1,
                    modifier = Mdf.weight(1f),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = homeRecordCardUI.showBalance,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor
                )
            }
            for (homeRecordItemUI in homeRecordCardUI.recordList) {
                Divider(
                    modifier = Mdf.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                HomeCardRecordItem(
                    homeRecordItemUI = homeRecordItemUI,
                    modifier = Mdf.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewHomeCardRecordItem() {
    HomeCardRecordItem(
        homeRecordItemUI = HomeRecordItemUI(
            year = 2000,
            month = 12,
            dayOfMonth = 25,
            number = 300.0,
            expenseOrIncome = ExpenseOrIncome.Expense,
            iconId = R.drawable.category_e_traffic_stroke,
            typeName = "交通",
            remark = ""
        ),
        modifier = Mdf.fillMaxWidth()
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewHomeCard() {
    HomeCard(
        homeRecordCardUI = HomeRecordCardUI(
            localDate = LocalDate.of(2000, 12, 25),
            255.0,
            listOf(
                HomeRecordItemUI(
                    year = 2000,
                    month = 12,
                    dayOfMonth = 25,
                    number = 300.0,
                    expenseOrIncome = ExpenseOrIncome.Expense,
                    iconId = R.drawable.category_e_traffic_stroke,
                    typeName = "交通",
                    remark = ""
                ),
                HomeRecordItemUI(
                    year = 2000,
                    month = 12,
                    dayOfMonth = 25,
                    number = 300.0,
                    expenseOrIncome = ExpenseOrIncome.Expense,
                    iconId = R.drawable.category_e_traffic_stroke,
                    typeName = "交通",
                    remark = ""
                ),
                HomeRecordItemUI(
                    year = 2000,
                    month = 12,
                    dayOfMonth = 25,
                    number = 300.0,
                    expenseOrIncome = ExpenseOrIncome.Expense,
                    iconId = R.drawable.category_e_traffic_stroke,
                    typeName = "交通",
                    remark = ""
                ),

            )
        )
    )
}