package com.jeckonly.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.composable.background.ColorAndTextBackground
import com.jeckonly.designsystem.composable.background.CurvedBottomByCubicShape
import com.jeckonly.home.ui.HomeCard
import com.jeckonly.home.ui.HomeHeader
import com.jeckonly.home.ui.state.HomeHeaderUI
import com.jeckonly.home.ui.state.HomeRecordCardUI

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.primary

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    val records: List<HomeRecordCardUI> by viewModel.recordsThisMonth.collectAsState()
    val homeHeaderUI: HomeHeaderUI by viewModel.homeHeaderUIState.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onActivityResume()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })
    HomeScreen(
        records,
        homeHeaderUI,
        viewModel::addLocalDateByMonth,
        viewModel::minusLocalDateByMonth
    )
}

@Composable
fun HomeScreen(
    records: List<HomeRecordCardUI>,
    homeHeaderUI: HomeHeaderUI,
    addMonth: () -> Unit,
    minusMonth: () -> Unit,
) {
    Column(modifier = Mdf.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        HomeScreenHeader(
            homeHeaderUI = homeHeaderUI,
            whenClickBack = minusMonth,
            whenClickAhead = addMonth,
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Mdf
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(10.dp)),
            content = {
                items(count = records.size) { index: Int ->
                    HomeCard(homeRecordCardUI = records[index], modifier = Mdf.fillMaxWidth())
                    Spacer(modifier = Modifier.height(20.dp))
                    if (index == records.size - 1) {
                        Spacer(modifier = Mdf.height(60.dp))
                    }
                }
            }
        )
    }
}

@Composable
fun HomeScreenHeader(
    homeHeaderUI: HomeHeaderUI,
    whenClickBack: () -> Unit,
    whenClickAhead: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (background, card) = createRefs()

        ColorAndTextBackground(
            color = MaterialTheme.colorScheme.primary,
            shape = CurvedBottomByCubicShape(),
            title = stringResource(id = R.string.overview),
            hint = stringResource(id = R.string.overview_hint),
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(background) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

        HomeHeader(
            homeHeaderUI = homeHeaderUI,
            whenClickBack = whenClickBack,
            whenClickAhead = whenClickAhead,
            modifier = Mdf
                .fillMaxWidth(0.85f)
                .wrapContentHeight()
                .constrainAs(card) {
                    start.linkTo(background.start)
                    end.linkTo(background.end)
                    top.linkTo(background.bottom, margin = (-80).dp)
                }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewHomeScreenHeader() {
    Box(modifier = Mdf.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        HomeScreenHeader(
            HomeHeaderUI(
                monthYearText = "January 2020",
                totalExpense = "3.8",
                totalIncome = "2.8",
                totalBalance = "1"
            ),
            whenClickBack = {},
            whenClickAhead = {},
        )
    }
}