package com.cherepiv.gighubgrepos.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.model.GRepoIssue
import com.cherepiv.gighubgrepos.view.adapters.GRepoIssuesAdapter

class IssuesListActivity : AppCompatActivity() {

    private val TAG = IssuesListActivity::class.java.canonicalName
    private var issuesContainer: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_list)

        val issueUrl = intent?.getStringExtra("issue_url")
        Log.d(TAG, issueUrl)
//thub.com/repos/google/0x0g-2018-badge/issues

        val list = issueUrl?.split("{")
        list?.let {
            if (it.isNotEmpty()) {
                val substring = it[0].substring(it[0].indexOf("google/") + "google/".length)
            }
        }


//
//
//        issuesContainer = findViewById(R.id.repos_container)
//
//
//        RetrofitInstance.apiClient.getRepoIssues().enqueue(object : Callback<List<GRepoIssue>> {
//            override fun onFailure(call: Call<List<GRepoIssue>>, t: Throwable) {
//                //TODO retry logic
//
//                Log.d(TAG, t.message)
//            }
//
//            override fun onResponse(call: Call<List<GRepoIssue>>, response: Response<List<GRepoIssue>>) {
//                Log.d(TAG, response.body()?.size.toString())
//                response.body()?.let { initRecycler(it) }
//            }
//        })
    }

    private fun initRecycler(repos: List<GRepoIssue>){
        issuesContainer?.apply {
            layoutManager = LinearLayoutManager(this@IssuesListActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@IssuesListActivity, RecyclerView.VERTICAL))
            adapter = GRepoIssuesAdapter(repos)
        }
    }
}
