package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.trendingprojects.UrlConst.BASE_URL
import cz.blackchameleon.trendingprojects.framework.DeveloperApi
import cz.blackchameleon.trendingprojects.framework.LanguageApi
import cz.blackchameleon.trendingprojects.framework.RepositoryApi
import cz.blackchameleon.trendingprojects.framework.SpokenLanguageApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Koin module for retrofit
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val retrofitModule = module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }

    single { provideRetrofitService<DeveloperApi>(get()) }
    single { provideRetrofitService<RepositoryApi>(get()) }
    single { provideRetrofitService<LanguageApi>(get()) }
    single { provideRetrofitService<SpokenLanguageApi>(get()) }
}

// Creates new Okhttp client for API calls
private fun provideDefaultOkhttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .addInterceptor(provideLoggingInterceptor())
        .build()

// Creates an instance of retrofit for given OkHttpClient
private fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

// Extension function for creating retrofit service
private inline fun <reified T> provideRetrofitService(retrofit: Retrofit): T =
    retrofit.create(T::class.java)

private fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}