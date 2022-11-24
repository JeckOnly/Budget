package com.jeckonly.choosetype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.choosetype.ui.state.KeyboardState
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

@Composable
fun ChooseTypeKeyBoard(keyboardState: KeyboardState, modifier: Modifier = Modifier) {
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    Surface(
        modifier = modifier, color = backgroundColor, shadowElevation = 12.dp
    ) {
        Column(modifier = Mdf.fillMaxSize()) {
            Text(
                text = keyboardState.showText,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                color = contentColor,
                modifier = Mdf
                    .align(Alignment.End)
                    .padding(top = 15.dp, bottom = 15.dp, end = 20.dp)
            )
            Divider(
                modifier = Mdf.fillMaxWidth(),
                thickness = 0.1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Row(
                modifier = Mdf
                    .fillMaxWidth()
                    .weight(0.25f),
            ) {
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(), onClick = {
                    keyboardState.onEvent(keyboardState.numberButton7)
                }) {
                    Text(
                        text = "7", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(), onClick = {
                    keyboardState.onEvent(keyboardState.numberButton8)
                }) {
                    Text(
                        text = "8", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton9) }) {
                    Text(
                        text = "9", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }

                // 日历
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.dateButtonType) }) {
                    Row() {
                        Icon(
                            painter = painterResource(id = keyboardState.dateButtonType.iconId),
                            contentDescription = null,
                            modifier = Mdf.size(20.dp),
                            tint = contentColor
                        )
                        Text(
                            text = keyboardState.dateButtonType.text,
                            style = MaterialTheme.typography.bodyLarge,
                            color = contentColor,
                            modifier = Mdf.padding(start = 10.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Mdf
                    .fillMaxWidth()
                    .weight(0.25f)
            ) {
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(), onClick = {
                    keyboardState.onEvent(keyboardState.numberButton4)
                }) {
                    Text(
                        text = "4", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton5) }) {
                    Text(
                        text = "5", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton6) }) {
                    Text(
                        text = "6", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.plus) }) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
            Row(
                modifier = Mdf
                    .fillMaxWidth()
                    .weight(0.25f)
            ) {
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton1) }) {
                    Text(
                        text = "1", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton2) }) {
                    Text(
                        text = "2", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton3) }) {
                    Text(
                        text = "3", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.minus) }) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
            Row(
                modifier = Mdf
                    .fillMaxWidth()
                    .weight(0.25f)
            ) {
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.point) }) {
                    Text(
                        text = ".", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.numberButton0) }) {
                    Text(
                        text = "0", style = MaterialTheme.typography.bodyLarge,
                        color = contentColor,
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight(),
                    onClick = { keyboardState.onEvent(keyboardState.delete) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.keyboard_delete),
                        contentDescription = null,
                        modifier = Mdf.size(25.dp),
                        tint = contentColor
                    )
                }
                KeyboardBox(modifier = Mdf
                    .weight(0.25f)
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                    onClick = { keyboardState.onEvent(keyboardState.finish) }) {
                    Text(
                        text = keyboardState.finish.text.value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.tertiaryContainer),
                    )
                }
            }
        }
    }
}

@Composable
fun KeyboardBox(
    modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                width = 0.1.dp,
                brush = SolidColor(MaterialTheme.colorScheme.outlineVariant),
                shape = RectangleShape
            )
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewKeyboardBox() {
    KeyboardBox(modifier = Mdf
        .width(30.dp)
        .height(20.dp), onClick = { }) {
        Text(
            text = "1", style = MaterialTheme.typography.bodyLarge,
            color = contentColorFor(
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ),
        )
    }
}