package br.com.antoniojoseuchoa.appportfolio.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RepositoryGitHub {
    companion object{
        private val baseUrl = "https://api.github.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGitHub::class.java)
    }
}