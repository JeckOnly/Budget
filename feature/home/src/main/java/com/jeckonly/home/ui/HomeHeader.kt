package com.jeckonly.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.composable.header.HeaderItem
import com.jeckonly.designsystem.noIndicationClickable
import com.jeckonly.home.ui.state.HomeHeaderUI

@Composable
fun HomeHeader(
    homeHeaderUI: HomeHeaderUI,
    whenClickBack: () -> Unit,
    whenClickAhead: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    Surface(color = backgroundColor, shape = RoundedCornerShape(10.dp), modifier = modifier, tonalElevation = 1.dp) {
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
                HeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.income),
                    itemValue = homeHeaderUI.totalIncome
                )
                HeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.expense),
                    itemValue = homeHeaderUI.totalExpense
                )
                HeaderItem(
                    textColor = contentColor,
                    itemName = stringResource(id = R.string.balance),
                    itemValue = homeHeaderUI.totalBalance
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewHeaderItem() {
    HeaderItem(
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

