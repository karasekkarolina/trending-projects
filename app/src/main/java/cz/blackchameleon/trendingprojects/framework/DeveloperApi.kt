package cz.blackchameleon.trendingprojects.framework

import cz.blackchameleon.trendingprojects.framework.remote.DeveloperMo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Interface for developer object API calls
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface DeveloperApi {
    @GET("/developers")
    fun getDevelopers(): Single<List<DeveloperMo>>
}