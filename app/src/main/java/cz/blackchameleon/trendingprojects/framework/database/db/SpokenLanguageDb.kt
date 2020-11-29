package cz.blackchameleon.trendingprojects.framework.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.blackchameleon.domain.SpokenLanguage

/**
 * Spoken language entity representation in DB
 * Defines DB table
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Entity(tableName = "spoken_languages")
class SpokenLanguageDb(
    @PrimaryKey
    @ColumnInfo(name = "url_param")
    var urlParam: String,
    @ColumnInfo(name = "name")
    var name: String
) {
    /**
     * Conversion of DB object into [SpokenLanguage]
     */
    fun toSpokenLanguage(): SpokenLanguage = SpokenLanguage(
        urlParam, name
    )
}