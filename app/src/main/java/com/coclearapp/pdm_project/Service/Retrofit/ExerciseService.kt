package com.coclearapp.pdm_project.Service.Retrofit

import com.coclearapp.pdm_project.Room.Entity.Exercise
import com.google.android.gms.common.api.Response
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_BASE_URL = "https://pdmappapp.herokuapp.com/cocleappq/"

interface ExerciseService{

    @GET()
    fun getRepos(user: String): Deferred<Response<List<Exercise>>>

    companion object {

        var INSTANCE: ExerciseService? = null

        fun getGithubService(): ExerciseService {
            return if (INSTANCE != null) {
                INSTANCE!!
            } else {

                Retrofit
                    .Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(ExerciseService::class.java)
            }


        }
    }
}