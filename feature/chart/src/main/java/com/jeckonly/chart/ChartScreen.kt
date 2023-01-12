package com.jeckonly.chart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.chart.ui.ChartHeader
import com.jeckonly.chart.ui.LeaderBoardArea
import com.jeckonly.chart.ui.PieChartArea
import com.jeckonly.chart.ui.state.ChartScreenState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.composable.background.ColorAndTextBackground
import com.jeckonly.designsystem.composable.background.CurvedBottomByCubicShape

@Composable
fun ChartRoute(
    modifier: Modifier = Modifier,
    viewModel: ChartViewModel = hiltViewModel()
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

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onScreenResume()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    val chartScreenState = viewModel.chartScreenState.collectAsState()
    ChartScreen(
        chartScreenState = chartScreenState.value,
        expandOthers = viewModel.expandOthers,
        minusMonth = viewModel::minusLocalDateByMonth,
        addMonth = viewModel::addLocalDateByMonth,
        expand = viewModel::expandOthers,
        updateExpenseOrIncome = viewModel::updateExpenseOrIncome
    )
}

@Composable
fun ChartScreen(
    chartScreenState: ChartScreenState,
    expandOthers: Boolean,
    minusMonth: () -> Unit,
    addMonth: () -> Unit,
    expand: () -> Unit,
    updateExpenseOrIncome: (ExpenseOrIncome) -> Unit,
) {
    Column(modifier = Mdf.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        ChartScreenHeader(
            chartScreenState = chartScreenState,
            whenClickBack = minusMonth,
            whenClickAhead = addMonth,
            updateExpenseOrIncome = updateExpenseOrIncome,
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Mdf
                .verticalScroll(state = rememberScrollState())
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (chartScreenState.chartData.leaderBoardItemUIList.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.no_chart_data),
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Mdf.padding(top = 30.dp)
                )
            } else {
                PieChartArea(
                    pieChartSegmentUIList = chartScreenState.chartData.pieChartSegmentUIList,
                    middleIconId = chartScreenState.chartData.leaderBoardItemUIList[0].iconId
                )
                Spacer(modifier = Mdf.height(20.dp))
                LeaderBoardArea(
                    chartData = chartScreenState.chartData,
                    expandOthers = expandOthers,
                    expand = expand
                )
                Spacer(modifier = Mdf.height(60.dp))
            }
        }
    }
}

@Composable
fun ChartScreenHeader(
    chartScreenState: ChartScreenState,
    whenClickBack: () -> Unit,
    whenClickAhead: () -> Unit,
    updateExpenseOrIncome: (ExpenseOrIncome) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (background, card) = createRefs()

        ColorAndTextBackground(
            color = MaterialTheme.colorScheme.primary,
            shape = CurvedBottomByCubicShape(),
            title = stringResource(id = R.string.distribution),
            hint = stringResource(id = R.string.distribution_hint),
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(background) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

        ChartHeader(
            chartScreenState = chartScreenState,
            whenClickBack = whenClickBack,
            whenClickAhead = whenClickAhead,
            updateExpenseOrIncome = updateExpenseOrIncome,
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