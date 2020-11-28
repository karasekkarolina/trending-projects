package cz.blackchameleon.trendingprojects.ui.developer

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_developer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for developer list
 * @see BaseFragment
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class DeveloperFragment : BaseFragment(R.layout.fragment_developer) {

    override val viewModel: DeveloperViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setupListeners()
    }

    private fun initObservers() {
        viewModel.developers.observe(viewLifecycleOwner, {
            setLoadingVisibility(false)
        })
        viewModel.showEmptyState.observe(viewLifecycleOwner, {
            no_data_text.isVisible = true
            setLoadingVisibility(false)
        })
    }

    private fun setupListeners() {
        swipe_layout.setOnRefreshListener { viewModel.onSwipeReload() }
    }

    private fun setLoadingVisibility(visibility: Boolean) {
        swipe_layout.isVisible = visibility
        overlay.isVisible = visibility
    }
}