package cz.blackchameleon.trendingprojects

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.local.LocalDeveloperSource
import cz.blackchameleon.data.remote.RemoteDeveloperSource
import cz.blackchameleon.data.repository.DeveloperRepository
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.domain.DeveloperRepo
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test class for developer repository logic
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
@RunWith(MockitoJUnitRunner::class)
class DeveloperRepositoryTest {
    @Mock
    lateinit var localDeveloperSource: LocalDeveloperSource

    @Mock
    lateinit var remoteDeveloperSource: RemoteDeveloperSource

    private lateinit var developerRepository: DeveloperRepository

    private val firstDeveloper = Developer(
        username = "firstDeveloper",
        name = "firstName",
        url = "firstUrl",
        sponsorUrl = "firstSponsorUrl",
        avatar = "firstAvatar",
        repo = DeveloperRepo(
            name = "firstDeveloperRepoName",
            url = "firstDeveloperRepoUrl",
            description = "firstDeveloperRepoDescription"
        )
    )

    private val secondDeveloper = Developer(
        username = "secondDeveloper",
        name = "secondName",
        url = "secondUrl",
        sponsorUrl = "secondSponsorUrl",
        avatar = "secondAvatar",
        repo = DeveloperRepo(
            name = "secondDeveloperRepoName",
            url = "secondDeveloperRepoUrl",
            description = "secondDeveloperRepoDescription"
        )
    )

    @Before
    fun setup() {
        developerRepository = DeveloperRepository(localDeveloperSource, remoteDeveloperSource)
    }

    @Test
    fun `pass when local source is cleaned`() {
        runBlocking {
            developerRepository.clean()
            verify(localDeveloperSource, times(1)).clean()
        }
    }

    @Test
    fun `pass when developer is saved`() {
        runBlocking {
            developerRepository.saveDeveloper(firstDeveloper)
            verify(localDeveloperSource, times(1)).saveDeveloper(firstDeveloper)
        }
    }

    @Test
    fun `pass when developers are saved`() {
        runBlocking {
            developerRepository.saveDevelopers(listOf(firstDeveloper, firstDeveloper))
            verify(localDeveloperSource, times(2)).saveDeveloper(firstDeveloper)
        }
    }

    @Test
    fun `pass when developers are loaded from database`() {
        runBlocking {
            `when`(localDeveloperSource.getDevelopers()).thenReturn(
                listOf(
                    firstDeveloper,
                    secondDeveloper
                )
            )

            val developers = developerRepository.getDevelopers(false)
            assert(
                developers is Result.Success && developers.data == listOf(
                    firstDeveloper,
                    secondDeveloper
                )
            )
        }
    }

    @Test
    fun `pass when developers are loaded from api`() {
        runBlocking {
            doReturn(Single.just(listOf(firstDeveloper, secondDeveloper)))
                .`when`(remoteDeveloperSource)
                .fetchDevelopers()

            val developers = developerRepository.getDevelopers(true)
            assert(
                developers is Result.Success && developers.data == listOf(
                    firstDeveloper,
                    secondDeveloper
                )
            )
        }
    }

    @Test
    fun `pass when developers loading throws exception`() {
        val exception = mock(RuntimeException::class.java)

        runBlocking {
            doThrow(exception)
                .`when`(remoteDeveloperSource)
                .fetchDevelopers()

            val developers = developerRepository.getDevelopers(true)
            assert(developers is Result.Error)
        }
    }
}