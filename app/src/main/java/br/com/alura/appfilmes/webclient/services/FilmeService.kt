package br.com.alura.appfilmes.webclient.services

import br.com.alura.appfilmes.FilmeResposta
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeService {
    @GET ("movie/popular")
    suspend fun buscaTodas(@Query("api_key") api_key : String = "9106a44c761c36bbb02f24c16958a56a"): Response<FilmeResposta>
}