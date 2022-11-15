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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.navigation.BgtNavigationDestination

@Composable
fun BgtNavigationBar(
    onClickHome: () -> Unit,
    onClickChart: () -> Unit,
    onClickMore: () -> Unit,
    currentDestination: NavDestination?,
    homeDestination: BgtNavigationDestination,
    chartDestination: BgtNavigationDestination,
    moreDestination: BgtNavigationDestination,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(topStartPercent = 30, topEndPercent = 30),
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
                    selected = homeDestination.isSelected(currentDestination),
                    onClick = onClickHome,
                    id = R.drawable.home,
                )

                BgtNavigationBarItem(
                    selected = chartDestination.isSelected(currentDestination),
                    onClick = onClickChart,
                    id = R.drawable.line_chart,
                )

                BgtNavigationBarItem(
                    selected = moreDestination.isSelected(currentDestination),
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
    navController: NavHostController,
    homeDestination: BgtNavigationDestination,
    chartDestination: BgtNavigationDestination,
    moreDestination: BgtNavigationDestination,
    modifier: Modifier = Modifier
) {
    val currentDestination: NavDestination? = navController
        .currentBackStackEntryAsState().value?.destination

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
            currentDestination = currentDestination,
            homeDestination = homeDestination,
            chartDestination = chartDestination,
            moreDestination = moreDestination,
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


//@Preview(showBackground = true, backgroundColor = 0xffffff)
//@Composable
//fun PreviewBgtBottomBar() {
//    Box(
//        modifier = Mdf
//            .fillMaxWidth()
//            .height(500.dp)
//            .background(color = Color.White),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        BgtBottomBar(
//            {},
//            {},
//            {},
//            {}
//        )
//    }
//}



