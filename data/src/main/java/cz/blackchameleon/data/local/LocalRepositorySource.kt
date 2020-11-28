package cz.blackchameleon.data.local

import cz.blackchameleon.domain.Repository

/**
 * Interface specifying possible actions with locally stored data source of repository objects in framework layer
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface LocalRepositorySource {
    suspend fun getRepository(id: String): Repository?

    suspend fun getRepositories(): List<Repository>?

    suspend fun saveRepositories(repository: Repository)

    suspend fun clean()
}