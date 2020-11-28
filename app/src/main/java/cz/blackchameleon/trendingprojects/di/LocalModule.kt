package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.data.local.LocalDeveloperSource
import cz.blackchameleon.data.local.LocalRepositorySource
import cz.blackchameleon.trendingprojects.framework.local.LocalDeveloperSourceImpl
import cz.blackchameleon.trendingprojects.framework.local.LocalRepositorySourceImpl
import org.koin.dsl.module

/**
 * Koin module for local sources
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val localModule = module {
    single { LocalDeveloperSourceImpl(get()) as LocalDeveloperSource }
    single { LocalRepositorySourceImpl(get()) as LocalRepositorySource }
}