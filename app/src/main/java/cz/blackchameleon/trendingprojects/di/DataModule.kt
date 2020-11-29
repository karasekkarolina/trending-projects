package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.data.repository.DeveloperRepository
import cz.blackchameleon.data.repository.LanguageRepository
import cz.blackchameleon.data.repository.RepositoryRepository
import cz.blackchameleon.data.repository.SpokenLanguageRepository
import org.koin.dsl.module

/**
 * Koin module for repositories in data module
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val dataModule = module {
    single { DeveloperRepository(get(), get()) }
    single { RepositoryRepository(get(), get()) }
    single { LanguageRepository(get(), get()) }
    single { SpokenLanguageRepository(get(), get()) }
}