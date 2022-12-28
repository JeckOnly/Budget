package com.jeckonly.updatetype.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.updatetype.ui.state.UpdateTypeUiState
import org.burnoutcrew.reorderable.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UpdateTypePager(
    updateTypeUiState: UpdateTypeUiState,
    pagerState: PagerState,
    onDragEnd: ((newList: List<TypeUI>, startIndex: Int, endIndex: Int) -> (Unit)),
    goToAddOrEdit: (Int) -> Unit,
    onClickDelete: (Int, Boolean, (Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(count = 2, modifier = modifier, state = pagerState) { page ->
        if (updateTypeUiState.isLoading) {
            Box(
                modifier = Mdf
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiary)
            }
        } else {
            when (page) {
                0 -> {
                    UpdateTypePage(
                        expenseOrIncome = ExpenseOrIncome.Expense,
                        updateTypeUiState = updateTypeUiState,
                        onDragEnd = onDragEnd,
                        goToAddOrEdit = goToAddOrEdit,
                        onClickDelete = onClickDelete,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                1 -> {
                    UpdateTypePage(
                        expenseOrIncome = ExpenseOrIncome.Income,
                        updateTypeUiState = updateTypeUiState,
                        onDragEnd = onDragEnd,
                        goToAddOrEdit = goToAddOrEdit,
                        onClickDelete = onClickDelete,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun UpdateTypePage(
    expenseOrIncome: ExpenseOrIncome,
    updateTypeUiState: UpdateTypeUiState,
    onDragEnd: ((newList: List<TypeUI>, startIndex: Int, endIndex: Int) -> (Unit)),
    goToAddOrEdit: (Int) -> Unit,
    onClickDelete: (Int, Boolean, (Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        val data = remember(updateTypeUiState) {
            mutableStateOf(
                if (expenseOrIncome is ExpenseOrIncome.Expense) updateTypeUiState.expenseTypeList else updateTypeUiState.incomeTypeList
            )
        }
        // NOTE 因为data会更新引用，所以需要用[rememberUpdatedState]来让Composer知道引用更新
        val onMoveRemember = rememberUpdatedState(newValue = { from: Int, to: Int ->
            data.value = data.value.toMutableList().apply {
                add(to, removeAt(from))
            }
        })
        val onDragRemember = rememberUpdatedState(newValue = { startIndex: Int, endIndex: Int ->
            onDragEnd(data.value, startIndex, endIndex)
        })
        val canDragOverRemember = rememberUpdatedState(newValue = { draggedOverIndex: Int ->
            draggedOverIndex < data.value.size
        })

        val state = rememberReorderableLazyListState(
            onMove = { from, to ->
                onMoveRemember.value(from.index, to.index)
            },
            canDragOver = { draggedOver, _ ->
                canDragOverRemember.value(draggedOver.index)
            },
            onDragEnd = { startIndex: Int, endIndex: Int ->
                onDragRemember.value(startIndex, endIndex)
            }
        )
        LazyColumn(
            state = state.listState,
            modifier = Modifier
                .reorderable(state)
        ) {
            items(data.value, { it.typeId }) { item ->
                ReorderableItem(state, key = item.typeId) { isDragging ->
                    val elevation = animateDpAsState(if (isDragging) 1.dp else 0.dp)

                    Surface(
                        shadowElevation = elevation.value,
                        modifier = Mdf.detectReorderAfterLongPress(state)
                    ) {
                        UpdateTypeItem(
                            typeUI = item,
                            onClickEdit = goToAddOrEdit,
                            onClickDelete = onClickDelete,
                            modifier = Mdf.fillMaxWidth()
                        )
                    }
                }
            }
            item {
                Surface(
                    modifier = Mdf
                        .fillMaxWidth()
                        .height(80.dp)
                ) {

                }
            }
        }
    }
}
