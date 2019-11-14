package com.cherepiv.gighubgrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.model.GRepoIssue

class GRepoIssuesAdapter: RecyclerView.Adapter<GRepoIssuesAdapter.GReposVH>() {

    var repos: List<GRepoIssue>? = null

    override fun getItemCount() = if(repos?.size == null) 0 else repos?.size!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GReposVH(LayoutInflater.from(parent.context).inflate(R.layout.grepo_issues_view_item, parent, false))

    override fun onBindViewHolder(holder: GReposVH, position: Int) {
        repos?.get(position)?.let { holder.bindView(it) }
    }

    class GReposVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repoId: TextView? = itemView.findViewById(R.id.repo_id)
        private val repoTitle: TextView? = itemView.findViewById(R.id.repo_title)
        private val repoState: TextView? = itemView.findViewById(R.id.repo_state)
        private val repoBody: TextView? = itemView.findViewById(R.id.repo_body)

        fun bindView(repo: GRepoIssue) {
            repo.id?.let { repoId?.text = it.toString() }
            repo.title?.let { repoTitle?.text = it }
            repo.state?.let {
                when (it) {
                    "open" -> {repoState?.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))}
                    "close" -> {repoState?.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))}
                }
                repoState?.text = it
            }
            repo.body?.let { repoBody?.text = it }
        }
    }
}