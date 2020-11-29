package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.data.remote.RemoteDeveloperSource
import cz.blackchameleon.data.remote.RemoteLanguageSource
import cz.blackchameleon.data.remote.RemoteRepositorySource
import cz.blackchameleon.data.remote.RemoteSpokenLanguageSource
import cz.blackchameleon.trendingprojects.framework.remote.RemoteDeveloperSourceImpl
import cz.blackchameleon.trendingprojects.framework.remote.RemoteLanguageSourceImpl
import cz.blackchameleon.trendingprojects.framework.remote.RemoteRepositorySourceImpl
import cz.blackchameleon.trendingprojects.framework.remote.RemoteSpokenLanguageSourceImpl
import org.koin.dsl.module

/**
 * Koin module for remote sources
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val remoteModule = module {
    single { RemoteDeveloperSourceImpl(get()) as RemoteDeveloperSource }
    single { RemoteRepositorySourceImpl(get()) as RemoteRepositorySource }
    single { RemoteLanguageSourceImpl(get()) as RemoteLanguageSource }
    single { RemoteSpokenLanguageSourceImpl(get()) as RemoteSpokenLanguageSource }
}