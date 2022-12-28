package com.jeckonly.addtype.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.noIndicationClickable

@Composable
fun IconType(
    nowChooseId:Int,
    id: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var animateStartHelper by remember {
        mutableStateOf(0)
    }
    val scaleAnimated = remember {
        Animatable(1f)
    }
    LaunchedEffect(key1 = animateStartHelper, block = {
        if (animateStartHelper > 0) {
            scaleAnimated.animateTo(0.4f)
            scaleAnimated.animateTo(1f, animationSpec = SpringSpec(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy))
        }
    })
    val backgroundColor =
        if (id != nowChooseId)
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        else
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)

    val iconTint = contentColorFor(
        backgroundColor = if (id != nowChooseId)
            MaterialTheme.colorScheme.secondaryContainer
        else
            MaterialTheme.colorScheme.primaryContainer
    )
    Box(modifier = modifier.scale(scaleAnimated.value)) {
        Box(
            modifier = Mdf
                .noIndicationClickable {
                    // 通过不断改变state的true或false来开启动画
                    animateStartHelper++
                    onClick(id)
                }
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = id),
                contentDescription = null,
                modifier = Mdf
                    .size(35.dp),
                tint = iconTint
            )
        }
    }
}