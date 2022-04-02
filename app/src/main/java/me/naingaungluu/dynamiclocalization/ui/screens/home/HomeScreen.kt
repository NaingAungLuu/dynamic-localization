package me.naingaungluu.dynamiclocalization.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.preferences.AppLanguage

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenStateless(
        HomeScreenState(
            data = HomeScreenState.Data(
                localization = viewModel.localizationFlow.collectAsState(
                    initial = Localization.getDefaultLocalization()
                ),
                appLanguage = viewModel.currentLanguageFlow.collectAsState(
                    initial = AppLanguage.ENGLISH
                )
            ),
            delegates = HomeScreenState.Delegate(
                onTapEnglish = viewModel::switchToEnglish,
                onTapBurmese = viewModel::switchToBurmese,
                onTapChinese = viewModel::switchToChinese
            )
        )
    )
}

@Preview(
    showBackground = true
)
@Composable
fun HomeScreenStateless(
    state: HomeScreenState = HomeScreenState.previewState
) {

    val localization by state.data.localization
    val appLanguage by state.data.appLanguage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = localization.lblGreeting,
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 24.sp
                )
            )
            Text(
                text = Localization.getTemplatedString(
                    localization.lblSelectedLanguage,
                    appLanguage.value
                ),
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 24.sp
                )
            )
        }
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            Button(onClick = state.delegates.onTapEnglish) {
                Text(text = localization.lblEnglish)
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = state.delegates.onTapChinese) {
                Text(text = localization.lblChinese)
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = state.delegates.onTapBurmese) {
                Text(text = localization.lblBurmese)
            }
        }
    }
}

data class HomeScreenState(
    val data : Data = Data(),
    val delegates: Delegate = Delegate()
) {
    data class Data(
        val localization: State<Localization> = mutableStateOf(Localization()),
        val appLanguage: State<AppLanguage> = mutableStateOf(AppLanguage.ENGLISH)
    )

    data class Delegate(
        val onTapEnglish: () -> Unit = {},
        val onTapChinese: () -> Unit = {},
        val onTapBurmese: () -> Unit = {}
    )

    companion object {
        val previewState by lazy {
            HomeScreenState(
                data = Data(
                    localization = mutableStateOf(Localization.getDefaultLocalization())
                ),
                delegates = Delegate()
            )
        }
    }
}