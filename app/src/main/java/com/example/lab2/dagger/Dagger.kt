package com.example.lab2.dagger

import com.example.lab2.retrofit.RetrofitClient
import com.example.lab2.retrofit.RetrofitService
import com.example.lab2.ui.RssFragment
import com.example.lab2.ui.RssViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: RssFragment)
    val rssViewModel: RssViewModel
    val retrofitService: RetrofitService
}

@Module
object AppModule {

    @Provides
    fun provideRetrofitService() : RetrofitService {
        return RetrofitClient.getClient("https://habr.com/ru/rss/").create(RetrofitService::class.java)
    }
    @Provides
    fun provideRssViewModel(retrofitService: RetrofitService) : RssViewModel {
        return RssViewModel(retrofitService)
    }
}