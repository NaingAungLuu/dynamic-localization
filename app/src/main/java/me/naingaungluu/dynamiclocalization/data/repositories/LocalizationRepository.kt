package me.naingaungluu.dynamiclocalization.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage
import java.util.*

interface LocalizationRepository {

    val localizationFlow: MutableStateFlow<Localization>
    val currentAppLanguage: AppLanguage

    suspend fun updateLanguage(language: AppLanguage)
    suspend fun getLocalizationFromRemote()
}