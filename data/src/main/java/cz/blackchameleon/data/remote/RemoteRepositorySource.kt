package cz.blackchameleon.data.remote

import cz.blackchameleon.domain.Repository
import io.reactivex.rxjava3.core.Single

/**
 * Interface specifying which repository data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface RemoteRepositorySource {
    suspend fun fetchRepository() : Single<Repository>

    suspend fun fetchRepositories() : Single<List<Repository>>
}