package ru.skillbranch.sbdelivery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.sbdelivery.data.datasource.remote.api.SBDeliveryApi
import ru.skillbranch.sbdelivery.data.datasource.remote.api.interceptor.AppendTokenInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRestApiClient(appendTokenInterceptor: AppendTokenInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(appendTokenInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sandbox.skill-branch.ru")
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): SBDeliveryApi{
        return retrofit.create(SBDeliveryApi::class.java)
    }
}