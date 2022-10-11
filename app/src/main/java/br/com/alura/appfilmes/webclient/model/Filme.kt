package br.com.alura.appfilmes.webclient.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Filme (
        @PrimaryKey
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

