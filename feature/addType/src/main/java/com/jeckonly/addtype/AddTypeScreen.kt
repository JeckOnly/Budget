package com.jeckonly.addtype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.addtype.ui.EditArea
import com.jeckonly.addtype.ui.KindArea
import com.jeckonly.addtype.ui.state.AddTypeKind
import com.jeckonly.addtype.ui.state.AddTypeScreenState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable


private const val HEADER_HEIGHT = 55

@Composable
fun AddTypeRoute(
    typeId: Int,
    onClickBack: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTypeViewModel = hiltViewModel()
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val systemUiColor = MaterialTheme.colorScheme.secondaryContainer

    DisposableEffect(key1 = systemUiColor) {
        systemUiController.setStatusBarColor(
            color = systemUiColor,
            darkIcons = true
        )
        onDispose {

        }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.initViewModel(typeId)
    })

    AddTypeScreen(
        addTypeScreenState = viewModel.addTypeScreenState.collectAsState().value,
        onExpenseOrIncomeChanged = viewModel::onExpenseOrIncomeChanged,
        onNameChanged = viewModel::onNameChanged,
        onSave = {
            viewModel.onSave {
                onFinish()
            }
        },
        addTypeKinds = viewModel.addTypeKinds,
        onClickBack = onClickBack,
        onClickIcon = viewModel::onClickIcon,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun AddTypeScreen(
    addTypeScreenState: AddTypeScreenState,
    addTypeKinds: List<AddTypeKind>,
    onExpenseOrIncomeChanged: (ExpenseOrIncome) -> Unit,
    onNameChanged: (String) -> Unit,
    onSave: () -> Unit,
    onClickIcon: (Int) -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        Box {
            Column(modifier = modifier.fillMaxSize()) {
                AddTypeHeader(
                    onClickBack = onClickBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HEADER_HEIGHT.dp)
                )
                Spacer(modifier = Mdf.height(10.dp))
                KindArea(
                    nowChooseId = addTypeScreenState.iconId,
                    onClickIcon = onClickIcon,
                    kinds = addTypeKinds
                )
            }
            EditArea(
                addTypeScreenState = addTypeScreenState,
                onExpenseOrIncomeChanged = onExpenseOrIncomeChanged,
                onNameChanged = onNameChanged,
                onSave = onSave,
                modifier = Mdf.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun AddTypeHeader(onClickBack: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier.background(color = MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Mdf
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = null,
                modifier = Mdf
                    .padding(start = 15.dp)
                    .size(25.dp)
                    .align(Alignment.CenterStart)
                    .noIndicationClickable(onClick = onClickBack),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = stringResource(id = R.string.edit_type),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleMedium,
                modifier = Mdf.align(Alignment.Center),
            )

        }
    }
}