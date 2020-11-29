package cz.blackchameleon.data.local

import cz.blackchameleon.domain.SpokenLanguage

/**
 * Interface specifying possible actions with locally stored data source of spoken language objects in framework layer
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface LocalSpokenLanguageSource {
    suspend fun getSpokenLanguages(): List<SpokenLanguage>?

    suspend fun saveSpokenLanguage(spokenLanguage: SpokenLanguage)

    suspend fun clean()
}