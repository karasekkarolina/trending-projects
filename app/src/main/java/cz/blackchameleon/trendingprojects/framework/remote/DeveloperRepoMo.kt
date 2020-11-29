package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.DeveloperRepo
import kotlinx.android.parcel.Parcelize

/**
 * @author Karolina Klepackova on 29.11.2020.
 */
@Parcelize
data class DeveloperRepoMo(
    val name: String?,
    val url: String?,
    val description: String?
) : Parcelable {
    fun toDeveloperRepo() = DeveloperRepo(
        name ?: "",
        url ?: "",
        description ?: ""
    )
}