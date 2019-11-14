package com.cherepiv.gighubgrepos.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.model.GRepo
import com.cherepiv.gighubgrepos.view.IssuesListActivity

class GReposAdapter: RecyclerView.Adapter<GReposAdapter.GReposVH>() {

    override fun onBindViewHolder(holder: GReposVH, position: Int) {
        repos?.get(position)?.let { holder.bindView(it)}
    }

    var repos: List<GRepo>? = null

    override fun getItemCount() = if(repos?.size == null) 0 else repos?.size!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GReposVH(LayoutInflater.from(parent.context).inflate(R.layout.grepo_view_item, parent, false))

    class GReposVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val repoId: TextView? = itemView.findViewById(R.id.repo_id)
        private val repoDescription: TextView? = itemView.findViewById(R.id.repo_description)
        private val repoUrl: TextView? = itemView.findViewById(R.id.repo_url)
        private val repoIssuesCount: TextView? = itemView.findViewById(R.id.repo_issues_count)
        private val repoStatusOne: ImageView? = itemView.findViewById(R.id.repo_status_1)
        private val repoStatusTwo: ImageView? = itemView.findViewById(R.id.repo_status_2)
        private val repoStatusThree: ImageView? = itemView.findViewById(R.id.repo_status_3)
        private val repoStatusFour: ImageView? = itemView.findViewById(R.id.repo_status_4)
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
                    repoStatusOne?.setImageResource(R.drawable.status_enable)
                    repoStatusTwo?.setImageResource(R.drawable.status_enable)
                    repoStatusThree?.setImageResource(R.drawable.status_enable)
                    repoStatusFour?.setImageResource(R.drawable.status_enable)
                } else {
                    repoStatusOne?.setImageResource(R.drawable.status_disable)
                    repoStatusTwo?.setImageResource(R.drawable.status_disable)
                    repoStatusThree?.setImageResource(R.drawable.status_disable)
                    repoStatusFour?.setImageResource(R.drawable.status_disable)
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