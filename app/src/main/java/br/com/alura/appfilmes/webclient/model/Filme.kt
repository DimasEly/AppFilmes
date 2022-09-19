package br.com.alura.appfilmes.webclient.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Filme (
        @SerializedName("id") val id: Int,
        @SerializedName("tittle") val titulo: String,
        @SerializedName("release_data") val dataLancamento: String,
        @SerializedName("original_lenguage") val linguagem: String,
        @SerializedName("overview") val sinopse: String,
        @SerializedName("poster_path") val imagemVertical: String
        ) : Serializable
