package cz.blackchameleon.trendingprojects.ui.repository

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.DateRange
import cz.blackchameleon.domain.Filter
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import cz.blackchameleon.trendingprojects.ui.filter.FilterViewModel
import kotlinx.android.synthetic.main.fragment_repository.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that handles UI for repository list
 * @suppress (Unchecked cast) applied on List adapter cast
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
        language.text = resources.getString(R.string.filter_any_language)
        spoken_language.text = resources.getString(R.string.filter_any_spoken_language)
        date_range.text = resources.getString(R.string.filter_today)
    }

    private fun initObservers() {
        viewModel.dateRange.observe(viewLifecycleOwner, {
            date_range.text = when (it) {
                DateRange.DAILY -> resources.getString(R.string.filter_today)
                DateRange.WEEKLY -> resources.getString(R.string.filter_weekly)
                DateRange.MONTHLY -> resources.getString(R.string.filter_monthly)
            }
        })
        viewModel.language.observe(viewLifecycleOwner, {
            language.text = it?.name ?: resources.getString(R.string.filter_any_language)
        })

        viewModel.spokenLanguage.observe(viewLifecycleOwner, {
            spoken_language.text = it?.name ?: resources.getString(R.string.filter_any_spoken_language)
        })
        viewModel.repositories.observe(viewLifecycleOwner, { list ->
            repositoryAdapter.apply {
                submitList(list.sortedByDescending { it.currentPeriodStars.toInt() })
                period = viewModel.dateRange.value ?: DateRange.DAILY
                notifyDataSetChanged()
            }
            no_data_text.isVisible = list.isEmpty()
            setLoadingVisibility(false)
        })
        viewModel.showEmptyState.observe(viewLifecycleOwner, {
            no_data_text.isVisible = viewModel.repositories.value?.isEmpty() ?: true
            setLoadingVisibility(false)
        })
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Filter>("filter")
            ?.observe(
                viewLifecycleOwner
            ) { filter ->
                when (viewModel.currentFilterType) {
                    FilterViewModel.FilterType.LANGUAGE -> {
                        if (viewModel.language.value != filter) {
                            viewModel.onLanguageChanged(filter)
                            language.text =
                                filter?.name ?: resources.getString(R.string.filter_any_language)
                            viewModel.initData(true)
                            swipe_layout.isRefreshing = true
                        }
                    }
                    FilterViewModel.FilterType.SPOKEN_LANGUAGE -> {
                        if (viewModel.spokenLanguage.value != filter) {
                            viewModel.onSpokenLanguageChanged(filter)
                            spoken_language.text = filter?.name
                                ?: resources.getString(R.string.filter_any_spoken_language)
                            viewModel.initData(true)
                            swipe_layout.isRefreshing = true
                        }
                    }
                }
            }
    }

    private fun setupListeners() {
        swipe_layout.setOnRefreshListener { viewModel.onSwipeReload() }

        date_range.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                inflate(R.menu.date_range_filter)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.daily -> viewModel.onDailyFilterClicked()
                        R.id.weekly -> viewModel.onWeeklyFilterClicked()
                        R.id.monthly -> viewModel.onMonthlyFilterClicked()
                    }
                    swipe_layout.isRefreshing = true
                    true
                }
            }.show()
        }

        language.setOnClickListener {
            viewModel.currentFilterType = FilterViewModel.FilterType.LANGUAGE
            viewModel.currentFilterType?.let { findNavController().navigate(RepositoryFragmentDirections.actionFilterFragment(it)) }
        }

        spoken_language.setOnClickListener {
            viewModel.currentFilterType = FilterViewModel.FilterType.SPOKEN_LANGUAGE
            viewModel.currentFilterType?.let { findNavController().navigate(RepositoryFragmentDirections.actionFilterFragment(it)) }
        }
    }

    private fun setLoadingVisibility(visibility: Boolean) {
        swipe_layout.isRefreshing = visibility
        overlay.isVisible = visibility
    }
}