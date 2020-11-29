package cz.blackchameleon.usecases.language

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.repository.LanguageRepository
import cz.blackchameleon.domain.Language

/**
 * Use case that returns language data
 * @param languageRepository Repository [LanguageRepository]
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
class GetLanguages(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(force: Boolean): Result<List<Language>> =
        languageRepository.getLanguages(force)
}