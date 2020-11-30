package cz.blackchameleon.data.remote

import cz.blackchameleon.domain.DateRange
import cz.blackchameleon.domain.Language
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.domain.SpokenLanguage
import io.reactivex.rxjava3.core.Single

/**
 * Interface specifying which repository data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface RemoteRepositorySource {
    suspend fun fetchRepositories(
        dateRange: DateRange? = null,
        language: Language? = null,
        spokenLanguage: SpokenLanguage? = null
    ): Single<List<Repository>>
}