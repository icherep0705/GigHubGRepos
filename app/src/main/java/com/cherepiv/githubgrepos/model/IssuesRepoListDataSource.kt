package com.cherepiv.githubgrepos.model

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.cherepiv.githubgrepos.domain.RetrofitInstance
import com.cherepiv.githubgrepos.model.datamodels.GRepoIssue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssuesRepoListDataSource(var queury: String, var state: String?) : PageKeyedDataSource<Int, GRepoIssue>() {

    private val TAG = IssuesRepoListDataSource::class.java.canonicalName
    private val apiClient = RetrofitInstance.apiClient
    private var errorCount = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GRepoIssue>) {

        if (errorCount < 4) {
            apiClient.getRepoIssues(queury, 1, params.requestedLoadSize, state).enqueue(object : Callback<List<GRepoIssue>> {

                override fun onFailure(call: Call<List<GRepoIssue>>, t: Throwable) {
                    t.printStackTrace()
                    //TODO implement: check connection/show error dialog
                }

                override fun onResponse(call: Call<List<GRepoIssue>>, response: Response<List<GRepoIssue>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onResult(it, null, 2)
                            errorCount = 0
                        }
                    } else {
                        //TODO error dialog
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GRepoIssue>) {
        if (errorCount < 4) {
            apiClient.getRepoIssues(queury, params.key, params.requestedLoadSize, state).enqueue(object : Callback<List<GRepoIssue>> {
                override fun onFailure(call: Call<List<GRepoIssue>>, t: Throwable) {
                    t.printStackTrace()
                    //TODO implement: check connection/show error dialog
                }

                override fun onResponse(call: Call<List<GRepoIssue>>, response: Response<List<GRepoIssue>>) {
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GRepoIssue>) {}
}

class IssuesRepoListPagingFactory(var queury: String, var state: String?) : DataSource.Factory<Int, GRepoIssue>() {
    override fun create() = IssuesRepoListDataSource(queury, state)
}