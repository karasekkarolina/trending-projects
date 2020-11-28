package cz.blackchameleon.usecases.developer

import cz.blackchameleon.data.repository.DeveloperRepository
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.data.Result

/**
 * Use case that returns developer data
 * @param developerRepository Repository [DeveloperRepository]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class GetDevelopers(
    private val developerRepository: DeveloperRepository
) {
    suspend operator fun invoke(): Result<List<Developer>> = developerRepository.getDevelopers()
}