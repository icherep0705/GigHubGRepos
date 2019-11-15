package com.cherepiv.githubgrepos.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.githubgrepos.model.datamodels.GRepo
import com.cherepiv.githubgrepos.view.IssuesListActivity


class GReposAdapter : PagedListAdapter<GRepo, GReposAdapter.GReposVH>(GRepo.DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: GReposVH, position: Int) {
        getItem(position)?.let { holder.bindView(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GReposVH(LayoutInflater.from(parent.context).inflate(com.cherepiv.gighubgrepos.R.layout.grepo_view_item, parent, false))

    class GReposVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val repoId: TextView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_id)
        private val repoDescription: TextView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_description)
        private val repoUrl: TextView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_url)
        private val repoIssuesCount: TextView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_issues_count)
        private val repoStatusOne: ImageView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_status_1)
        private val repoStatusTwo: ImageView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_status_2)
        private val repoStatusThree: ImageView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_status_3)
        private val repoStatusFour: ImageView? = itemView.findViewById(com.cherepiv.gighubgrepos.R.id.repo_status_4)
        private var currentRepo: GRepo? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(repo: GRepo) {

            currentRepo = repo
            repo.id?.let { repoId?.text = it.toString() }
            repo.description?.let { repoDescription?.text = it }
            repo.url?.let { repoUrl?.text = it.toString() }
            repo.openIssuesCount?.let { repoIssuesCount?.text = it.toString() }

            repo.disabled?.let {
                if (!it) {
                    repoStatusOne?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_enable)
                    repoStatusTwo?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_enable)
                    repoStatusThree?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_enable)
                    repoStatusFour?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_enable)
                } else {
                    repoStatusOne?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_disable)
                    repoStatusTwo?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_disable)
                    repoStatusThree?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_disable)
                    repoStatusFour?.setImageResource(com.cherepiv.gighubgrepos.R.drawable.status_disable)
                }
            }
        }

        override fun onClick(v: View?) {
            Intent(v?.context, IssuesListActivity::class.java).apply {
                putExtra("issue_url", currentRepo?.issuesUrl)
                v?.context?.startActivity(this)
                adapterPosition
            }
        }
    }
}