package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.Dao
import androidx.room.Query

/**
 * DB operations for contributors
 * Completes CRUD functions together with [BaseDao] [https://www.codecademy.com/articles/what-is-crud]
 * @see BaseDao
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
@Dao
interface ContributorDao : BaseDao<ContributorDb> {
    @Query("SELECT * FROM contributors")
    suspend fun getContributors(): List<ContributorDb>

    @Query("SELECT * FROM contributors WHERE repository_url=:repositoryUrl")
    suspend fun findContributorsForRepository(repositoryUrl: String): List<ContributorDb>

    @Query("DELETE FROM contributors")
    suspend fun deleteAll()
}