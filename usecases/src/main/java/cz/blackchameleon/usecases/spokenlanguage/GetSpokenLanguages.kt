package cz.blackchameleon.usecases.spokenlanguage

import cz.blackchameleon.data.Result
import cz.blackchameleon.data.repository.SpokenLanguageRepository
import cz.blackchameleon.domain.SpokenLanguage

/**
 * Use case that returns spoken language data
 * @param spokenLanguageRepository Repository [SpokenLanguageRepository]
 *
 * @author Karolina Klepackova on 29.11.2020.
 */
class GetSpokenLanguages(
    private val spokenLanguageRepository: SpokenLanguageRepository
) {
    suspend operator fun invoke(): Result<List<SpokenLanguage>> =
        spokenLanguageRepository.getSpokenLanguages()
}