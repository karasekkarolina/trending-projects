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
    @PrimaryKey
    var id: String,
    @ColumnInfo(name = "name")
    var name: String
) {
    /**
     * Conversion of DB object into [Repository]
     */
    fun toRepository(): Repository = Repository(
        id = this.id,
        name = this.name
    )
}