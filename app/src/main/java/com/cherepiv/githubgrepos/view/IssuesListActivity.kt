package com.cherepiv.githubgrepos.view

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.githubgrepos.view.adapters.GRepoIssuesAdapter
import com.cherepiv.githubgrepos.viewmodels.IssuesListActivityViewModel

class IssuesListActivity : AppCompatActivity() {

    private val TAG = IssuesListActivity::class.java.canonicalName
    private var issuesRecycler: RecyclerView? = null
    private var issuesQuery: String = ""
    private val reposAdapter = GRepoIssuesAdapter()
    private var viewModel : IssuesListActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_list)
        viewModel = ViewModelProviders.of(this).get(IssuesListActivityViewModel::class.java)
        extractString()
        init()
        viewModel?.getData(issuesQuery, null)?.observe( this, Observer {
            reposAdapter.submitList(it)
            issuesRecycler?.adapter = reposAdapter
            reposAdapter.notifyDataSetChanged()
        })
    }

    private fun extractString(): String {
        val issueUrl = intent?.getStringExtra("issue_url")
        val list = issueUrl?.split("{")
        list?.let {
            if (it.isNotEmpty()) {
                issuesQuery = it[0].substring(it[0].indexOf("google/") + "google/".length, it[0].indexOf("/issues"))
            }
        }
        return issuesQuery
    }

    private fun init() {
        initRecycler()
        (findViewById<RadioGroup>(R.id.radio_group)).apply {
            setOnCheckedChangeListener { radioGroup, idRes ->
                when (idRes) {
                    R.id.issues_all -> {
                        viewModel?.getData(issuesQuery, null)?.observe( this@IssuesListActivity, Observer {
                            reposAdapter.submitList(it)
                            reposAdapter.notifyDataSetChanged()
                        })
                    }
                    R.id.issues_closed -> {
                        viewModel?.getData(issuesQuery, "close")?.observe( this@IssuesListActivity, Observer {
                            reposAdapter.submitList(it)
                            reposAdapter.notifyDataSetChanged()
                        })
                    }
                    R.id.issues_open -> {
                        viewModel?.getData(issuesQuery, "open")?.observe( this@IssuesListActivity, Observer {
                            reposAdapter.submitList(it)
                            reposAdapter.notifyDataSetChanged()
                        })
                    }
                }
            }
        }
    }

    private fun initRecycler(){
        issuesRecycler = findViewById(R.id.issues_container)
        issuesRecycler?.let {
            it.layoutManager = LinearLayoutManager(this@IssuesListActivity)
            it.setHasFixedSize(true)
            it.addItemDecoration(DividerItemDecoration(this@IssuesListActivity, RecyclerView.VERTICAL))
        }
    }
}
