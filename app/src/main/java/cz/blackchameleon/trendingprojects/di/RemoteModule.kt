package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.data.remote.RemoteDeveloperSource
import cz.blackchameleon.data.remote.RemoteRepositorySource
import cz.blackchameleon.trendingprojects.framework.remote.RemoteDeveloperSourceImpl
import cz.blackchameleon.trendingprojects.framework.remote.RemoteRepositorySourceImpl
import org.koin.dsl.module

/**
 * Koin module for remote sources
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val remoteModule = module {
    single { RemoteDeveloperSourceImpl(get()) as RemoteDeveloperSource }
    single { RemoteRepositorySourceImpl(get()) as RemoteRepositorySource }
}