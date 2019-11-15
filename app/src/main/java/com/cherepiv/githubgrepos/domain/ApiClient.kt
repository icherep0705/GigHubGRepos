package com.cherepiv.githubgrepos.domain

import com.cherepiv.githubgrepos.model.datamodels.GRepo
import com.cherepiv.githubgrepos.model.datamodels.GRepoIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

        @GET("users/google/repos")
        fun getRepos(
                @Query("page") page: Int?,
                @Query("per_page") perPage: Int): Call<List<GRepo>>


        //https://api.github.com/repos/google/access-bridge-explorer/issues?page=1&per_page=10&state=open
        @GET("repos/google/{repo}/issues")
        fun getRepoIssues(
                @Path("repo")repo: String,
                @Query("page") page: Int?,
                @Query("per_page") perPage: Int?,
                @Query("state") state: String?): Call<List<GRepoIssue>>
}