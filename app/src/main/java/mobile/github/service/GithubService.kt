package mobile.github.service

import mobile.github.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * T-mobile-github
 *
 * Created by bedoy on 2019-09-24.
 */
interface GithubService {
    @GET("users/{nickname}")
    suspend fun getUser(
        @Path("nickname") nickname: String
    ) : UserDetail

    @GET("users")
    suspend fun getUserList(

    ) : List<User>

    @GET("/search/users")
    suspend fun getUserList(
        @Query("q") query: String
    ) : UserQuery

    @GET("users/{nickname}/repos")
    suspend fun getRepos(
        @Path("nickname") nickname: String
    ) : List<Repo>

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("user") user: String,
        @Query("q") query: String
    ) : RepoQuery
}