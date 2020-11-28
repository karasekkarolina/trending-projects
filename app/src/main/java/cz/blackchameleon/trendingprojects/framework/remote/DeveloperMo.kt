package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.Developer
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of developer object
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Parcelize
data class DeveloperMo(
    val id: String?,
    val name: String?
) : Parcelable {
    fun toDeveloper() = Developer(
        id ?: "",
        name ?: ""
    )
}