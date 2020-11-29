package cz.blackchameleon.trendingprojects.ui.developer

import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Developer
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.removeUnderlines
import cz.blackchameleon.trendingprojects.extensions.setImage
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

                avatar.setImage(item.avatar)
                full_name.apply {
                    text = (HtmlCompat.fromHtml(
                        resources.getString(
                            R.string.url, item.name, item.url, item.username
                        ), HtmlCompat.FROM_HTML_MODE_LEGACY
                    ) as Spannable)
                    movementMethod = LinkMovementMethod.getInstance()
                }
                repository.apply {
                    text = (HtmlCompat.fromHtml(
                        resources.getString(
                            R.string.repository_url,
                            item.repo.url,
                            item.repo.name
                        ), HtmlCompat.FROM_HTML_MODE_LEGACY
                    ) as Spannable)
                    movementMethod = LinkMovementMethod.getInstance()
                }
                description.text = item.repo.description

                sponsor_url.apply {
                    text = (HtmlCompat.fromHtml(
                        resources.getString(
                            R.string.sponsor_url,
                            item.sponsorUrl
                        ), HtmlCompat.FROM_HTML_MODE_LEGACY
                    ) as Spannable).removeUnderlines()
                    movementMethod = LinkMovementMethod.getInstance()
                }
            }
        }
    }
}