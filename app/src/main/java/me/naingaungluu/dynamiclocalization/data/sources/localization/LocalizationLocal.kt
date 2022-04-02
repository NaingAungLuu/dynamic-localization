package me.naingaungluu.dynamiclocalization.data.sources.localization

import me.naingaungluu.dynamiclocalization.data.models.LocalizationBundle

interface LocalizationLocal {
    fun getLocalizationBundle(): LocalizationBundle
}