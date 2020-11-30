package cz.blackchameleon.usecases.repository

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.repository.RepositoryRepository
import cz.blackchameleon.domain.DateRange
import cz.blackchameleon.domain.Language
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.domain.SpokenLanguage

/**
 * Use case that returns repository data
 * @param repositoryRepository Repository [RepositoryRepository]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class GetRepositories(
    private val repositoryRepository: RepositoryRepository
) {
    suspend operator fun invoke(
        force: Boolean,
        dateRange: DateRange?,
        language: Language?,
        spokenLanguage: SpokenLanguage?
    ): Result<List<Repository>> =
        repositoryRepository.getRepositories(force, dateRange, language, spokenLanguage)
}