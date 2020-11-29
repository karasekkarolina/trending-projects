package cz.blackchameleon.trendingprojects.ui.developer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.data.Result
import cz.blackchameleon.trendingprojects.ui.base.BaseViewModel
import cz.blackchameleon.usecases.developer.GetDevelopers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model that provides information what to display in view represented by [DeveloperFragment]
 * @see BaseViewModel
 *
 * @param getDevelopers Use case [GetDevelopers]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class DeveloperViewModel(
    private val getDevelopers: GetDevelopers
) : BaseViewModel() {

    private val _developers: MutableLiveData<List<Developer>> = MutableLiveData()
    val developers: LiveData<List<Developer>> = _developers

    init {
        initData(false)
    }

    override fun initData(force: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            getDevelopers(force).let {
                when (it) {
                    is Result.Success -> {
                        _developers.postValue(it.data)
                    }
                    is Result.Error -> {
                        _showError.postValue(R.string.developer_list_loading_failed)
                    }
                }
            }
        }
    }
}