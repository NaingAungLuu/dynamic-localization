package me.naingaungluu.dynamiclocalization.data.sources.localization

import me.naingaungluu.dynamiclocalization.data.models.LocalizationBundle

interface LocalizationRemote {
    suspend fun getLocalizationBundle(): LocalizationBundle
}