package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.blackchameleon.domain.Repository

/**
 * Repository entity representation in DB
 * Defines DB table
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Entity(tableName = "repositories")
class RepositoryDb(
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "avatar")
    var avatar: String,
    @PrimaryKey
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "language")
    var language: String,
    @ColumnInfo(name = "languageColor")
    var languageColor: String,
    @ColumnInfo(name = "stars")
    var stars: String,
    @ColumnInfo(name = "forks")
    var forks: String,
    @ColumnInfo(name = "currentPeriodStars")
    var currentPeriodStars: String
) {
    /**
     * Conversion of DB object into [Repository]
     */
    fun toRepository(): Repository = Repository(
        author,
        name,
        avatar,
        url,
        description,
        language,
        languageColor,
        stars,
        forks,
        currentPeriodStars
    )
}