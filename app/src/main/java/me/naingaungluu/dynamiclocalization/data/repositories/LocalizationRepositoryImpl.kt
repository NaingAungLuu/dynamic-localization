package me.naingaungluu.dynamiclocalization.data.repositories

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.naingaungluu.dynamiclocalization.R
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.data.models.LocalizationBundle
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationLocal
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationRemote
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage
import me.naingaungluu.dynamiclocalization.preferences.PreferenceStorage
import java.util.*
import javax.inject.Inject

class LocalizationRepositoryImpl @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
    private val local: LocalizationLocal,
    private val remote: LocalizationRemote,
) : LocalizationRepository {

    override lateinit var localizationFlow: MutableStateFlow<Localization>

    private var cachedLocalizationBundle : LocalizationBundle? = null

    override val currentAppLanguage: AppLanguage
        get() = preferenceStorage.getAppLanguage()

    init {
        initLocalizationFlow()
        CoroutineScope(Dispatchers.IO).launch {
            getLocalizationFromRemote()
        }
    }

    private fun initLocalizationFlow() {
        val localization = local.getLocalizationBundle().getLocalization(currentAppLanguage)
        localizationFlow = MutableStateFlow(localization)
        localizationFlow.value
    }

    override suspend fun updateLanguage(language: AppLanguage) {
        localizationFlow.value = getLocalization(language)
        preferenceStorage.saveAppLanguage(language)
    }

    override suspend fun getLocalizationFromRemote() {
        // Fetch localization data from remote here
        remote.getLocalizationBundle().let {
            cachedLocalizationBundle = it
            localizationFlow.value = it.getLocalization(currentAppLanguage)
        }
    }

    private fun getLocalization(language: AppLanguage) : Localization
    = cachedLocalizationBundle?.getLocalization(language)
        ?: local.getLocalizationBundle().getLocalization(language)


    fun LocalizationBundle.getLocalization(language: AppLanguage)
    = when(language) {
        AppLanguage.ENGLISH -> en
        AppLanguage.CHINESE -> cn
        AppLanguage.BURMESE -> mm
    }

}