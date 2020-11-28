package cz.blackchameleon.trendingprojects.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.trendingprojects.R

/**
 * @author Karolina Klepackova on 27.11.2020.
 */
abstract class BaseFragment(layout: Int) : Fragment(layout) {

    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showError.observe(viewLifecycleOwner, { error ->
            context?.let { context ->
                AlertDialog.Builder(context, R.style.Widget_TrendingProjects_AlertDialog)
                    .setTitle(R.string.error_occurred)
                    .setMessage(error)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        viewModel.showEmptyState()
                    }
                    .show()
            }
        })
    }

    fun initRecycler(recyclerView: RecyclerView, adapter: ListAdapter<Any, RecyclerView.ViewHolder>) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }
}