package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.usecases.developer.GetDevelopers
import cz.blackchameleon.usecases.language.GetLanguages
import cz.blackchameleon.usecases.repository.GetRepositories
import cz.blackchameleon.usecases.repository.GetRepository
import cz.blackchameleon.usecases.spokenlanguage.GetSpokenLanguages
import org.koin.dsl.module

/**
 * Koin module for use cases
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val useCaseModule = module {
    single { GetDevelopers(get()) }
    single { GetRepository(get()) }
    single { GetRepositories(get()) }
    single { GetLanguages(get()) }
    single { GetSpokenLanguages(get()) }
}