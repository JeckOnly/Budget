package com.jeckonly.updatetype.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable

private const val ICON_SIZE = 30

@Composable
fun UpdateTypeItem(
    typeUI: TypeUI,
    onClickEdit: (Int) -> Unit,
    onClickDelete: (Int, Boolean, (Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        Row(modifier = Mdf.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = null,
                modifier = Mdf
                    .padding(start = 16.dp, end = 16.dp)
                    .size(20.dp)
                    .noIndicationClickable {
                        onClickDelete(typeUI.typeId, false) {
                            showDeleteDialog = true
                        }
                    },
                tint = MaterialTheme.colorScheme.error
            )
            Box(
                modifier = Mdf
                    .padding(vertical = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = typeUI.iconId),
                    contentDescription = typeUI.typeName,
                    modifier = Mdf
                        .size(ICON_SIZE.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Text(
                text = typeUI.typeName,
                style = MaterialTheme.typography.bodySmall,
                color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.surface),
                modifier = Mdf
                    .padding(start = 5.dp)
                    .weight(1f)
            )

            Icon(
                painter = painterResource(id = R.drawable.pencil),
                contentDescription = null,
                modifier = Mdf
                    .padding(start = 5.dp, end = 16.dp)
                    .size(20.dp)
                    .noIndicationClickable(onClick = {
                        onClickEdit(typeUI.typeId)
                    })
                ,
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
        Divider(
            modifier = Mdf
                .fillMaxWidth()
                .height(0.5.dp), color = MaterialTheme.colorScheme.outlineVariant
        )
    }

    AnimatedVisibility(visible = showDeleteDialog) {
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
                        onClickDelete(typeUI.typeId, true) {

                        }
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
                    text = stringResource(id = R.string.delete_hint),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            shape = RoundedCornerShape(10.dp),
            containerColor = MaterialTheme.colorScheme.surface,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewUpdateTypeItem() {
    UpdateTypeItem(
        typeUI = TypeUI(
            typeId = 0,
            iconId = R.drawable.category_i_money_stroke,
            typeName = "餐饮",
            order = 0,
            expenseOrIncome = ExpenseOrIncome.Expense
        ),
        onClickEdit = {

        },
        onClickDelete = { _, _, _ ->

        },
        modifier = Mdf
            .width(300.dp)
            .wrapContentHeight()
    )
}