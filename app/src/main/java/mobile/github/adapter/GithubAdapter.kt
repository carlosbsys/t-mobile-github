package mobile.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobile.github.BaseHolder
import mobile.github.R
import mobile.github.holders.ProfileHolder
import mobile.github.holders.RepoHolder
import mobile.github.holders.UserHolder
import mobile.github.models.User
import mobile.github.models.UserDetail

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
class GithubAdapter : RecyclerView.Adapter<BaseHolder>() {
    val dataModel = ArrayList<Any>()
    var listener : OnGithubAdapterListener? = null

    enum class TYPE{
        USER, USER_DETAIL, REPO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (viewType) {
            TYPE.USER.ordinal -> UserHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
            )
            TYPE.USER_DETAIL.ordinal-> ProfileHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.profile_item, parent, false)
            )
            else -> RepoHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun getItemViewType(position: Int): Int {
        if (dataModel[position]::class.java == User::class.java)
            return TYPE.USER.ordinal
        else if (dataModel[position]::class.java == UserDetail::class.java)
            return TYPE.USER_DETAIL.ordinal
        return TYPE.REPO.ordinal
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.reload(dataModel[position])
        holder.containerView.setOnClickListener {
            listener?.onSelectedItem(dataModel[position])
        }
    }

    interface OnGithubAdapterListener {
        fun onSelectedItem(source: Any)
    }
}