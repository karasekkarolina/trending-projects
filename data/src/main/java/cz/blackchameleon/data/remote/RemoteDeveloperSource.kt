package cz.blackchameleon.data.remote

import cz.blackchameleon.domain.Developer
import io.reactivex.rxjava3.core.Single

/**
 * Interface specifying which developer data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
interface RemoteDeveloperSource {
    suspend fun fetchDevelopers() : Single<List<Developer>>
}