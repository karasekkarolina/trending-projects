package cz.blackchameleon.trendingprojects.ui.repository

import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.domain.Contributor
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.setImage
import kotlinx.android.synthetic.main.item_contributor.view.*

/**
 * @author Karolina Klepackova on 30.11.2020.
 */
class ContributorAdapter : ListAdapter<Contributor, ContributorAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Contributor>() {
        override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor) =
            oldItem == newItem
    }) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contributor, parent, false)
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Contributor) {
            itemView.apply {
                avatar.setImage(item.avatar)
                username.text = (HtmlCompat.fromHtml(
                    resources.getString(
                        R.string.hypertext_name,
                        item.href,
                        item.username
                    ), HtmlCompat.FROM_HTML_MODE_LEGACY
                ) as Spannable)
                username.movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }
}