package com.test.byju.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.byju.io.Constants
import com.test.byju.io.NetworkIoManager
import com.test.byju.io.dto.NewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NewsHeadlinesViewModel : ViewModel() {

    val newsHeadLines = MutableLiveData<NewsResponse>()
    val networksState = MutableLiveData<Int>()
    val disposable = CompositeDisposable()

    fun getHeadlines() {
        if (NetworkIoManager.isInternetAvailable()) {

            NetworkIoManager.fetchHeadlines("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = {
                    networksState.value = Constants.NTWORK_STATE_ERROR
                },
                    onSuccess = {
                        networksState.value = Constants.NTWORK_STATE_SUCCESS
                        newsHeadLines.value = it
                    })
                .addTo(disposable)

        } else {
            networksState.value = Constants.NTWORK_STATE_NO_INTERNET
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}