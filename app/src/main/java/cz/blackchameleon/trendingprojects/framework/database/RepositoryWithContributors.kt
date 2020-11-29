package cz.blackchameleon.trendingprojects.framework.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * @author Karolina Klepackova on 29.11.2020.
 */
data class RepositoryWithContributors(
    @Embedded val repository: RepositoryDb,
    @Relation(
        parentColumn = "repositories",
        entityColumn = "contributors"
    )
    val contributors: List<ContributorDb>
)