package cz.blackchameleon.trendingprojects.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.ui.base.BaseViewModel
import cz.blackchameleon.usecases.repository.GetRepository

/**
 * View model that provides information what to display in view represented by [RepositoryDetailFragment]
 * @see BaseViewModel
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RepositoryDetailViewModel(
    private val args: RepositoryDetailFragmentArgs
) : BaseViewModel() {

    private val _repository: MutableLiveData<Repository> = MutableLiveData()
    val repository: LiveData<Repository> = _repository

    init {
        initData(false)
    }

    override fun initData(force: Boolean) {
        _repository.postValue(args.repository)
    }
}