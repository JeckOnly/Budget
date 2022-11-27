package com.jeckonly.choosetype.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.jeckonly.designsystem.composable.datepicker.DatePicker
import com.jeckonly.designsystem.composable.datepicker.rememberDatePickerState
import java.time.LocalDate

@Composable
fun ChooseTypeKeyBoard(
    keyboardState: KeyboardState, onCLickDown: () -> Unit, modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = contentColorFor(backgroundColor = backgroundColor)
    var showRemarkDialog: Boolean by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    Surface(
        modifier = modifier,
        color = backgroundColor,
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(modifier = Mdf.fillMaxSize()) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = null,
                modifier = Mdf
                    .padding(top = 8.dp)
                    .size(20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCLickDown
                    )
                    .align(Alignment.CenterHorizontally),
                tint = contentColor
            )
            Box(modifier = Mdf.fillMaxWidth()) {
                Box(
                    modifier = Mdf
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp, bottom = 15.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.remark),
                        contentDescription = null,
                        modifier = Mdf
                            .size(20.dp)
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    showRemarkDialog = true
                                })
                            .align(Alignment.Center),
                        tint = contentColor)
                    if (keyboardState.remark.value != "") {
                        Box(
                            modifier = Mdf
                                .align(Alignment.TopEnd)
                                .offset(x = 10.dp, y = 10.dp)
                        ) {
                            Box(
                                modifier = Mdf
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = CircleShape
                                    )
                                    .size(15.dp)
                                    .offset(x = 10.dp, y = 10.dp)
                            )
                        }
                    }
                }
                Text(
                    text = keyboardState.showText,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
                    color = contentColor,
                    modifier = Mdf
                        .align(alignment = Alignment.CenterEnd)
                        .padding(bottom = 15.dp, end = 20.dp)
                )
            }
            Divider(
                modifier = Mdf.fillMaxWidth(),
                thickness = (0.2).dp,
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
                    .fillMaxHeight(), onClick = {
                    // show date picker
                    datePickerState.show()
                }) {
                    if (keyboardState.calendarString == "") {
                        Row {
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
                    } else {
                        Text(
                            text = keyboardState.calendarString,
                            style = MaterialTheme.typography.bodyLarge,
                            color = contentColor
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
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                    onClick = { keyboardState.onEvent(keyboardState.finish) }) {
                    Text(
                        text = keyboardState.finish.text.value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer),
                    )
                }
            }
        }
    }

    // remark dialog
    AnimatedVisibility(
        visible = showRemarkDialog, enter = fadeIn(), exit = fadeOut(
            animationSpec = TweenSpec(50)
        )
    ) {
        RemarkDialog(initialTextFieldValue = keyboardState.remark.value, onClickSure = { remark ->
            keyboardState.remark.value = remark
            showRemarkDialog = false
        }, onClickCancel = {
            showRemarkDialog = false
        }, onDismissRequest = { showRemarkDialog = false })
    }

    // date picker
    DatePicker(dialogState = datePickerState, initialDate = keyboardState.chooseLocalDate?: LocalDate.now()){
        keyboardState.onDatePicked(it)
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