package com.ismailmesutmujde.kotlindictionaryappretrofit.dao

import com.ismailmesutmujde.kotlindictionaryappretrofit.model.WordsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WordsDaoInterface {

    @GET("sozluk/tum_kelimeler.php")
    fun allWords(): Call<WordsResponse>

    @POST("sozluk/kelime_ara.php")
    @FormUrlEncoded
    fun searchWord(@Field("ingilizce") english:String) : Call<WordsResponse>

}