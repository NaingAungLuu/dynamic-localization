package me.naingaungluu.dynamiclocalization.ui.screens.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.naingaungluu.dynamiclocalization.base.LocalizedViewModel
import me.naingaungluu.dynamiclocalization.data.repositories.LocalizationRepository
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : LocalizedViewModel() {

    private val _currentLanguage: MutableStateFlow<AppLanguage> = MutableStateFlow(AppLanguage.ENGLISH)
    val currentLanguageFlow: Flow<AppLanguage> = _currentLanguage

    fun switchToEnglish() = switchLanguage(AppLanguage.ENGLISH)

    fun switchToChinese() = switchLanguage(AppLanguage.CHINESE)

    fun switchToBurmese() = switchLanguage(AppLanguage.BURMESE)

    private fun switchLanguage(language: AppLanguage) {
        viewModelScope.launch {
            localizationRepository.updateLanguage(language)
            _currentLanguage.emit(language)
        }
    }
}