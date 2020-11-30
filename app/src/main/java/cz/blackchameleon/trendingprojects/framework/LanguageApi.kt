package cz.blackchameleon.trendingprojects.framework

import cz.blackchameleon.trendingprojects.framework.remote.LanguageMo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Interface for language object API calls
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface LanguageApi {
    @GET("/languages")
    fun getLanguages(): Single<List<LanguageMo>>
}