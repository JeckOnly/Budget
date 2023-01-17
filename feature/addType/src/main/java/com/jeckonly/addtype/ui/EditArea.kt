package com.jeckonly.addtype.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.addtype.ui.state.AddTypeScreenState
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditArea(
    addTypeScreenState: AddTypeScreenState,
    onExpenseOrIncomeChanged: (ExpenseOrIncome) -> Unit,
    onNameChanged: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {

    // icon每次选中新的都有动画
    val scaleAnimated = remember {
        Animatable(1f)
    }
    LaunchedEffect(key1 = addTypeScreenState.iconId, block = {
        scaleAnimated.animateTo(0.4f)
        scaleAnimated.animateTo(
            1f,
            animationSpec = SpringSpec(
                stiffness = Spring.StiffnessLow,
                dampingRatio = Spring.DampingRatioHighBouncy
            )
        )
    })
    val backgroundColor =
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)

    val iconTint = contentColorFor(
        MaterialTheme.colorScheme.primaryContainer
    )
    Surface(
        modifier = modifier
            .fillMaxWidth(), color = MaterialTheme.colorScheme.surface, shadowElevation = 4.dp
    ) {
        ConstraintLayout(
            modifier = Mdf
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (area1, area2) = createRefs()
            Box(modifier = Mdf
                .fillMaxWidth(0.7f)
                .padding(10.dp)
                .constrainAs(area1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    height = Dimension.wrapContent
                }) {
                Column(
                    modifier = Mdf
                        .fillMaxWidth()
                ) {
                    Row(modifier = Mdf.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Mdf.scale(scaleAnimated.value)) {
                            Box(
                                modifier = Mdf
                                    .background(
                                        color = backgroundColor, shape = CircleShape
                                    )
                                    .padding(5.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = addTypeScreenState.iconId),
                                    contentDescription = null,
                                    modifier = Mdf
                                        .size(35.dp),
                                    tint = iconTint
                                )
                            }
                        }
                        Spacer(modifier = Mdf.width(10.dp))
                        Text(
                            text = if (addTypeScreenState.expenseOrIncome is ExpenseOrIncome.Expense)
                                stringResource(id = R.string.expense) else stringResource(id = R.string.income),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Mdf.width(5.dp))
                        Icon(
                            painter = painterResource(R.drawable.switch_icon),
                            contentDescription = null,
                            modifier = Mdf
                                .size(20.dp)
                                .noIndicationClickable {
                                    if (addTypeScreenState.expenseOrIncome is ExpenseOrIncome.Expense)
                                        onExpenseOrIncomeChanged(ExpenseOrIncome.Income)
                                    else
                                        onExpenseOrIncomeChanged(ExpenseOrIncome.Expense)
                                },
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Box(
                        modifier = Mdf.padding(vertical = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        TextField(
                            value = addTypeScreenState.typeName,
                            onValueChange = onNameChanged,
                            textStyle = MaterialTheme.typography.bodyLarge,
                            singleLine = true,
                            label = {
                                Text(
                                    text = stringResource(id = R.string.name),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            modifier = Mdf.fillMaxWidth()
                        )
                    }
                }
            }

            // 右边的按钮
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Mdf
                    .fillMaxWidth(0.3f)
                    .noIndicationClickable(onClick = onSave)
                    .constrainAs(area2) {
                        end.linkTo(parent.end)
                        top.linkTo(area1.top)
                        bottom.linkTo(area1.bottom)
                        height = Dimension.fillToConstraints
                    }
            ) {
                Box(modifier = Mdf.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.save),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff, showSystemUi = true)
@Composable
fun PreviewEditArea() {
    EditArea(
        addTypeScreenState = AddTypeScreenState(),
        {},
        {},
        {},
        modifier = Modifier.fillMaxWidth()
    )
}