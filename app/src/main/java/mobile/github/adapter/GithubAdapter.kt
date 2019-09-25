package mobile.github.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mobile.github.BaseHolder

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
class GithubAdapter : RecyclerView.Adapter<BaseHolder>() {
    val dataModel = ArrayList<Any>()
    var listener : OnGithubAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {

    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {

    }

    interface OnGithubAdapterListener {
        fun onSelectedItem(source: Any)
    }
}