package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.Dao
import androidx.room.Query

/**
 * DB operations for repositories
 * Completes CRUD functions together with [BaseDao] [https://www.codecademy.com/articles/what-is-crud]
 * @see BaseDao
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Dao
interface RepositoryDao : BaseDao<RepositoryDb> {
    @Query("SELECT * FROM repositories")
    suspend fun getRepositories(): List<RepositoryDb>

    @Query("SELECT * FROM repositories WHERE id=:id ")
    suspend fun getRepository(id: String): RepositoryDb

    @Query("DELETE FROM repositories")
    suspend fun deleteAll()
}