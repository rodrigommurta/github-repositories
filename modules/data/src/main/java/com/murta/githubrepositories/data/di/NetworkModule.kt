package com.murta.githubrepositories.data.di

import com.murta.githubrepositories.data.features.pullrequests.PullRequestsService
import com.murta.githubrepositories.data.features.repositories.RepositoriesService
import com.murta.githubrepositories.data.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesPullRequestsService(retrofit: Retrofit): PullRequestsService {
        return retrofit.create(PullRequestsService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepositoriesService(retrofit: Retrofit): RepositoriesService {
        return retrofit.create(RepositoriesService::class.java)
    }
}