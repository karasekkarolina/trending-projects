package cz.blackchameleon.trendingprojects

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalLanguageSource
import cz.blackchameleon.data.remote.RemoteLanguageSource
import cz.blackchameleon.data.repository.LanguageRepository
import cz.blackchameleon.domain.Language
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test class for language repository logic
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
@RunWith(MockitoJUnitRunner::class)
class LanguageRepositoryTest {
    @Mock
    lateinit var localLanguageSource: LocalLanguageSource

    @Mock
    lateinit var remoteLanguageSource: RemoteLanguageSource

    private lateinit var languageRepository: LanguageRepository

    private val firstLanguage = Language(
        urlParam = "firstUrlParam",
        name = "firstLanguage"
    )

    private val secondLanguage = Language(
        urlParam = "secondUrlParam",
        name = "secondLanguage"
    )

    @Before
    fun setup() {
        languageRepository = LanguageRepository(localLanguageSource, remoteLanguageSource)
    }

    @Test
    fun `pass when local source is cleaned`() {
        runBlocking {
            languageRepository.clean()
            verify(localLanguageSource, times(1)).clean()
        }
    }

    @Test
    fun `pass when language is saved`() {
        runBlocking {
            languageRepository.saveLanguage(firstLanguage)
            verify(localLanguageSource, times(1)).saveLanguage(firstLanguage)
        }
    }

    @Test
    fun `pass when languages are saved`() {
        runBlocking {
            languageRepository.saveLanguages(listOf(firstLanguage, firstLanguage))
            verify(localLanguageSource, times(2)).saveLanguage(firstLanguage)
        }
    }

    @Test
    fun `pass when languages are loaded from database`() {
        runBlocking {
            `when`(localLanguageSource.getLanguages()).thenReturn(
                listOf(
                    firstLanguage,
                    secondLanguage
                )
            )

            val languages = languageRepository.getLanguages(false)
            assert(
                languages is Result.Success && languages.data == listOf(
                    firstLanguage,
                    secondLanguage
                )
            )
        }
    }

    @Test
    fun `pass when languages are loaded from api`() {
        runBlocking {
            doReturn(Single.just(listOf(firstLanguage, secondLanguage)))
                .`when`(remoteLanguageSource)
                .fetchLanguages()

            val languages = languageRepository.getLanguages(true)
            assert(
                languages is Result.Success && languages.data == listOf(
                    firstLanguage,
                    secondLanguage
                )
            )
        }
    }

    @Test
    fun `pass when languages loading throws exception`() {
        val exception = mock(RuntimeException::class.java)

        runBlocking {
            doThrow(exception)
                .`when`(remoteLanguageSource)
                .fetchLanguages()

            val languages = languageRepository.getLanguages(true)
            assert(languages is Result.Error)
        }
    }
}