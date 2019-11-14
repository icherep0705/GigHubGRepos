package com.cherepiv.gighubgrepos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherepiv.gighubgrepos.R
import com.cherepiv.gighubgrepos.domain.RetrofitInstance
import com.cherepiv.gighubgrepos.model.GRepo
import com.cherepiv.gighubgrepos.view.adapters.GReposAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.canonicalName
    private var reposContainer: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reposContainer = findViewById(R.id.repos_container)


        RetrofitInstance.apiClient.getRepos(1, 100).enqueue(object : Callback<List<GRepo>>{
            override fun onFailure(call: Call<List<GRepo>>, t: Throwable) {
                //TODO error/retry
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<GRepo>>, response: Response<List<GRepo>>) {
                if (response.isSuccessful)
                    response.body()?.let { initRecycler(it) }
                else {
                    //TODO error/retry
                }
            }
        })
    }

    private fun initRecycler(repos: List<GRepo>){
        reposContainer?.let {
            it.layoutManager = LinearLayoutManager(this@MainActivity)
            it.setHasFixedSize(true)
            it.addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            val reposAdapter = GReposAdapter()
            reposAdapter.repos = repos
            it.adapter = reposAdapter
        }
    }
}
