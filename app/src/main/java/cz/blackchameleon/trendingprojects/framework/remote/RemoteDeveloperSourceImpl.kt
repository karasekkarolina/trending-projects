package cz.blackchameleon.trendingprojects.framework.remote

import cz.blackchameleon.data.remote.RemoteDeveloperSource
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.trendingprojects.framework.DeveloperApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Remote source implementation for getting developer object from API
 * Implementation of [RemoteDeveloperSource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RemoteDeveloperSourceImpl(private val developerApi: DeveloperApi) : RemoteDeveloperSource {
    override suspend fun fetchDevelopers(): Single<List<Developer>> =
        developerApi.getDevelopers()
            .map { list ->
                list.map {
                    it.toDeveloper()
                }
            }
            .subscribeOn(Schedulers.io())
}