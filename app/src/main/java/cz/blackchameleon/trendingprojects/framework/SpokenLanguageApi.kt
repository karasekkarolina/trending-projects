package cz.blackchameleon.trendingprojects.framework

import cz.blackchameleon.trendingprojects.framework.remote.SpokenLanguageMo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Interface for spoken language object API calls
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface SpokenLanguageApi {
    @GET("/spoken_languages")
    fun getSpokenLanguages(): Single<List<SpokenLanguageMo>>
}