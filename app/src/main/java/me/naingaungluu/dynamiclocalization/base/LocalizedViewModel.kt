package me.naingaungluu.dynamiclocalization.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    val localizationFlow : StateFlow<Localization>
        get() = localizationRepository.localizationFlow

    val localization : Localization
    get() = localizationFlow.value

}