package mobile.github.holders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.user_item.*
import mobile.github.BaseHolder
import mobile.github.models.User


class UserHolder(override val containerView: View) : BaseHolder(containerView) {
    override fun reload(source: Any) {
        source as User

        user_item_nickname.text = source.login
        user_item_repo.text = if (source.repoCount() == -1) "Loading" else "${source.repoCount()}"

        Glide.with(user_item_avatar)
            .load(source.avatar_url)
            .apply(RequestOptions.circleCropTransform())
            .into(user_item_avatar)
    }
}