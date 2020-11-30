package cz.blackchameleon.trendingprojects.ui.repository

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Filter
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import cz.blackchameleon.trendingprojects.ui.filter.FilterViewModel
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
            findNavController().navigate(
                RepositoryFragmentDirections.actionRepositoryDetailFragment(
                    it
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        initObservers()
        setupListeners()
    }

    private fun setupViews() {
        initRecycler(
            repository_list,
            repositoryAdapter as ListAdapter<Any, RecyclerView.ViewHolder>
        )
        language.text = viewModel.language?.name ?: resources.getString(R.string.filter_any_language)
        spoken_language.text =
            viewModel.spokenLanguage?.name ?: resources.getString(R.string.filter_any_spoken_language)
        date_range.text = resources.getString(R.string.filter_today)
    }

    private fun initObservers() {
        viewModel.repositories.observe(viewLifecycleOwner, {
            repositoryAdapter.apply {
                submitList(it)
                period = viewModel.dateRange
                notifyDataSetChanged()
            }
            no_data_text.isVisible = it.isEmpty()
            setLoadingVisibility(false)
        })
        viewModel.showEmptyState.observe(viewLifecycleOwner, {
            no_data_text.isVisible = true
            setLoadingVisibility(false)
        })
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Filter>("filter")
            ?.observe(
                viewLifecycleOwner
            ) { filter ->
                when (viewModel.currentFilterType) {
                    FilterViewModel.FilterType.LANGUAGE -> {
                        viewModel.language = filter
                        language.text = filter.name
                    }
                    FilterViewModel.FilterType.SPOKEN_LANGUAGE -> {
                        viewModel.spokenLanguage = filter
                        spoken_language.text = filter.name
                    }
                }
                viewModel.initData(true)
            }
    }

    private fun setupListeners() {
        swipe_layout.setOnRefreshListener { viewModel.onSwipeReload() }

        date_range.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                inflate(R.menu.date_range_filter)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.daily -> {
                            viewModel.onDailyFilterClicked()
                            date_range.text = resources.getString(R.string.filter_today)
                        }
                        R.id.weekly -> {
                            viewModel.onWeeklyFilterClicked()
                            date_range.text = resources.getString(R.string.filter_weekly)
                        }
                        R.id.monthly -> {
                            viewModel.onMonthlyFilterClicked()
                            date_range.text = resources.getString(R.string.filter_monthly)
                        }
                    }
                    true
                }
            }.show()
        }

        language.setOnClickListener {
            viewModel.currentFilterType = FilterViewModel.FilterType.LANGUAGE
            findNavController().navigate(RepositoryFragmentDirections.actionFilterFragment(viewModel.currentFilterType))
        }

        spoken_language.setOnClickListener {
            viewModel.currentFilterType = FilterViewModel.FilterType.SPOKEN_LANGUAGE
            findNavController().navigate(RepositoryFragmentDirections.actionFilterFragment(viewModel.currentFilterType))
        }
    }

    private fun setLoadingVisibility(visibility: Boolean) {
        swipe_layout.isRefreshing = visibility
        overlay.isVisible = visibility
    }
}