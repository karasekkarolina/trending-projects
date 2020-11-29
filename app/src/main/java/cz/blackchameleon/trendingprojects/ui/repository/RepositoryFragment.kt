package cz.blackchameleon.trendingprojects.ui.repository

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_repository.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for repository list
 * * @suppress Unchecked cast applied on List adapter cast
 * @see BaseFragment
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Suppress("UNCHECKED_CAST")
class RepositoryFragment : BaseFragment(R.layout.fragment_repository) {

    override val viewModel: RepositoryViewModel by viewModel()

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter {
            findNavController().navigate(RepositoryFragmentDirections.actionRepositoryDetailFragment(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(
            repository_list,
            repositoryAdapter as ListAdapter<Any, RecyclerView.ViewHolder>
        )
        initObservers()
        setupListeners()
    }

    private fun initObservers() {
        viewModel.repositories.observe(viewLifecycleOwner, {
            repositoryAdapter.submitList(it)
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