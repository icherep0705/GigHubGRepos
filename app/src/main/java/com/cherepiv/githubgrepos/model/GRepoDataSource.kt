package com.cherepiv.githubgrepos.model

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.cherepiv.githubgrepos.domain.RetrofitInstance
import com.cherepiv.githubgrepos.model.datamodels.GRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GRepoDataSource : PageKeyedDataSource<Int, GRepo>() {

    private val apiClient = RetrofitInstance.apiClient
    private val TAG = GRepoDataSource::class.java.canonicalName
    private var errorCount = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GRepo>) {
        if (errorCount < 4) {

            apiClient.getRepos(1, params.requestedLoadSize).enqueue(object : Callback<List<GRepo>> {
                override fun onFailure(call: Call<List<GRepo>>, t: Throwable) {
                    t.printStackTrace()
                    //TODO implement: check connection/show error dialog
                }

                override fun onResponse(call: Call<List<GRepo>>, response: Response<List<GRepo>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onResult(it, null, 2)
                            errorCount = 0
                        }
                    } else {
                        //TODO show error dialog
                        errorCount ++
                        loadInitial(params, callback)
                        Log.d(TAG, response.errorBody()?.string())
                    }
                }
            })
        } else {
            //TODO show error dialog
            Log.d(TAG, "Number of trials:$errorCount")
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GRepo>) {
        if (errorCount < 4) {
            apiClient.getRepos(params.key, params.requestedLoadSize).enqueue(object : Callback<List<GRepo>> {
                override fun onFailure(call: Call<List<GRepo>>, t: Throwable) {
                    t.printStackTrace()
                    //TODO error dialog
                }

                override fun onResponse(call: Call<List<GRepo>>, response: Response<List<GRepo>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onResult(it, params.key + 1)
                            errorCount = 0
                        }
                    } else {
                        //TODO show error dialog
                        errorCount ++
                        loadAfter(params, callback)
                        Log.d(TAG, response.errorBody()?.string())
                    }
                }
            })
        } else {
            //TODO show error dialog
            Log.d(TAG, "Number of trials:$errorCount")
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GRepo>) {}
}

class GRepoPagingFactory : DataSource.Factory<Int, GRepo>() {
    override fun create() = GRepoDataSource()
}