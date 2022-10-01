package br.com.alura.appfilmes.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.alura.appfilmes.FilmeInfoActivity
import br.com.alura.appfilmes.databinding.FragmentHomeBinding
import br.com.alura.appfilmes.recyclerview.adapter.ListaFilmesAdapter
import br.com.alura.appfilmes.webclient.model.Filme
import br.com.alura.appfilmes.webclient.services.RetrofitInicializador
import java.io.Serializable

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.progressBar.isVisible = true
                val response = RetrofitInicializador().notaService.buscaTodas()
                RetrofitInicializador().notaService.buscaTodas()
                binding.progressBar.isVisible = false
                if (response.isSuccessful) {
                    binding.activityListaNotasRecyclerview.adapter = ListaFilmesAdapter().apply {
                        val listaFilme : List<Filme> = response.body()?.resultado ?: emptyList()
                        populaAdapter(listaFilme)
                        setOnItemClickListener(object : ListaFilmesAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                            val intent = Intent(context, FilmeInfoActivity::class.java)
                            intent.putExtra("Filme", listaFilme.get(position) as Serializable)
                                startActivity(intent)
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}