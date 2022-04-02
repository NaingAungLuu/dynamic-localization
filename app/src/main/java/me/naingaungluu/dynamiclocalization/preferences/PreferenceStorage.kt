package me.naingaungluu.dynamiclocalization.preferences

interface PreferenceStorage {
    fun getAppLanguage(): AppLanguage
    suspend fun saveAppLanguage(language: AppLanguage)
}