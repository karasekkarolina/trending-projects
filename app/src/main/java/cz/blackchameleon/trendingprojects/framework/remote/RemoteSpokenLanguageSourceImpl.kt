package cz.blackchameleon.trendingprojects.framework.remote

import cz.blackchameleon.data.remote.RemoteSpokenLanguageSource
import cz.blackchameleon.domain.SpokenLanguage
import cz.blackchameleon.trendingprojects.framework.SpokenLanguageApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Remote source implementation for getting spoken language object from API
 * Implementation of [RemoteSpokenLanguageSource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RemoteSpokenLanguageSourceImpl(private val spokenLanguageApi: SpokenLanguageApi) :
    RemoteSpokenLanguageSource {
    override suspend fun fetchSpokenLanguages(): Single<List<SpokenLanguage>> =
        spokenLanguageApi.getSpokenLanguages()
            .map { list ->
                list.map {
                    it.toSpokenLanguage()
                }
            }
            .subscribeOn(Schedulers.io())
}