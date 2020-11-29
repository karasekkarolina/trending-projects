package cz.blackchameleon.trendingprojects.framework.database.dao

import androidx.room.Dao
import androidx.room.Query
import cz.blackchameleon.trendingprojects.framework.database.db.SpokenLanguageDb

/**
 * DB operations for spoken language
 * Completes CRUD functions together with [BaseDao] [https://www.codecademy.com/articles/what-is-crud]
 * @see BaseDao
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Dao
interface SpokenLanguageDao : BaseDao<SpokenLanguageDb> {
    @Query("SELECT * FROM spoken_languages")
    suspend fun getSpokenLanguages(): List<SpokenLanguageDb>

    @Query("DELETE FROM spoken_languages")
    suspend fun deleteAll()
}