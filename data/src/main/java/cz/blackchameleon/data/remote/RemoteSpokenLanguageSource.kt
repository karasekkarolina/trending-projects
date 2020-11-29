package cz.blackchameleon.data.remote

import cz.blackchameleon.domain.SpokenLanguage
import io.reactivex.rxjava3.core.Single

/**
 * Interface specifying which spoken language data can be provided via API calls implemented in framework layer
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface RemoteSpokenLanguageSource {
    suspend fun fetchSpokenLanguages() : Single<List<SpokenLanguage>>
}