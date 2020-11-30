package cz.blackchameleon.trendingprojects

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalSpokenLanguageSource
import cz.blackchameleon.data.remote.RemoteSpokenLanguageSource
import cz.blackchameleon.data.repository.SpokenLanguageRepository
import cz.blackchameleon.domain.SpokenLanguage
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test class for spoken language repository logic
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
@RunWith(MockitoJUnitRunner::class)
class SpokenLanguageRepositoryTest {
    @Mock
    lateinit var localSpokenLanguageSource: LocalSpokenLanguageSource

    @Mock
    lateinit var remoteSpokenLanguageSource: RemoteSpokenLanguageSource

    private lateinit var spokenLanguageRepository: SpokenLanguageRepository

    private val firstSpokenLanguage = SpokenLanguage(
        urlParam = "firstUrlParam",
        name = "firstSpokenLanguage"
    )

    private val secondSpokenLanguage = SpokenLanguage(
        urlParam = "secondUrlParam",
        name = "secondSpokenLanguage"
    )

    @Before
    fun setup() {
        spokenLanguageRepository =
            SpokenLanguageRepository(localSpokenLanguageSource, remoteSpokenLanguageSource)
    }

    @Test
    fun `pass when local source is cleaned`() {
        runBlocking {
            spokenLanguageRepository.clean()
            verify(localSpokenLanguageSource, times(1)).clean()
        }
    }

    @Test
    fun `pass when spoken language is saved`() {
        runBlocking {
            spokenLanguageRepository.saveSpokenLanguage(firstSpokenLanguage)
            verify(localSpokenLanguageSource, times(1)).saveSpokenLanguage(firstSpokenLanguage)
        }
    }

    @Test
    fun `pass when spoken languages are saved`() {
        runBlocking {
            spokenLanguageRepository.saveSpokenLanguages(
                listOf(
                    firstSpokenLanguage,
                    firstSpokenLanguage
                )
            )
            verify(localSpokenLanguageSource, times(2)).saveSpokenLanguage(firstSpokenLanguage)
        }
    }

    @Test
    fun `pass when spoken languages are loaded from database`() {
        runBlocking {
            `when`(localSpokenLanguageSource.getSpokenLanguages()).thenReturn(
                listOf(
                    firstSpokenLanguage,
                    secondSpokenLanguage
                )
            )

            val spokenLanguages = spokenLanguageRepository.getSpokenLanguages(false)
            assert(
                spokenLanguages is Result.Success && spokenLanguages.data == listOf(
                    firstSpokenLanguage,
                    secondSpokenLanguage
                )
            )
        }
    }

    @Test
    fun `pass when spoken languages are loaded from api`() {
        runBlocking {
            doReturn(Single.just(listOf(firstSpokenLanguage, secondSpokenLanguage)))
                .`when`(remoteSpokenLanguageSource)
                .fetchSpokenLanguages()

            val spokenLanguages = spokenLanguageRepository.getSpokenLanguages(true)
            assert(
                spokenLanguages is Result.Success && spokenLanguages.data == listOf(
                    firstSpokenLanguage,
                    secondSpokenLanguage
                )
            )
        }
    }

    @Test
    fun `pass when spoken languages loading throws exception`() {
        val exception = mock(RuntimeException::class.java)

        runBlocking {
            doThrow(exception)
                .`when`(remoteSpokenLanguageSource)
                .fetchSpokenLanguages()

            val spokenLanguages = spokenLanguageRepository.getSpokenLanguages(true)
            assert(spokenLanguages is Result.Error)
        }
    }
}