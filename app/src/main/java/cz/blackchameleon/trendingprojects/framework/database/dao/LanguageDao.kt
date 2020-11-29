package cz.blackchameleon.trendingprojects.framework.database.dao

import androidx.room.Dao
import androidx.room.Query
import cz.blackchameleon.trendingprojects.framework.database.db.LanguageDb

/**
 * DB operations for language
 * Completes CRUD functions together with [BaseDao] [https://www.codecademy.com/articles/what-is-crud]
 * @see BaseDao
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Dao
interface LanguageDao : BaseDao<LanguageDb> {
    @Query("SELECT * FROM languages")
    suspend fun getLanguages(): List<LanguageDb>

    @Query("DELETE FROM languages")
    suspend fun deleteAll()
}