package cz.blackchameleon.trendingprojects.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Filter
import cz.blackchameleon.trendingprojects.R
import kotlinx.android.synthetic.main.item_filter.view.*

/**
 * @author Karolina Klepackova on 30.11.2020.
 */
class FilterAdapter(
    private val clickListener: (Filter) -> Unit
) : ListAdapter<Filter, FilterAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter) =
            oldItem == newItem
    }) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter, parent, false)
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Filter) {
            itemView.apply {
                name.text = item.name
                main_content.setOnClickListener { clickListener(item) }
            }
        }
    }
}