package com.cherepiv.gighubgrepos.domain

import com.cherepiv.gighubgrepos.model.GRepo
import com.cherepiv.gighubgrepos.model.GRepoIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

        @GET("users/google/repos")
        fun getRepos(
                @Query("page") page: Int?,
                @Query("per_page") perPage: Int): Call<List<GRepo>>

        @GET("repos/google/{repo}/issues")
        fun getRepoIssues(@Path("repo")repo: String): Call<List<GRepoIssue>>

}