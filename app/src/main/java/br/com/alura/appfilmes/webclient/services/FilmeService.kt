package br.com.alura.appfilmes.webclient.services

import br.com.alura.appfilmes.FilmeResposta
import retrofit2.Call
import retrofit2.http.GET

interface FilmeService {
    @GET ("movie/popular")
    suspend fun buscaTodas(): Call<FilmeResposta>
}