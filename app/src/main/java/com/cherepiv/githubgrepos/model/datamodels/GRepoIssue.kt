package com.cherepiv.githubgrepos.model.datamodels

import androidx.recyclerview.widget.DiffUtil

data class GRepoIssue(
        val id: Long?,
        val title: String?,
        val state: String?,
        val body: String?) {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<GRepoIssue> = object : DiffUtil.ItemCallback<GRepoIssue>() {
            override fun areItemsTheSame(oldItem: GRepoIssue, newItem: GRepoIssue): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GRepoIssue, newItem: GRepoIssue): Boolean {
                return oldItem == newItem
            }
        }
    }
}
