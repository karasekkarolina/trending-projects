package cz.blackchameleon.trendingprojects.framework.local

import android.content.Context
import cz.blackchameleon.data.local.LocalRepositorySource
import cz.blackchameleon.domain.Contributor
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.framework.database.*
import cz.blackchameleon.trendingprojects.framework.database.dao.ContributorDao
import cz.blackchameleon.trendingprojects.framework.database.dao.RepositoryDao
import cz.blackchameleon.trendingprojects.framework.database.db.ContributorDb
import cz.blackchameleon.trendingprojects.framework.database.db.RepositoryDb

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
    private val contributorDao: ContributorDao?

    init {
        repositoryDao = database?.repositoryDao()
        contributorDao = database?.contributorDao()
    }

    override suspend fun getRepository(url: String): Repository? {
        val repository = repositoryDao?.getRepository(url)?.toRepository()
        val contributors: List<Contributor> = repository?.url?.let { repositoryUrl ->
            contributorDao?.findContributorsForRepository(repositoryUrl)?.map {
                it.toContributor()
            }
        } ?: emptyList()
        repository?.apply {
            builtBy = contributors
        }
        return repository
    }

    override suspend fun getRepositories(): List<Repository>? {
        val repositories = repositoryDao?.getRepositories()?.map { it.toRepository() }
        repositories?.forEach { repository ->
            val contributors: List<Contributor> = repository.url.let { repositoryUrl ->
                contributorDao?.findContributorsForRepository(repositoryUrl)?.map {
                    it.toContributor()
                }
            } ?: emptyList()
            repository.apply {
                builtBy = contributors
            }
        }
        return repositories
    }

    override suspend fun saveRepository(repository: Repository) {
        repository.run {
            repositoryDao?.insert(
                RepositoryDb(
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
            )

            repository.builtBy.forEach {
                contributorDao?.insert(
                    ContributorDb(
                        it.username,
                        it.href,
                        it.avatar,
                        repository.url
                    )
                )
            }
        }
    }

    override suspend fun clean() {
        repositoryDao?.deleteAll()
    }
}