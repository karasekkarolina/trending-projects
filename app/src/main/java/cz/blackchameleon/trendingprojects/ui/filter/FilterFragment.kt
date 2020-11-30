package cz.blackchameleon.trendingprojects.ui.filter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
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
                    FilterViewModel.FilterType.LANGUAGE -> previousBackStackEntry?.savedStateHandle?.set("filter", it)
                    FilterViewModel.FilterType.SPOKEN_LANGUAGE -> previousBackStackEntry?.savedStateHandle?.set("filter", it)
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

        viewModel.filters.observe(viewLifecycleOwner, {
            filterAdapter.submitList(it)
        })
    }
}