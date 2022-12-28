package com.jeckonly.addtype.ui.state

import androidx.compose.runtime.Stable

@Stable
data class AddTypeKind(
    val kindName: String,
    val idList: List<Int>
)
