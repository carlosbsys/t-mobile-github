package mobile.github.models

open class User {
    val login = ""
    val id = -1
    val avatar_url = ""
    val url = ""
    val type = ""
    val name = ""
    var repos : List<Repo> = ArrayList()

    fun repoCount (): Int {
        return repos.size
    }
}