package com.cherepiv.githubgrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cherepiv.githubgrepos.model.IssuesRepoListPagingFactory
import com.cherepiv.githubgrepos.model.datamodels.GRepoIssue

class IssuesListActivityViewModel : ViewModel() {

    fun getData(query: String, state: String?): LiveData<PagedList<GRepoIssue>> {
        val dataSourceFactory = IssuesRepoListPagingFactory(query, state)
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(5)
                .build()

        return LivePagedListBuilder<Int, GRepoIssue>(dataSourceFactory, config).build()
    }
}