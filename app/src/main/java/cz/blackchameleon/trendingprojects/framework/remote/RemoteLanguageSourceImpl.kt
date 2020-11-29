package cz.blackchameleon.trendingprojects.framework.remote

import cz.blackchameleon.data.remote.RemoteLanguageSource
import cz.blackchameleon.domain.Language
import cz.blackchameleon.trendingprojects.framework.LanguageApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Remote source implementation for getting language object from API
 * Implementation of [RemoteLanguageSource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RemoteLanguageSourceImpl(private val languageApi: LanguageApi) : RemoteLanguageSource {
    override suspend fun fetchLanguages(): Single<List<Language>> =
        languageApi.getLanguages()
            .map { list ->
                list.map {
                    it.toLanguage()
                }
            }
            .subscribeOn(Schedulers.io())
}