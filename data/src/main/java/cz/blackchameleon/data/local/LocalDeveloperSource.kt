package cz.blackchameleon.data.local

import cz.blackchameleon.domain.Developer

/**
 * Interface specifying possible actions with locally stored data source of developer objects in framework layer
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface LocalDeveloperSource {
    suspend fun getDevelopers(): List<Developer>?

    suspend fun saveDevelopers(developer: Developer)

    suspend fun clean()
}