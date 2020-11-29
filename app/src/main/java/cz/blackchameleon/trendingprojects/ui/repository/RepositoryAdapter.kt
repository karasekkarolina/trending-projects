package cz.blackchameleon.trendingprojects.ui.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Repository
import cz.blackchameleon.trendingprojects.R
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Handles repository list items logic
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
class RepositoryAdapter(
    private val clickListener: (Repository) -> Unit
) : ListAdapter<Repository, RepositoryAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
            oldItem == newItem
    }) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Repository) {
            itemView.apply {

                name.text = item.name
                author.text = item.author
                description.text = item.description
                current_period_stars.text = item.currentPeriodStars
                main_content.setOnClickListener { clickListener(item) }
            }
        }
    }
}