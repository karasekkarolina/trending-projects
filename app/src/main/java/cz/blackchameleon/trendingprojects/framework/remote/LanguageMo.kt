package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.Language
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of language object
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
@Parcelize
data class LanguageMo(
    val urlParam: String?,
    val name: String?
) : Parcelable {
    fun toLanguage() = Language(
        urlParam ?: "",
        name ?: ""
    )
}