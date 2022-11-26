package com.jeckonly.choosetype.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R
import com.jeckonly.designsystem.noIndicationClickable


private const val REMARK_MAX_LENGTH = 60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemarkDialog(
    initialTextFieldValue: String,
    onClickSure: (String) -> Unit,
    onClickCancel: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    val textStyle = MaterialTheme.typography.bodyLarge
    var remarkValue: String by remember {
        mutableStateOf(initialTextFieldValue)
    }
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            color = backgroundColor,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = remarkValue,
                    onValueChange = { newValue ->
                        if (newValue.length > REMARK_MAX_LENGTH) {
                            // 长度超过了
                        } else {
                            remarkValue = newValue
                        }
                    },
                    textStyle = textStyle,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = context.getString(R.string.click_write_remark),
                            style = textStyle,
                            color = contentColor,
                        )
                    })
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = context.getString(R.string.cancel),
                        style = textStyle,
                        color = contentColor,
                        modifier = Mdf.noIndicationClickable(onClick = onClickCancel)
                    )
                    Button(
                        onClick = {
                            onClickSure(remarkValue)
                        },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = context.getString(R.string.sure),
                            style = textStyle,
                        )
                    }
                }
            }
        }
    }
}