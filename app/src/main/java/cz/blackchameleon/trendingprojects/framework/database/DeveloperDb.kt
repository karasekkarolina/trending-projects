package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.domain.DeveloperRepo

/**
 * Developer entity representation in DB
 * Defines DB table
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Entity(tableName = "developers")
class DeveloperDb(
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "name")
    var name: String,
    @PrimaryKey
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "sponsorUrl")
    var sponsorUrl: String,
    @ColumnInfo(name = "avatar")
    var avatar: String,
    @ColumnInfo(name = "repo")
    var repo: DeveloperRepo
) {
    /**
     * Conversion of DB object into [Developer]
     */
    fun toDeveloper(): Developer = Developer(
        username, name, url, sponsorUrl, avatar, repo
    )
}