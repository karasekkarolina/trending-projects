package cz.blackchameleon.trendingprojects.framework.remote

import cz.blackchameleon.data.remote.RemoteRepositorySource
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.framework.RepositoryApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Remote source implementation for getting repository object from API
 * Implementation of [RemoteRepositorySource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RemoteRepositorySourceImpl(private val repositoryApi: RepositoryApi) :
    RemoteRepositorySource {
    override suspend fun fetchRepositories(): Single<List<Repository>> =
        repositoryApi.getRepositories()
            .map { list ->
                list.map {
                    it.toRepository()
                }
            }
            .subscribeOn(Schedulers.io())
}