package com.cherepiv.gighubgrepos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                //TODO retry logic

                Log.d(TAG, t.message)
            }

            override fun onResponse(call: Call<List<GRepo>>, response: Response<List<GRepo>>) {
                Log.d(TAG, response.body()?.size.toString())
                response.body()?.let { initRecycler(it) }
            }
        })
    }

    private fun initRecycler(repos: List<GRepo>){
        reposContainer?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            adapter = GReposAdapter(repos)
        }
    }
}
