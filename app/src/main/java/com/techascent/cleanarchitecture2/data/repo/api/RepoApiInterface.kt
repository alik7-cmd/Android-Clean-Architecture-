package com.techascent.cleanarchitecture2.data.repo.api

import com.techascent.cleanarchitecture2.data.repo.dto.ReposItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RepoApiInterface {
    @GET("/users/{user_id}/repos")
    suspend fun getRepos(@Path("user_id") userid : String, @QueryMap map : Map<String, String>) : List<ReposItem>
}