package me.naingaungluu.dynamiclocalization.data.sources.localization

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import me.naingaungluu.dynamiclocalization.R
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.data.models.LocalizationBundle
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage
import javax.inject.Inject

class LocalizationLocalImpl @Inject constructor(
    @ApplicationContext private val context : Context
): LocalizationLocal {

    private val moshiAdapter : JsonAdapter<LocalizationBundle> by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(LocalizationBundle::class.java)
    }

    override fun getLocalizationBundle(): LocalizationBundle {
        // Fetch Localization Bundle from Raw Asset
        val localizationJson = getLocalizationJsonFromLocal()
        val localizationBundle = moshiAdapter.fromJson(localizationJson)
        return localizationBundle ?: LocalizationBundle()
    }

    private fun getLocalizationJsonFromLocal() : String
    = context.resources
        .openRawResource(R.raw.localization)
        .bufferedReader()
        .use { it.readText() }

}