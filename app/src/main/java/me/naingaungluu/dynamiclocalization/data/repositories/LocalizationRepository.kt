package me.naingaungluu.dynamiclocalization.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage
import java.util.*

interface LocalizationRepository {

    val localizationFlow: StateFlow<Localization>
    val currentAppLanguage: AppLanguage

    suspend fun updateLanguage(language: AppLanguage)
}