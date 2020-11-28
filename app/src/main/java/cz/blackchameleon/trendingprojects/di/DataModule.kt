package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.data.repository.DeveloperRepository
import cz.blackchameleon.data.repository.RepositoryRepository
import org.koin.dsl.module

/**
 * Koin module for repositories in data module
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val dataModule = module {
    single { DeveloperRepository(get(), get()) }
    single { RepositoryRepository(get(), get()) }
}