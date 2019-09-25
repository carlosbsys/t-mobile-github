package mobile.github.holders

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.profile_item.*
import mobile.github.BaseHolder
import mobile.github.models.UserDetail

class ProfileHolder (override val containerView: View) : BaseHolder(containerView) {
    @SuppressLint("SetTextI18n")
    override fun reload(source: Any) {
        source as UserDetail

        Glide.with(containerView)
            .load(source.avatar_url)
            .apply(RequestOptions.circleCropTransform())
            .into(profile_item_avatar)

        profile_item_nickname.text = source.login
        profile_item_location.text = source.location
        profile_item_join_date.text = "${source.created_at}\nJoined at"
        profile_item_email.text = source.email
        profile_item_followers.text = "${source.followers}\nfollowers"
        profile_item_following.text = "${source.following}\nfollowing"
    }
}