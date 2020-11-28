package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.Dao
import androidx.room.Query

/**
 * DB operations for developers
 * Completes CRUD functions together with [BaseDao] [https://www.codecademy.com/articles/what-is-crud]
 * @see BaseDao
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Dao
interface DeveloperDao : BaseDao<DeveloperDb> {
    @Query("SELECT * FROM developers")
    suspend fun getDevelopers(): List<DeveloperDb>

    @Query("DELETE FROM developers")
    suspend fun deleteAll()
}