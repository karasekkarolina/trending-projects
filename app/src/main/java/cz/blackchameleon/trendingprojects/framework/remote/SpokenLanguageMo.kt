package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.SpokenLanguage
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of spoken language object
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
@Parcelize
data class SpokenLanguageMo(
    val urlParam: String?,
    val name: String?
) : Parcelable {
    fun toSpokenLanguage() = SpokenLanguage(
        urlParam ?: "",
        name ?: ""
    )
}