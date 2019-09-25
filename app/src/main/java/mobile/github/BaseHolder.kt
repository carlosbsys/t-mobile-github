package mobile.github

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
abstract class BaseHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    abstract fun reload(source: Any)
}