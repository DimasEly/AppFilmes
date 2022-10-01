package br.com.alura.appfilmes.webclient.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Filme (
        @SerializedName("id") val id: Int,
        @SerializedName("title") val titulo: String,
        @SerializedName("release_date") val dataLancamento: String,
        @SerializedName("original_language") val linguagem: String,
        @SerializedName("overview") val sinopse: String,
        @SerializedName("poster_path") val imagemVertical: String,
        @SerializedName("backdrop_path") val imagemPaisagem : String
        ) : Serializable {
        val filme: Filme
                get() = Filme(
                        id ?: 0,
                        titulo ?: "",
                        dataLancamento?: "",
                        linguagem ?: "",
                        sinopse ?: "",
                        imagemVertical ?: "",
                        imagemPaisagem ?: ""
                )
}

