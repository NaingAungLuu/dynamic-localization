package me.naingaungluu.dynamiclocalization.data.models

import java.io.Serializable

class Localization : Serializable {

    /**
     * Properties
     */
    var lblGreeting : String = ""
    var lblSelectedLanguage : String = ""
    var lblEnglish : String = ""
    var lblBurmese : String = ""
    var lblChinese : String = ""

    companion object {
        //dummy
        private val dummy = Localization().apply {
            lblGreeting = "Hello"
            lblSelectedLanguage = "Selected Language : %@"
            lblEnglish = "English"
            lblChinese = "Chinese"
            lblBurmese = "Burmese"
        }
        fun getDefaultLocalization() : Localization = dummy

        fun getTemplatedString(format: String, vararg params: String): String =
            if (params.isNotEmpty() && format.contains("%@"))
                getTemplatedString(
                    format.replaceFirst("%@", params.first()),
                    *params.drop(1).toTypedArray()
                )
        else
            format
    }
}