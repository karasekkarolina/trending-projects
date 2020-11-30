package cz.blackchameleon.trendingprojects.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.data.Result
import cz.blackchameleon.domain.DateRange
import cz.blackchameleon.domain.Language
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.domain.SpokenLanguage
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseViewModel
import cz.blackchameleon.trendingprojects.ui.filter.FilterViewModel
import cz.blackchameleon.usecases.repository.GetRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model that provides information what to display in view represented by [RepositoryFragment]
 * @see BaseViewModel
 *
 * @param getRepositories Use case [GetRepositories]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RepositoryViewModel(
    private val getRepositories: GetRepositories,
) : BaseViewModel() {

    private val _repositories: MutableLiveData<List<Repository>> = MutableLiveData()
    val repositories: LiveData<List<Repository>> = _repositories

    var currentFilterType: FilterViewModel.FilterType = FilterViewModel.FilterType.LANGUAGE
    var dateRange: DateRange = DateRange.DAILY
    var language: Language? = null
    var spokenLanguage: SpokenLanguage? = null

    init {
        initData(false)
    }

    override fun initData(force: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            getRepositories(force, dateRange, language, spokenLanguage).let {
                when (it) {
                    is Result.Success -> {
                        _repositories.postValue(it.data)
                    }
                    is Result.Error -> {
                        _showError.postValue(R.string.repository_list_loading_failed)
                    }
                }
            }
        }
    }

    fun onDailyFilterClicked() {
        dateRange = DateRange.DAILY
        initData(true)
    }

    fun onWeeklyFilterClicked() {
        dateRange = DateRange.WEEKLY
        initData(true)
    }

    fun onMonthlyFilterClicked() {
        dateRange = DateRange.MONTHLY
        initData(true)
    }
}