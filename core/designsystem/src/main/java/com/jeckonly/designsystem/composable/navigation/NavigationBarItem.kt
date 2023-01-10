package com.jeckonly.designsystem.composable.navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun BgtNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    id: Int,
    nameId: Int,
    modifier: Modifier = Modifier,
) {
    val scaleAnimated = remember {
        Animatable(1.2f)
    }
    LaunchedEffect(key1 = selected, block = {
        if (selected) {
            scaleAnimated.animateTo(1.4f)
            scaleAnimated.animateTo(1.2f)
        }
    })
    val color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            modifier = Mdf
                .size(20.dp)
                .scale(scaleAnimated.value)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClick()
                },
            tint = color
        )
        Spacer(modifier = Mdf.height(5.dp))
        Text(text = stringResource(id = nameId), fontWeight = FontWeight.Normal, fontSize = 10.sp, color = color)
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewBgtNavigationBarItem() {
    BgtNavigationBarItem(selected = false, onClick = {  }, id = R.drawable.home, nameId = R.string.home_bottom)
}