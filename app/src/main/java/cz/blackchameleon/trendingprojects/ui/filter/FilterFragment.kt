package cz.blackchameleon.trendingprojects.ui.filter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Filter
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.afterTextChangedDelayed
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment that handles UI for filters
 * @suppress (Unchecked cast) applied on List adapter cast
 *
 * @author Karolina Klepackova on 30.11.2020.
 */
@Suppress("UNCHECKED_CAST")
class FilterFragment : BaseFragment(R.layout.fragment_filter) {

    override val viewModel: FilterViewModel by viewModel { parametersOf(args) }

    private val args: FilterFragmentArgs by navArgs()

    private val filterAdapter: FilterAdapter by lazy {
        FilterAdapter {
            findNavController().run {
                when (args.filter) {
                    FilterViewModel.FilterType.LANGUAGE -> previousBackStackEntry?.savedStateHandle?.set(
                        "filter",
                        it
                    )
                    FilterViewModel.FilterType.SPOKEN_LANGUAGE -> previousBackStackEntry?.savedStateHandle?.set(
                        "filter",
                        it
                    )
                }
                popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(
            filter_list,
            filterAdapter as ListAdapter<Any, RecyclerView.ViewHolder>
        )
        search_view.afterTextChangedDelayed {
            viewModel.onSearchInputChanged(it)
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.filters.observe(viewLifecycleOwner, {
            val primalValue = when (args.filter) {
                FilterViewModel.FilterType.LANGUAGE -> resources.getString(R.string.filter_any_language)
                FilterViewModel.FilterType.SPOKEN_LANGUAGE -> resources.getString(R.string.filter_any_spoken_language)
            }
            val initializedList: List<Filter> = listOf(Filter(null, primalValue))
            filterAdapter.submitList(initializedList + it)
        })
        viewModel.searchedFilters.observe(viewLifecycleOwner, {
            val primalValue = when (args.filter) {
                FilterViewModel.FilterType.LANGUAGE -> resources.getString(R.string.filter_any_language)
                FilterViewModel.FilterType.SPOKEN_LANGUAGE -> resources.getString(R.string.filter_any_spoken_language)
            }
            val initializedList: List<Filter> = listOf(Filter(null, primalValue))
            filterAdapter.submitList(initializedList + it)
        })
    }
}