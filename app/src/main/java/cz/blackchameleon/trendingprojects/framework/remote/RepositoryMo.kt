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
    val author: String?,
    val name: String?,
    val avatar: String?,
    val url: String?,
    val description: String?,
    val language: String?,
    val languageColor: String?,
    val stars: String?,
    val forks: String?,
    val currentPeriodStars: String?,
    val builtBy: List<ContributorMo>?
) : Parcelable {
    fun toRepository() = Repository(
        author ?: "",
        name ?: "",
        avatar ?: "",
        url ?: "",
        description ?: "",
        language ?: "",
        languageColor ?: "",
        stars ?: "",
        forks ?: "",
        currentPeriodStars ?: "",
        builtBy?.map { it.toContributor() } ?: listOf()
    )
}