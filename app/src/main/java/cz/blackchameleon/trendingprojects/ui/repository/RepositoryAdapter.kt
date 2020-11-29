package cz.blackchameleon.trendingprojects.ui.repository

import android.graphics.*
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
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

                name.apply {
                    text = (HtmlCompat.fromHtml(
                        resources.getString(
                            R.string.author_repo_name,
                            item.author,
                            item.url,
                            item.name
                        ), HtmlCompat.FROM_HTML_MODE_LEGACY
                    ) as Spannable)
                    movementMethod = LinkMovementMethod.getInstance()
                }
                description.text = item.description
                val periodInt = 1
                val period = when (periodInt) {
                    1 -> resources.getString(R.string.today)
                    2 -> resources.getString(R.string.weekly)
                    3 -> resources.getString(R.string.monthly)
                    else -> resources.getString(R.string.today)
                }
                current_period_stars.text = resources.getString(R.string.current_stars, item.currentPeriodStars, period)
                overall_stars.text = resources.getString(R.string.overall_stars, item.stars)
                val color = if (item.languageColor.isBlank() || item.languageColor.isEmpty()) {
                    Color.TRANSPARENT
                } else {
                    Color.parseColor(item.languageColor)
                }
                language_color.background.setTint(color)
                language.text = item.language
                main_content.setOnClickListener { clickListener(item) }
            }
        }
    }
}