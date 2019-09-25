package mobile.github.repository

import mobile.github.RetrofitHelper
import mobile.github.models.User
import mobile.github.service.GithubService


/**
 * GithubApp
 *
 * Created by bedoy on 2019-09-10.
 */
object UserListRepository {

    private val service by lazy {
        RetrofitHelper.createService(GithubService::class.java)
    }

    suspend fun requestUserList(keyword: String): List<User> {
        try {
            val userList: List<User> = if (keyword.isNotEmpty()){
                service.getUserList(query = keyword).items
            }else{
                service.getUserList()
            }

            //userList.forEach {
            //    val repos = service.getRepos(it.login)
            //
            //    it.repos = repos
            //}
            return userList
        }catch (e: Exception){
            e.printStackTrace()
            return ArrayList()
        }
    }
}