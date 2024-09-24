package com.ismailmesutmujde.kotlindictionaryappretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordsResponse (@SerializedName("kelimeler")
                          @Expose
                          var words : List<Words>,
                          @SerializedName("success")
                          @Expose
                          var success:Int) {
}