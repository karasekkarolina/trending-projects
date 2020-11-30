package cz.blackchameleon.trendingprojects.framework.database.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cz.blackchameleon.domain.Contributor

/**
 * Contributor entity representation in DB
 * Defines DB table and is connected to repositories table in DB
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
@Entity(
    tableName = "contributors",
    foreignKeys = [ForeignKey(
        entity = RepositoryDb::class,
        parentColumns = ["url"],
        childColumns = ["repository_url"],
        onDelete = ForeignKey.CASCADE
    )]
)
class ContributorDb(
    @ColumnInfo(name = "username")
    var username: String,
    @PrimaryKey
    @ColumnInfo(name = "href")
    var href: String,
    @ColumnInfo(name = "avatar")
    var avatar: String,
    @ColumnInfo(name = "repository_url", index = true)
    var repositoryUrl: String
) {
    /**
     * Conversion of DB object into [Contributor]
     */
    fun toContributor(): Contributor = Contributor(
        username, href, avatar
    )
}