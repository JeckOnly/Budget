package com.jeckonly.designsystem.composable.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun BgtNavigationBar(
    onClickHome: () -> Unit,
    onClickChart: () -> Unit,
    onClickMore: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(topStartPercent = 25, topEndPercent = 25),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(
            modifier = Mdf.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Mdf.fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                BgtNavigationBarItem(
                    selected = false,
                    onClick = onClickHome,
                    id = R.drawable.home,
                )

                BgtNavigationBarItem(
                    selected = false,
                    onClick = onClickChart,
                    id = R.drawable.line_chart,
                )

                BgtNavigationBarItem(
                    selected = false,
                    onClick = onClickMore,
                    id = R.drawable.more,
                )
            }
        }
    }
}

@Composable
fun BgtBottomBar(
    onClickHome: () -> Unit,
    onClickChart: () -> Unit,
    onClickMore: () -> Unit,
    onClickPlus: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (navigationBar, plus) = createRefs()
        BgtNavigationBar(
            onClickHome = onClickHome,
            onClickChart = onClickChart,
            onClickMore = onClickMore,
            modifier = Mdf.constrainAs(navigationBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.matchParent
                height = Dimension.wrapContent
            }
        )
        PlusButton(
            onClick = onClickPlus,
            modifier = Mdf.constrainAs(plus) {
                top.linkTo(navigationBar.top)
                bottom.linkTo(navigationBar.top)
                linkTo(navigationBar.start, navigationBar.end, bias = 0.8f)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewBgtBottomBar() {
    Box(
        modifier = Mdf
            .fillMaxWidth()
            .height(500.dp)
            .background(color = Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        BgtBottomBar(
            {},
            {},
            {},
            {}
        )
    }
}



