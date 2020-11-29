package cz.blackchameleon.data.repository

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalRepositorySource
import cz.blackchameleon.data.remote.RemoteRepositorySource
import cz.blackchameleon.domain.Repository
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

/**
 * Uses data sources implementations for providing repository data for use cases
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RepositoryRepository(
    private val localRepositorySource: LocalRepositorySource,
    private val remoteRepositorySource: RemoteRepositorySource
) {
    suspend fun getRepository(url: String): Result<Repository> =
        withContext(coroutineContext) {
            localRepositorySource.getRepository(url)?.let {
                return@withContext Result.Success(it)
            } ?: Result.Error("Repository not found")
        }

    suspend fun getRepositories(): Result<List<Repository>> =
        withContext(coroutineContext) {
            localRepositorySource.getRepositories()?.let {
                if (it.isNotEmpty()) {
                    return@withContext Result.Success(it)
                }
            }

            try {
                val repositories = remoteRepositorySource.fetchRepositories().blockingGet()
                saveRepositories(repositories)
                return@withContext Result.Success(repositories)
            } catch (e: RuntimeException) {
                return@withContext Result.Error<List<Repository>>(e.message)
            }
        }

    suspend fun saveRepositories(repositories: List<Repository>) {
        repositories.forEach {
            saveRepository(it)
        }
    }

    suspend fun saveRepository(repository: Repository) {
        localRepositorySource.saveRepository(repository)
    }

    suspend fun clean() = localRepositorySource.clean()
}