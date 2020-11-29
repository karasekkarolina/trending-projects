package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.domain.DeveloperRepo
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of developer object
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Parcelize
data class DeveloperMo(
    val username: String?,
    val name: String?,
    val url: String?,
    val sponsorUrl: String?,
    val avatar: String?,
    val repo: DeveloperRepoMo?
) : Parcelable {
    fun toDeveloper() = Developer(
        username ?: "",
        name ?: "",
        url ?: "",
        sponsorUrl ?: "",
        avatar ?: "",
        repo?.toDeveloperRepo() ?: DeveloperRepo("", "", "")
    )
}