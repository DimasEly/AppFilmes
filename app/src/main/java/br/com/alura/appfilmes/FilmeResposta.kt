package br.com.alura.appfilmes

import br.com.alura.appfilmes.webclient.model.Filme
import com.google.gson.annotations.SerializedName

data class FilmeResposta (
    @SerializedName("results") val resultado: List<Filme>,
    @SerializedName("page") val pagina: Int,
    @SerializedName("total_pages") val total_paginas: Int,
    @SerializedName("total_results") val total_resultados: Int
        )
