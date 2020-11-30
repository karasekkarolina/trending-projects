package cz.blackchameleon.trendingprojects.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.data.Result
import cz.blackchameleon.domain.DateRange
import cz.blackchameleon.domain.Language
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.domain.SpokenLanguage
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.Event
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

    private val _dateRange: MutableLiveData<DateRange> = MutableLiveData()
    val dateRange: LiveData<DateRange> = _dateRange

    private val _language: MutableLiveData<Language?> = MutableLiveData()
    val language: LiveData<Language?> = _language

    private val _spokenLanguage: MutableLiveData<SpokenLanguage?> = MutableLiveData()
    val spokenLanguage: LiveData<SpokenLanguage?> = _spokenLanguage

    var currentFilterType: FilterViewModel.FilterType? = null

    init {
        initData(false)
    }

    override fun initData(force: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            getRepositories(force, dateRange.value, language.value, spokenLanguage.value).let {
                when (it) {
                    is Result.Success -> {
                        _repositories.postValue(it.data)
                    }
                    is Result.Error -> {
                        _showError.postValue(Event(R.string.repository_list_loading_failed))
                    }
                }
            }
        }
    }

    fun onDailyFilterClicked() {
        _dateRange.postValue(DateRange.DAILY)
        initData(true)
    }

    fun onWeeklyFilterClicked() {
        _dateRange.postValue(DateRange.MONTHLY)
        initData(true)
    }

    fun onMonthlyFilterClicked() {
        _dateRange.postValue(DateRange.MONTHLY)
        initData(true)
    }

    fun onLanguageChanged(language: Language?) {
        _language.postValue(language)
    }

    fun onSpokenLanguageChanged(spokenLanguage: SpokenLanguage?) {
        _spokenLanguage.postValue(spokenLanguage)
    }
}