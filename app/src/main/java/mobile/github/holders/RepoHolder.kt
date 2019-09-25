package mobile.github.holders

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.repo_item.*
import mobile.github.BaseHolder
import mobile.github.models.Repo

class RepoHolder(override val containerView: View) : BaseHolder(containerView) {
    @SuppressLint("SetTextI18n")
    override fun reload(source: Any) {
        source as Repo

        repo_item_name.text = source.name
        repo_item_forks.text = "${source.forks_count} forks"
        repo_item_stars.text = "${source.stargazers_count} stars"
    }

}