package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.Contributor
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of contributor object
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
@Parcelize
data class ContributorMo(
    val username: String?,
    val href: String?,
    val avatar: String?
) : Parcelable {
    fun toContributor() = Contributor(
        username ?: "",
        href ?: "",
        avatar ?: ""
    )
}