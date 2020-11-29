package cz.blackchameleon.trendingprojects.di

import cz.blackchameleon.trendingprojects.ui.developer.DeveloperViewModel
import cz.blackchameleon.trendingprojects.ui.repository.RepositoryDetailViewModel
import cz.blackchameleon.trendingprojects.ui.repository.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for view models
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
val viewModelModule = module {
    viewModel { DeveloperViewModel(get()) }
    viewModel { RepositoryViewModel(get(), get(), get()) }
    viewModel { RepositoryDetailViewModel(get()) }
}