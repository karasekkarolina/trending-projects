package cz.blackchameleon.data.remote

import cz.blackchameleon.domain.Language
import io.reactivex.rxjava3.core.Single

/**
 * Interface specifying which language data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface RemoteLanguageSource {
    suspend fun fetchLanguages() : Single<List<Language>>
}