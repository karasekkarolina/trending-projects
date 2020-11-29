package cz.blackchameleon.trendingprojects.ui.developer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.trendingprojects.R
import kotlinx.android.synthetic.main.item_developer.view.*

/**
 * Handles repository list items logic
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class DeveloperAdapter : ListAdapter<Developer, DeveloperAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Developer>() {
        override fun areItemsTheSame(oldItem: Developer, newItem: Developer) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Developer, newItem: Developer) =
            oldItem == newItem
    }) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_developer, parent, false)
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Developer) {
            itemView.apply {

                username.text = item.username
                url.text = item.url
                description.text = item.repo.description
            }
        }
    }
}