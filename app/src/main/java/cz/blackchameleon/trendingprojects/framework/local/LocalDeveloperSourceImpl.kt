package cz.blackchameleon.trendingprojects.framework.local

import android.content.Context
import cz.blackchameleon.data.local.LocalDeveloperSource
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.trendingprojects.framework.database.Database
import cz.blackchameleon.trendingprojects.framework.database.dao.DeveloperDao
import cz.blackchameleon.trendingprojects.framework.database.db.DeveloperDb

/**
 * Local source implementation for getting developer object from DB
 * Implementation of [LocalDeveloperSource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class LocalDeveloperSourceImpl(
    context: Context
) : LocalDeveloperSource {
    private val database = Database.getInstance(context)
    private val developerDao: DeveloperDao?

    init {
        developerDao = database?.developerDao()
    }

    override suspend fun getDevelopers(): List<Developer>? =
        developerDao?.getDevelopers()?.map { it.toDeveloper() }

    override suspend fun saveDevelopers(developer: Developer) {
        developer.run {
            developerDao?.insert(
                DeveloperDb(
                    username, name, url, sponsorUrl, avatar, repo
                )
            )
        }
    }

    override suspend fun clean() {
        developerDao?.deleteAll()
    }
}