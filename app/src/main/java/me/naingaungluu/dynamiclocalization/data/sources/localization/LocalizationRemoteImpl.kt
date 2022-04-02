package me.naingaungluu.dynamiclocalization.data.sources.localization

import kotlinx.coroutines.delay
import me.naingaungluu.dynamiclocalization.data.models.Localization
import me.naingaungluu.dynamiclocalization.data.models.LocalizationBundle
import javax.inject.Inject

class LocalizationRemoteImpl @Inject constructor(): LocalizationRemote {

    override suspend fun getLocalizationBundle(): LocalizationBundle {
        // A Dummy Implementation of fetching Localization from Remote
        delay(5000)
        return LocalizationBundle(
            en = Localization().apply {
                lblGreeting = "Hello from Remote"
                lblSelectedLanguage = "Selected Language From Remote : %@"
                lblEnglish = "English"
                lblChinese = "Chinese"
                lblBurmese = "Burmese"
            },
            mm = Localization().apply {
                lblGreeting = "မင်္ဂလာပါ။ from Remote"
                lblSelectedLanguage = "ရွေးချယ်ထားသောဘာသာစကား From Remote : %@"
                lblEnglish = "အင်္ဂလိပ်"
                lblChinese = "တရုတ်"
                lblBurmese = "ဗမာ"
            },
            cn = Localization().apply {
                lblGreeting = "你好 from Remote"
                lblSelectedLanguage = "选择的语言 From Remote : %@"
                lblEnglish = "英语"
                lblChinese = "缅甸语"
                lblBurmese = "中文"
            }
        )
    }
}