package com.jeckonly.choosetype.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.core_model.entity.helper.ExpenseOrIncome
import com.jeckonly.core_model.ui.ChooseTypeTypeUI
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

private const val ICON_SIZE = 35

@Composable
fun TypeItem(typeUI: ChooseTypeTypeUI, onClick: () -> Unit, modifier: Modifier = Modifier) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
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
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Mdf.scale(scaleAnimated.value)) {
            Box(
                modifier = Mdf
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            // 通过不断改变state的true或false来开启动画
                            animateStartHelper++
                            onClick()
                        }
                    )
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = typeUI.iconId),
                    contentDescription = typeUI.typeName,
                    modifier = Mdf
                        .size(ICON_SIZE.dp),
                    tint = contentColorFor(
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
        }

        Text(
            text = typeUI.typeName,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColorFor(
                backgroundColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Mdf.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewTypeItem() {
    TypeItem(
        typeUI = ChooseTypeTypeUI(
            iconId = R.drawable.eat,
            typeName = "餐饮",
            order = 0,
            expenseOrIncome = ExpenseOrIncome.Expense
        ),
        onClick = {

        }
    )
}