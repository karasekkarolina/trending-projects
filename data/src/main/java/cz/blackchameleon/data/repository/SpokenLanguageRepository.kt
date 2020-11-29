package cz.blackchameleon.data.repository

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalSpokenLanguageSource
import cz.blackchameleon.data.remote.RemoteSpokenLanguageSource
import cz.blackchameleon.domain.SpokenLanguage
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

/**
 * Uses data sources implementations for providing spoken language data for use cases
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class SpokenLanguageRepository(
    private val localSpokenLanguageSource: LocalSpokenLanguageSource,
    private val remoteSpokenLanguageSource: RemoteSpokenLanguageSource
) {
    suspend fun getSpokenLanguages(force: Boolean): Result<List<SpokenLanguage>> =
        withContext(coroutineContext) {
            localSpokenLanguageSource.getSpokenLanguages()?.let {
                if (it.isNotEmpty() && !force) {
                    return@withContext Result.Success(it)
                }
            }

            try {
                val spokenLanguages = remoteSpokenLanguageSource.fetchSpokenLanguages().blockingGet()
                if (force) {
                    localSpokenLanguageSource.clean()
                }
                saveSpokenLanguages(spokenLanguages)
                return@withContext Result.Success(spokenLanguages)
            } catch (e: RuntimeException) {
                return@withContext Result.Error<List<SpokenLanguage>>(e.message)
            }
        }

    suspend fun saveSpokenLanguages(spokenLanguage: List<SpokenLanguage>) {
        spokenLanguage.forEach {
            saveSpokenLanguage(it)
        }
    }

    suspend fun saveSpokenLanguage(spokenLanguage: SpokenLanguage) {
        localSpokenLanguageSource.saveSpokenLanguage(spokenLanguage)
    }

    suspend fun clean() = localSpokenLanguageSource.clean()
}