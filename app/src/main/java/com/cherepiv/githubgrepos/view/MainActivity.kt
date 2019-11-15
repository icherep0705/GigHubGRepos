package com.cherepiv.githubgrepos.view

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.githubgrepos.view.adapters.GReposAdapter
import com.cherepiv.githubgrepos.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.canonicalName
    private var recyclerView: RecyclerView? = null
    private var progressBar: LinearLayout? = null
    private var viewModel : MainActivityViewModel? = null
    private val reposAdapter = GReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        progressBar = findViewById(R.id.progress_container)
        progressBar?.visibility = View.VISIBLE
        initRecycler()

        viewModel?.liveDataPaged?.observe(this, Observer {
            reposAdapter.submitList(it)
            recyclerView?.adapter = reposAdapter
            reposAdapter.notifyDataSetChanged()
            Handler().postDelayed({ progressBar?.visibility = View.GONE }, 2000)
        })
    }

    private fun initRecycler(){
        recyclerView = findViewById(R.id.repos_container)
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity)
            it.setHasFixedSize(true)
            it.addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        }
    }
}
