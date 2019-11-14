package com.cherepiv.gighubgrepos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.model.GRepoIssue

class GRepoIssuesAdapter(repos: List<GRepoIssue>): RecyclerView.Adapter<GRepoIssuesAdapter.GReposVH>() {

    private var repos: List<GRepoIssue> = repos

    override fun getItemCount() = repos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GReposVH(LayoutInflater.from(parent.context).inflate(R.layout.grepo_view_item, parent, false))

    override fun onBindViewHolder(holder: GReposVH, position: Int) {
        holder.bindView(repos[position])
    }


    class GReposVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//
//        val repoId: TextView? = itemView.findViewById(R.id.repo_id)
//        val repoDescription: TextView? = itemView.findViewById(R.id.repo_description)
//        val repoUrl: TextView? = itemView.findViewById(R.id.repo_url)
//        val repoIssuesCount: TextView? = itemView.findViewById(R.id.repo_issues_count)
//        val repoStatusOne: ImageView? = itemView.findViewById(R.id.repo_status_1)
//        val repoStatusTwo: ImageView? = itemView.findViewById(R.id.repo_status_2)
//        val repoStatusThree: ImageView? = itemView.findViewById(R.id.repo_status_3)
//        val repoStatusFour: ImageView? = itemView.findViewById(R.id.repo_status_4)

        init {
            itemView.setOnClickListener(this)
        }
        fun bindView(repo: GRepoIssue) {

//            repo.id?.let { repoId?.text = it.toString() }
//            repo.description?.let { repoDescription?.text = it }
//            repo.url?.let { repoUrl?.text = it.toString() }
//            repo.openIssuesCount?.let { repoIssuesCount?.text = it.toString() }
//
//            repo.disabled?.let {
//                if (!it) {
//                    repoStatusOne?.setImageResource(R.drawable.status_enable)
//                    repoStatusTwo?.setImageResource(R.drawable.status_enable)
//                    repoStatusThree?.setImageResource(R.drawable.status_enable)
//                    repoStatusFour?.setImageResource(R.drawable.status_enable)
//                } else {
//                    repoStatusOne?.setImageResource(R.drawable.status_disable)
//                    repoStatusTwo?.setImageResource(R.drawable.status_disable)
//                    repoStatusThree?.setImageResource(R.drawable.status_disable)
//                    repoStatusFour?.setImageResource(R.drawable.status_disable)
//                }
//
//            }
        }

        override fun onClick(v: View?) {
//            Intent(v?.context, IssuesListActivity::class.java).apply {
//                v?.context?.startActivity(this)
//            }
        }
    }
}