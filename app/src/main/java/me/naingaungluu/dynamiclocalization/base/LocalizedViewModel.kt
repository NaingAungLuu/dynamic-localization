package me.naingaungluu.dynamiclocalization.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.data.repositories.LocalizationRepository
import javax.inject.Inject

/**
 * Created by Naing Aung Luu on 1/13/22.
 */

abstract class LocalizedViewModel : ViewModel() {

    @Inject
    protected lateinit var localizationRepository: LocalizationRepository

    lateinit var localization : Localization

    val localizationFlow : Flow<Localization>
        get() = localizationRepository.localizationFlow
            .onEach {
                localization = it
            }

}