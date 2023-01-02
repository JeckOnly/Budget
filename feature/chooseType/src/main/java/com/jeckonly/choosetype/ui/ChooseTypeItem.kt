package com.jeckonly.choosetype.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.TypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable

private const val ICON_SIZE = 40

@Composable
fun ChooseTypeItem(
    typeUI: TypeUI,
    nowTypeUI: TypeUI?,
    onClick: (TypeUI) -> Unit,
    modifier: Modifier = Modifier
) {

    val backgroundColor =
        if (typeUI != nowTypeUI)
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        else
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)

    val iconTint = contentColorFor(
        backgroundColor = if (typeUI != nowTypeUI)
            MaterialTheme.colorScheme.secondaryContainer
        else
            MaterialTheme.colorScheme.primaryContainer
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TypeIcon(
            onClick,
            typeUI,
            backgroundColor,
            iconTint
        )

        Text(
            text = typeUI.typeName,
            style = MaterialTheme.typography.bodySmall,
            color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.surface),
            modifier = Mdf.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TypeIcon(
    onClick: (TypeUI) -> Unit,
    typeUI: TypeUI,
    backgroundColor: Color,
    iconTint: Color
) {
    var animateStartHelper by remember {
        mutableStateOf(0)
    }
    val scaleAnimated = remember {
        Animatable(1f)
    }
    LaunchedEffect(key1 = animateStartHelper, block = {
        if (animateStartHelper > 0) {
            scaleAnimated.animateTo(0.7f)
            scaleAnimated.animateTo(1f)
        }
    })
    Box(modifier = Mdf.scale(scaleAnimated.value)) {
        Box(
            modifier = Mdf
                .noIndicationClickable {
                    // 通过不断改变state的true或false来开启动画
                    animateStartHelper++
                    onClick(typeUI)
                }
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = typeUI.iconId),
                contentDescription = typeUI.typeName,
                modifier = Mdf
                    .size(ICON_SIZE.dp),
                tint = iconTint
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewTypeItem() {
    ChooseTypeItem(
        typeUI = TypeUI(
            typeId = 0,
            iconId = R.drawable.category_i_money_stroke,
            typeName = "餐饮",
            order = 0,
            expenseOrIncome = ExpenseOrIncome.Expense
        ),
        nowTypeUI = null,
        onClick = {

        }
    )
}