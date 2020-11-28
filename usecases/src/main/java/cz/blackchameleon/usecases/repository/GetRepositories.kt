package cz.blackchameleon.usecases.repository

import cz.blackchameleon.data.repository.RepositoryRepository
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.data.Result

/**
 * Use case that returns repository data
 * @param repositoryRepository Repository [RepositoryRepository]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class GetRepositories(
    private val repositoryRepository: RepositoryRepository
) {
    suspend operator fun invoke(): Result<List<Repository>> = repositoryRepository.getRepositories()
}