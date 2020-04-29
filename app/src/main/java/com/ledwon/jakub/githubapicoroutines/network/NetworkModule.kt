package com.ledwon.jakub.githubapicoroutines.network

import com.ledwon.jakub.githubapicoroutines.BuildConfig
import com.ledwon.jakub.networkmodule.RepoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModules = module {
    single { provideOkHttp() }
    single { provideMoshi() }
    single { provideRetrofit(get(), get()) }
    single { provideGithubApi(get()) }
}


private fun provideOkHttp() : OkHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

private fun provideMoshi() : MoshiConverterFactory = MoshiConverterFactory.create()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory
) =
    Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

private fun provideGithubApi(retrofit: Retrofit) = retrofit.create(RepoApi::class.java)


