package com.jeckonly.designsystem.composable.datepicker

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jeckonly.designsystem.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

/**
 * @param onDateChange only be called when the positive button is pressed
 */
@Composable
fun DatePicker(dialogState:  MaterialDialogState, initialDate: LocalDate = LocalDate.now(), onDateChange: (LocalDate) -> Unit = {}) {
    val context = LocalContext.current
    MaterialDialog(dialogState = dialogState,
        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        autoDismiss = true,
        buttons = {
            positiveButton(
                text = context.getString(R.string.sure),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = contentColorFor(
                    backgroundColor = MaterialTheme.colorScheme.surface
                ))
            )
            negativeButton(
                text = context.getString(R.string.cancel),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = contentColorFor(
                    backgroundColor = MaterialTheme.colorScheme.surface
                ))
            )
        }) {
        datepicker(
            initialDate = initialDate,
            title = context.getString(R.string.choose_date),
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                headerTextColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondaryContainer),
                calendarHeaderTextColor = contentColorFor(
                    backgroundColor = MaterialTheme.colorScheme.surface
                ),
                dateInactiveBackgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                    alpha = 0.5f
                ),
                dateInactiveTextColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondaryContainer),
                dateActiveBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                dateActiveTextColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
            ),
            yearRange = IntRange(2000, 2100),
            waitForPositiveButton = true,
        ) { date ->
            // only be called when the positive button is pressed
            onDateChange(date)
        }
    }
}

@Composable
fun rememberDatePickerState(): MaterialDialogState {
    return rememberMaterialDialogState()
}