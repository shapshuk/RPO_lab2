package com.example.lab2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2.retrofit.RetrofitService
import com.example.lab2.retrofit.RssItem
import kotlinx.coroutines.launch

class RssViewModel(private val retrofitService: RetrofitService) : ViewModel() {

    private val _rssItems = MutableLiveData<List<RssItem>>()
    val rssItems = _rssItems

    private val _fetchingError = MutableLiveData<String>()
    val fetchingError = _fetchingError

    fun fetchRssData(url: String) {
        viewModelScope.launch {
            val response = retrofitService.getData(url)
            if (response.isSuccessful) {
                _rssItems.value = response.body()!!.channel!!.items!!
            }
            else {
                _fetchingError.value = response.errorBody().toString()
            }
        }
    }
}