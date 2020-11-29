package cz.blackchameleon.data.local

import cz.blackchameleon.domain.Language

/**
 * Interface specifying possible actions with locally stored data source of language objects in framework layer
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
interface LocalLanguageSource {
    suspend fun getLanguages(): List<Language>?

    suspend fun saveLanguage(language: Language)

    suspend fun clean()
}