package cz.blackchameleon.data.repository

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalDeveloperSource
import cz.blackchameleon.data.remote.RemoteDeveloperSource
import cz.blackchameleon.domain.Developer
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

/**
 * Uses data sources implementations for providing developer data for use cases
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class DeveloperRepository(
    private val localDeveloperSource: LocalDeveloperSource,
    private val remoteDeveloperSource: RemoteDeveloperSource
) {
    suspend fun getDevelopers(force: Boolean): Result<List<Developer>> =
        withContext(coroutineContext) {
            localDeveloperSource.getDevelopers()?.let {
                if (it.isNotEmpty() && !force) {
                    return@withContext Result.Success(it)
                }
            }

            try {
                val developers = remoteDeveloperSource.fetchDevelopers().blockingGet()
                if (force) {
                    localDeveloperSource.clean()
                }
                saveDevelopers(developers)
                return@withContext Result.Success(developers)
            } catch (e: RuntimeException) {
                return@withContext Result.Error<List<Developer>>(e.message)
            }
        }

    suspend fun saveDevelopers(developers: List<Developer>) {
        developers.forEach {
            saveDeveloper(it)
        }
    }

    suspend fun saveDeveloper(developer: Developer) {
        localDeveloperSource.saveDeveloper(developer)
    }

    suspend fun clean() = localDeveloperSource.clean()
}