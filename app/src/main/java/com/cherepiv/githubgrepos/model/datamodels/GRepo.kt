package com.cherepiv.githubgrepos.model.datamodels

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class GRepo(
        val id: Long?,
        val description: String?,
        val disabled: Boolean?,
        val url: String?,
        @SerializedName("open_issues_count") val openIssuesCount: Int?,
        @SerializedName("issues_url") val issuesUrl: String?) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<GRepo> = object : DiffUtil.ItemCallback<GRepo>() {
            override fun areItemsTheSame(oldItem: GRepo, newItem: GRepo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GRepo, newItem: GRepo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
