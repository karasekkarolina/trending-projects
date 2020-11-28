package cz.blackchameleon.trendingprojects.framework.remote

import android.os.Parcelable
import cz.blackchameleon.domain.Repository
import kotlinx.android.parcel.Parcelize

/**
 * API object representation of repository object
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Parcelize
data class RepositoryMo(
    val id: String?,
    val name: String?
) : Parcelable {
    fun toRepository() = Repository(
        id ?: "",
        name ?: ""
    )
}