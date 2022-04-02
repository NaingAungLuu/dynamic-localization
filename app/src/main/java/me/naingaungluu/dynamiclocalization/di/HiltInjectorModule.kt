package me.naingaungluu.dynamiclocalization.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import me.naingaungluu.dynamiclocalization.data.repositories.LocalizationRepository
import me.naingaungluu.dynamiclocalization.data.repositories.LocalizationRepositoryImpl
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationLocal
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationLocalImpl
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationRemote
import me.naingaungluu.dynamiclocalization.data.sources.localization.LocalizationRemoteImpl
import me.naingaungluu.dynamiclocalization.preferences.PreferenceStorage
import me.naingaungluu.dynamiclocalization.preferences.PreferenceStorageImpl
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object HiltInjectorModule {

    @Provides
    @ViewModelScoped
    fun provideLocalizationRepository(
        preferenceStorage: PreferenceStorage,
        localizationLocal: LocalizationLocal,
        localizationRemote: LocalizationRemote
    ): LocalizationRepository
    = LocalizationRepositoryImpl(preferenceStorage,localizationLocal,localizationRemote)

    @ViewModelScoped
    @Provides
    fun providePreferenceStorage(
        @ApplicationContext context: Context
    ): PreferenceStorage
    = PreferenceStorageImpl(context)

    @ViewModelScoped
    @Provides
    fun provideLocalizationLocal(
        @ApplicationContext context: Context
    ): LocalizationLocal
    = LocalizationLocalImpl(context)

    @ViewModelScoped
    @Provides
    fun provideLocalizationRemote(): LocalizationRemote
    = LocalizationRemoteImpl()
}