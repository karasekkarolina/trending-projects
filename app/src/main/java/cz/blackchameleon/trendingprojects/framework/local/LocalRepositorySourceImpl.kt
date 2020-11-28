package cz.blackchameleon.trendingprojects.framework.local

import android.content.Context
import cz.blackchameleon.data.local.LocalRepositorySource
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.framework.database.Database
import cz.blackchameleon.trendingprojects.framework.database.RepositoryDao
import cz.blackchameleon.trendingprojects.framework.database.RepositoryDb

/**
 * Local source implementation for getting repository object from DB
 * Implementation of [LocalRepositorySource]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class LocalRepositorySourceImpl(
    context: Context
) : LocalRepositorySource {
    private val database = Database.getInstance(context)
    private val repositoryDao: RepositoryDao?

    init {
        repositoryDao = database?.repositoryDao()
    }

    override suspend fun getRepository(id: String): Repository? = repositoryDao?.getRepository(id)?.toRepository()

    override suspend fun getRepositories(): List<Repository>? =
        repositoryDao?.getRepositories()?.map { it.toRepository() }

    override suspend fun saveRepositories(repository: Repository) {
        repository.run {
            repositoryDao?.insert(
                RepositoryDb(
                    id, name
                )
            )
        }
    }

    override suspend fun clean() {
        repositoryDao?.deleteAll()
    }
}