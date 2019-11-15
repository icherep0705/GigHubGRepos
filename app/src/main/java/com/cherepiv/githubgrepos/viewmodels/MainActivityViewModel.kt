package com.cherepiv.githubgrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cherepiv.githubgrepos.model.GRepoPagingFactory
import com.cherepiv.githubgrepos.model.datamodels.GRepo

class MainActivityViewModel : ViewModel() {

    val liveDataPaged: LiveData<PagedList<GRepo>>

    init {
        val dataSourceFactory = GRepoPagingFactory()
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .setPageSize(20)
                .build()

        liveDataPaged = LivePagedListBuilder<Int, GRepo>(dataSourceFactory, config).build()
    }
}
