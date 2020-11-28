package cz.blackchameleon.trendingprojects.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.trendingprojects.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import cz.blackchameleon.data.Result
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.usecases.repository.GetRepository

/**
 * View model that provides information what to display in view represented by [RepositoryDetailFragment]
 * @see BaseViewModel
 *
 * @param getRepository Use case [GetRepository]
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RepositoryDetailViewModel(
    private val getRepository: GetRepository
) : BaseViewModel() {

    private val _repository: MutableLiveData<Repository> = MutableLiveData()
    val repository: LiveData<Repository> = _repository

    var repositoryId: String? = null

    init {
        initData()
    }

    override fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryId?.let { repositoryId ->
                getRepository(repositoryId).let {
                    when (it) {
                        is Result.Success -> {
                            _repository.postValue(it.data)
                        }
                        is Result.Error -> {
                            _showError.postValue(R.string.repository_loading_failed)
                        }
                    }
                }
            }
        }
    }
}