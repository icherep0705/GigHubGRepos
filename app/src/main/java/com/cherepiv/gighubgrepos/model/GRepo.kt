package com.cherepiv.gighubgrepos.model

import com.google.gson.annotations.SerializedName

data class GRepo(
        val id: Long?,
        val description: String?,
        val disabled: Boolean?,
        val url: String?,
        @SerializedName("open_issues_count") val openIssuesCount: Int?,
        @SerializedName("issues_url") val issuesUrl: String?)
