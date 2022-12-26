package com.jeckonly.choosetype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.noIndicationClickable

private const val ICON_SIZE = 40

@Composable
fun UpdateTypeItem(
    iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val backgroundColor =
        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)


    val iconTint = MaterialTheme.colorScheme.onTertiaryContainer

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Mdf
                .noIndicationClickable(onClick = onClick)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Mdf
                    .size(ICON_SIZE.dp),
                tint = iconTint
            )
        }
    }
}