package cz.blackchameleon.trendingprojects.framework

import cz.blackchameleon.trendingprojects.framework.remote.RepositoryMo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for repository object API calls
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface RepositoryApi {
    @GET("/repositories")
    fun getRepositories(
        @Query("language") language: String?,
        @Query("since") since: String?,
        @Query("spoken_language_code") spoken_language_code: String?
    ): Single<List<RepositoryMo>>
}