package cz.blackchameleon.trendingprojects

import android.app.Application
import cz.blackchameleon.trendingprojects.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class that inits dependency injection
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Dependency injection made by insert-koin.io (see https://insert-koin.io)
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    dataModule,
                    useCaseModule,
                    retrofitModule,
                    localModule,
                    remoteModule
                )
            )
        }
    }
}