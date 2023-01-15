package com.jeckonly.recorddetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable
import com.jeckonly.recorddetail.ui.state.RecordDetailUIState


private const val HEADER_HEIGHT = 55

@Composable
fun RecordDetailRoute(
    recordId: Int,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecordDetailViewModel = hiltViewModel()
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

    LaunchedEffect(key1 = Unit, block = {
        viewModel.initViewModel(recordId)
    })

    val recordDetailUIState = viewModel.recordDetailUIStateFlow.collectAsState()

    RecordDetailScreen(
        recordDetailUIState.value,
        popBackStack = popBackStack,
        onDelete = {
            viewModel.onDelete(recordId) {
                popBackStack()
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun RecordDetailScreen(
    recordDetailUIState: RecordDetailUIState,
    popBackStack: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier) {
        Column(modifier = Mdf.fillMaxSize()) {
            Box(
                modifier = Mdf
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    painter = painterResource(id = com.jeckonly.designsystem.R.drawable.arrow_left),
                    contentDescription = null,
                    modifier = Mdf
                        .align(
                            Alignment.TopStart
                        )
                        .padding(horizontal = 15.dp, vertical = 15.dp)
                        .size(25.dp)
                        .noIndicationClickable {
                            popBackStack()
                        }
                )
                Column(
                    Mdf
                        .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Mdf
                            .padding(top = 10.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            )
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = recordDetailUIState.iconId),
                            contentDescription = null,
                            modifier = Mdf
                                .size(35.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Text(
                        text = recordDetailUIState.typeName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        modifier = Mdf.padding(top = 10.dp, bottom = 10.dp)
                    )
                }
            }

            // items
            RecordDetailItem(
                title = stringResource(id = com.jeckonly.designsystem.R.string.type),
                content = recordDetailUIState.expenseOrIncome
            )
            RecordDetailItem(
                title = stringResource(id = com.jeckonly.designsystem.R.string.amount),
                content = recordDetailUIState.showNumber
            )
            RecordDetailItem(
                title = stringResource(id = com.jeckonly.designsystem.R.string.date),
                content = recordDetailUIState.showDate
            )
            RecordDetailItem(
                title = stringResource(id = com.jeckonly.designsystem.R.string.remark),
                content = recordDetailUIState.remark
            )
        }
        Row(
            modifier = Mdf
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Mdf
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = com.jeckonly.designsystem.R.string.edit),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Mdf
                        .padding(vertical = 20.dp)
                )
            }
            Box(
                modifier = Mdf
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.errorContainer)
                    .clickable {
                        showDeleteDialog = true
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = com.jeckonly.designsystem.R.string.delete),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Mdf
                        .padding(vertical = 20.dp)
                )
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.sure),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Mdf.noIndicationClickable {
                        showDeleteDialog = false
                        onDelete()
                    }
                )
            },
            dismissButton = {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Mdf.noIndicationClickable {
                        showDeleteDialog = false
                    }
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Mdf.size(30.dp)
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.warn),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.delete_record_hint),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            shape = RoundedCornerShape(10.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        )
    }
}

@Composable
fun RecordDetailItem(title: String, content: String) {
    Box(modifier = Mdf.fillMaxWidth()) {
        Row(modifier = Mdf.fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 1,
                modifier = Mdf.padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 15.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Mdf
                    .padding(top = 20.dp, bottom = 20.dp, end = 8.dp)
                    .weight(1f)
            )
        }
        Divider(
            modifier = Mdf
                .padding(start = 20.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(), thickness = 0.5.dp, color = MaterialTheme.colorScheme.outline
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewRecordDetailScreen() {
    RecordDetailScreen(
        recordDetailUIState = RecordDetailUIState(
            iconId = com.jeckonly.designsystem.R.drawable.category_e_beauty_stroke,
            typeName = "衣服"
        ),
        {},
        {},
        modifier = Mdf.fillMaxSize()
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewRecordDetailItem() {
    RecordDetailItem(
        title = "uiuiui",
        content = "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
    )
}