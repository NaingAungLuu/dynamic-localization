package me.naingaungluu.dynamiclocalization.data.models

data class LocalizationBundle(
    val en : Localization = Localization(),
    val cn : Localization = Localization(),
    val mm : Localization = Localization()
)