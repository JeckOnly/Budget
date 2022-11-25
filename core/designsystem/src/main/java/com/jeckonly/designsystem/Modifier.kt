package com.jeckonly.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

typealias Mdf = Modifier

fun Modifier.noIndicationClickable(
    enabled: Boolean = true,
    onClick: () -> Unit
) = composed {
    this.clickable(
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null,
        onClick = onClick
    )
}