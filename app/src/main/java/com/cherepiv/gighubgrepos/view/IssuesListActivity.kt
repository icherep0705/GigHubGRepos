package com.cherepiv.gighubgrepos.view

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.domain.RetrofitInstance
import com.cherepiv.gighubgrepos.model.GRepoIssue
import com.cherepiv.gighubgrepos.view.adapters.GRepoIssuesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IssuesListActivity : AppCompatActivity() {

    private val TAG = IssuesListActivity::class.java.canonicalName
    private var issuesRecycler: RecyclerView? = null
    private var issuesQuery: String = ""
    private var allReposList: List<GRepoIssue>? = null
    val reposAdapter = GRepoIssuesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_list)
        init()

        val issueUrl = intent?.getStringExtra("issue_url")
        val list = issueUrl?.split("{")
        list?.let {
            if (it.isNotEmpty()) {
                issuesQuery = it[0].substring(it[0].indexOf("google/") + "google/".length, it[0].indexOf("/issues"))

            }
        }



        RetrofitInstance.apiClient.getRepoIssues(issuesQuery).enqueue(object : Callback<List<GRepoIssue>> {
            override fun onFailure(call: Call<List<GRepoIssue>>, t: Throwable) {
                //TODO error/retry
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<GRepoIssue>>, response: Response<List<GRepoIssue>>) {
                if (response.isSuccessful)
                    response.body()?.let { initRecycler(it) }
                else {
                    //TODO error/retry
                }
            }
        })
    }

    private fun init() {

        (findViewById<RadioGroup>(R.id.radio_group)).apply {
            setOnCheckedChangeListener { radioGroup, idRes ->
                when (idRes) {
                    R.id.issues_all -> {
                        reposAdapter.repos = allReposList
                        reposAdapter.notifyDataSetChanged()
                    }
                    R.id.issues_closed -> {
                        val closed = allReposList?.filter { it.state.equals("closed", true)}
                        reposAdapter.repos = closed
                        reposAdapter.notifyDataSetChanged()
                    }

                    R.id.issues_open -> {
                        val open = allReposList?.filter { it.state.equals("open", true) }
                        reposAdapter.repos = open
                        reposAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun initRecycler(repos: List<GRepoIssue>){
        allReposList = repos
        issuesRecycler = findViewById(R.id.issues_container)
        issuesRecycler?.let {
            it.layoutManager = LinearLayoutManager(this@IssuesListActivity)
            it.setHasFixedSize(true)
            it.addItemDecoration(DividerItemDecoration(this@IssuesListActivity, RecyclerView.VERTICAL))
            reposAdapter.repos = repos
            it.adapter = reposAdapter
        }
    }
}
