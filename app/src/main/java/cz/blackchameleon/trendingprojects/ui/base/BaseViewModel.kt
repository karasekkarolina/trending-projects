package cz.blackchameleon.trendingprojects.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.blackchameleon.trendingprojects.extensions.Event

/**
 * Base view model class that provides common functionality of view models
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
abstract class BaseViewModel : ViewModel() {
    protected val _showError: MutableLiveData<Event<Int>> = MutableLiveData()
    val showError: LiveData<Event<Int>> = _showError

    private val _showEmptyState: MutableLiveData<Event<Unit>> = MutableLiveData()
    val showEmptyState: LiveData<Event<Unit>> = _showEmptyState

    // Provides view model data initialization
    abstract fun initData(force: Boolean)

    fun onSwipeReload() {
        initData(true)
    }

    fun showEmptyState() {
        _showEmptyState.postValue(Event(Unit))
    }
}