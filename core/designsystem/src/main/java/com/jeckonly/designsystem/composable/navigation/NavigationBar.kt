package com.jeckonly.designsystem.composable.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.navigation.BgtBottomNavigationDestination

@Composable
fun BgtNavigationBar(
    onClickHome: () -> Unit,
    onClickChart: () -> Unit,
    onClickMore: () -> Unit,
    currentDestination: NavDestination?,
    homeDestination: BgtBottomNavigationDestination,
    chartDestination: BgtBottomNavigationDestination,
    moreDestination: BgtBottomNavigationDestination,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
) {
    Surface(
        color = containerColor,
        tonalElevation = 3.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
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
                    id = R.drawable.circle_chart,
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
    homeDestination: BgtBottomNavigationDestination,
    chartDestination: BgtBottomNavigationDestination,
    moreDestination: BgtBottomNavigationDestination,
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


