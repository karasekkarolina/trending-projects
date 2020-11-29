package cz.blackchameleon.trendingprojects.framework.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.blackchameleon.domain.Language

/**
 * Language entity representation in DB
 * Defines DB table
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Entity(tableName = "languages")
class LanguageDb(
    @PrimaryKey
    @ColumnInfo(name = "url_param")
    var urlParam: String,
    @ColumnInfo(name = "name")
    var name: String
) {
    /**
     * Conversion of DB object into [Language]
     */
    fun toLanguage(): Language = Language(
        urlParam, name
    )
}