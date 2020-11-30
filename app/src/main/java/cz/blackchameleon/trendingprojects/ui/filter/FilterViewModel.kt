package cz.blackchameleon.trendingprojects.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.data.Result
import cz.blackchameleon.domain.Filter
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.Event
import cz.blackchameleon.trendingprojects.ui.base.BaseViewModel
import cz.blackchameleon.usecases.language.GetLanguages
import cz.blackchameleon.usecases.spokenlanguage.GetSpokenLanguages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model that provides information what to display in view represented by [FilterFragment]
 * @see BaseViewModel
 *
 * @param args contains which filter type should be shown
 * @param getLanguages Use case [GetLanguages]
 * @param getSpokenLanguages Use case [GetSpokenLanguages]
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
class FilterViewModel(
    private val args: FilterFragmentArgs,
    private val getLanguages: GetLanguages,
    private val getSpokenLanguages: GetSpokenLanguages
) : BaseViewModel() {

    private val _filters: MutableLiveData<List<Filter>> = MutableLiveData()
    val filters: LiveData<List<Filter>> = _filters

    private val _searchedFilters: MutableLiveData<List<Filter>> = MutableLiveData()
    val searchedFilters: LiveData<List<Filter>> = _searchedFilters

    init {
        initData(false)
    }

    override fun initData(force: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            when (args.filter) {
                FilterType.LANGUAGE -> {
                    getLanguages(force).let {
                        when (it) {
                            is Result.Success -> {
                                _filters.postValue(it.data)
                            }
                            is Result.Error -> {
                                _showError.postValue(Event(R.string.developer_list_loading_failed))
                            }
                        }
                    }
                }
                FilterType.SPOKEN_LANGUAGE -> {
                    getSpokenLanguages(force).let {
                        when (it) {
                            is Result.Success -> {
                                _filters.postValue(it.data)
                            }
                            is Result.Error -> {
                                _showError.postValue(Event(R.string.developer_list_loading_failed))
                            }
                        }
                    }
                }
            }
        }
    }

    fun onSearchInputChanged(string: String) {
        val searchedFilters: MutableList<Filter> = mutableListOf()

        searchedFilters.run {
            _filters.value?.forEach {
                if (it.name.contains(string)) add(it)
            }
        }
        _searchedFilters.postValue(searchedFilters)
    }

    enum class FilterType {
        LANGUAGE,
        SPOKEN_LANGUAGE
    }
}