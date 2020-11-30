package cz.blackchameleon.trendingprojects.ui.repository

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.blackchameleon.trendingprojects.R
import cz.blackchameleon.trendingprojects.extensions.setImage
import cz.blackchameleon.trendingprojects.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment that handles UI for repository detail
 * @suppress (Unchecked cast) applied on List adapter cast
 *
 * @see BaseFragment
 *
 * @author Karolina Klepackova on 27.11.2020.
 */
@Suppress("UNCHECKED_CAST")
class RepositoryDetailFragment : BaseFragment(R.layout.fragment_repository_detail) {

    override val viewModel: RepositoryDetailViewModel by viewModel { parametersOf(args) }

    private val args: RepositoryDetailFragmentArgs by navArgs()

    private val contributorAdapter: ContributorAdapter by lazy { ContributorAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler(
            contributor_list,
            contributorAdapter as ListAdapter<Any, RecyclerView.ViewHolder>
        )
        initObservers()
        setupListeners()
    }

    private fun initObservers() {
        viewModel.repository.observe(viewLifecycleOwner, {
            avatar.setImage(it.avatar)
            name.text = (HtmlCompat.fromHtml(
                resources.getString(
                    R.string.hypertext_name,
                    it.url,
                    it.name
                ), HtmlCompat.FROM_HTML_MODE_LEGACY
            ) as Spannable)
            name.movementMethod = LinkMovementMethod.getInstance()

            author.text = it.author
            description.text = it.description
            forks.text = it.forks
            overall_stars.text = it.stars
            language_color.background.setTint(
                if (it.languageColor.isBlank() || it.languageColor.isEmpty()) {
                    Color.TRANSPARENT
                } else {
                    Color.parseColor(it.languageColor)
                }
            )
            language.text = it.language

            contributorAdapter.submitList(it.builtBy)
            setLoadingVisibility(false)
        })
        viewModel.showEmptyState.observe(viewLifecycleOwner, {
            no_data_text.isVisible = true
            setLoadingVisibility(false)
        })
    }

    private fun setupListeners() {
        swipe_layout.setOnRefreshListener { viewModel.onSwipeReload() }
    }

    private fun setLoadingVisibility(visibility: Boolean) {
        swipe_layout.isRefreshing = visibility
        overlay.isVisible = visibility
    }
}