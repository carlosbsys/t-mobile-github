package mobile.github.repository

import mobile.github.RetrofitHelper
import mobile.github.models.Repo
import mobile.github.models.UserDetail
import mobile.github.service.GithubService
import java.lang.Exception

/**
 * GithubApp
 *
 * Created by bedoy on 2019-09-10.
 */
object ProfileRepository {
    private val service by lazy {
        RetrofitHelper.createService(GithubService::class.java)
    }

    suspend fun getProfile(user: String): UserDetail {
        return service.getUser(user)
    }

    suspend fun getRepos(user: String) : List<Repo>{
        return try {
            service.getRepos(user)
        }catch (e: Exception){
            ArrayList()
        }
    }

    suspend fun filterRepos(nickname: String, keyword: String): List<Repo> {
        return try {
            val repoQuery = service.getRepos(nickname, keyword)

            repoQuery.items
        }catch (e: Exception){
            ArrayList()
        }
    }
}