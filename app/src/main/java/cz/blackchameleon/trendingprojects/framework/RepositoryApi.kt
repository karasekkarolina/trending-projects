package cz.blackchameleon.trendingprojects.framework

import cz.blackchameleon.trendingprojects.framework.remote.RepositoryMo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Interface for repository object API calls
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface RepositoryApi {
    @GET("/repositories")
    fun getRepositories(): Single<List<RepositoryMo>>
}