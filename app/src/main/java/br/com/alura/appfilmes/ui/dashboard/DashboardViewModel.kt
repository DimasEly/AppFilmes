package br.com.alura.appfilmes.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.appfilmes.FilmeResposta
import br.com.alura.appfilmes.webclient.services.RetrofitInicializador
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _uiEventResponse = MutableLiveData<FilmeResposta>()
    val uiEventResponse: LiveData<FilmeResposta>
        get() = _uiEventResponse

    private var currentPage: Int = 1

    fun getMovieFromApi() = viewModelScope.launch{
        val response = RetrofitInicializador().notaService.buscaPopular()

        if(response.isSuccessful){
            _uiEventResponse.postValue(response.body())
        }
    }
}