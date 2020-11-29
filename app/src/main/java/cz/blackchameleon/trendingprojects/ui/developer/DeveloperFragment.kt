package cz.blackchameleon.trendingprojects.ui.developer

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_developer.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for developer list
 * @suppress Unchecked cast applied on List adapter cast
 * @see BaseFragment
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Suppress("UNCHECKED_CAST")
class DeveloperFragment : BaseFragment(R.layout.fragment_developer) {

    override val viewModel: DeveloperViewModel by viewModel()

    private val developerAdapter: DeveloperAdapter by lazy { DeveloperAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(developer_list, developerAdapter as ListAdapter<Any, RecyclerView.ViewHolder>)
        initObservers()
        setupListeners()
    }

    private fun initObservers() {
        viewModel.developers.observe(viewLifecycleOwner, {
            developerAdapter.submitList(it)
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
        swipe_layout.isRefreshing = visibility
        overlay.isVisible = visibility
    }
}