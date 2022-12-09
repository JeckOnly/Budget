package com.jeckonly.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable
import com.jeckonly.home.ui.state.HomeHeaderUI

@Composable
fun HomeHeader(
    homeHeaderUI: HomeHeaderUI,
    whenClickBack: () -> Unit,
    whenClickAhead: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    Surface(color = backgroundColor, shape = RoundedCornerShape(10.dp), modifier = modifier) {
        Column(
            modifier = Mdf
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Mdf.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Mdf
                        .rotate(90f)
                        .align(Alignment.CenterStart)
                        .noIndicationClickable(onClick = whenClickBack)
                    ,
                    tint = contentColor
                )
                Text(
                    text = homeHeaderUI.monthYearText,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Mdf.align(Alignment.Center),
                    color = contentColor
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Mdf
                        .rotate(-90f)
                        .align(Alignment.CenterEnd)
                        .noIndicationClickable(onClick = whenClickAhead)
                    ,
                    tint = contentColor
                )
            }
            Spacer(modifier = Mdf.height(20.dp))
            Divider(
                modifier = Mdf.fillMaxWidth(0.9f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(modifier = Mdf.height(20.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Mdf.fillMaxWidth(0.9f)) {
                HomeHeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.income),
                    itemValue = homeHeaderUI.totalIncome
                )
                HomeHeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.expense),
                    itemValue = homeHeaderUI.totalExpense
                )
                HomeHeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.balance),
                    itemValue = homeHeaderUI.totalBalance
                )
            }
        }
    }
}

@Composable
fun HomeHeaderItem(
    textColor: Color,
    itemName: String,
    itemValue: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = itemName, style = MaterialTheme.typography.bodySmall, color = textColor)
            Spacer(modifier = Mdf.height(5.dp))
            Text(
                text = itemValue,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewHomeHeaderItem() {
    HomeHeaderItem(
        textColor = MaterialTheme.colorScheme.onSurface,
        itemName = "Income",
        itemValue = "3.800"
    )
}


@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewHomeHeader() {
    Box(modifier = Mdf.fillMaxWidth(), contentAlignment = Alignment.Center) {
        HomeHeader(
            HomeHeaderUI(
                monthYearText = "January 2020",
                totalExpense = "3.8",
                totalIncome = "2.8",
                totalBalance = "1"
            ),
            whenClickBack = {},
            whenClickAhead = {},
            modifier = Mdf
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
        )
    }
}

