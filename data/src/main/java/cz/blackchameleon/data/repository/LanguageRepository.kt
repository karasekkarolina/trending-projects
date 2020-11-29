package cz.blackchameleon.data.repository

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalLanguageSource
import cz.blackchameleon.data.remote.RemoteLanguageSource
import cz.blackchameleon.domain.Language
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

/**
 * Uses data sources implementations for providing language data for use cases
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class LanguageRepository(
    private val localLanguageSource: LocalLanguageSource,
    private val remoteLanguageSource: RemoteLanguageSource
) {
    suspend fun getLanguages(): Result<List<Language>> =
        withContext(coroutineContext) {
            localLanguageSource.getLanguages()?.let {
                if (it.isNotEmpty()) {
                    return@withContext Result.Success(it)
                }
            }

            try {
                val languages = remoteLanguageSource.fetchLanguages().blockingGet()
                saveLanguages(languages)
                return@withContext Result.Success(languages)
            } catch (e: RuntimeException) {
                return@withContext Result.Error<List<Language>>(e.message)
            }
        }

    suspend fun saveLanguages(languages: List<Language>) {
        languages.forEach {
            saveLanguage(it)
        }
    }

    suspend fun saveLanguage(language: Language) {
        localLanguageSource.saveLanguage(language)
    }

    suspend fun clean() = localLanguageSource.clean()
}