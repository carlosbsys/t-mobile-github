package mobile.github

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-25.
 */
object Utils {
    @SuppressLint("SimpleDateFormat")
    fun formatTime(_createAt: String): String? {
        var createdAt = _createAt
        createdAt = createdAt.replace("T", " ")
        createdAt = createdAt.replace("Z", " ")

        var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val date = format.parse(createdAt)
        format = SimpleDateFormat("dd MMM yyyy")

        return format.format(date)
    }
}
