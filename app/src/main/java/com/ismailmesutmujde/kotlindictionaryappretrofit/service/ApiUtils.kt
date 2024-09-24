package com.ismailmesutmujde.kotlindictionaryappretrofit.service

import com.ismailmesutmujde.kotlindictionaryappretrofit.dao.WordsDaoInterface
import com.ismailmesutmujde.kotlindictionaryappretrofit.retrofit.RetrofitClient

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getWordsDaoInterface() : WordsDaoInterface {
            return  RetrofitClient.getClient(BASE_URL).create(WordsDaoInterface::class.java)
        }
    }
}