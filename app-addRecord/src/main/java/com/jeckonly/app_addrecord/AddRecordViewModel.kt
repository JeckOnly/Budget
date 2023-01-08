package com.jeckonly.app_addrecord

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeckonly.core_data.common.repo.interface_.UserPrefsRepo
import com.jeckonly.designsystem.theme.BudgetColorTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(
    private val app: Application,
    private val userPrefsRepo: UserPrefsRepo,
) : ViewModel() {

    val themeFlow: StateFlow<BudgetColorTheme> = userPrefsRepo.getThemeNumber().map {
       BudgetColorTheme.chooseThemeByNumber(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BudgetColorTheme.chooseThemeByNumber(0)
    )
}