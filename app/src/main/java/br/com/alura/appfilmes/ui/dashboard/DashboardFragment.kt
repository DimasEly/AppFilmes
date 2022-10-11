package br.com.alura.appfilmes.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.appfilmes.FilmeInfoActivity
import br.com.alura.appfilmes.databinding.FragmentDashboardBinding
import br.com.alura.appfilmes.databinding.FragmentHomeBinding
import br.com.alura.appfilmes.recyclerview.adapter.ListaFilmesAdapter
import br.com.alura.appfilmes.ui.home.HomeViewModel
import br.com.alura.appfilmes.webclient.model.Filme
import br.com.alura.appfilmes.webclient.services.RetrofitInicializador
import java.io.Serializable

class DashboardFragment : Fragment() {

//    private var _binding: FragmentDashboardBinding? = null
//    private val arrayList = arrayListOf<Filme>()
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//        override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View {
//
//            _binding = FragmentDashboardBinding.inflate(inflater, container, false)
//            val root: View = binding.root
//
//            return root
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//
//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                    binding.progressBar.isVisible = arrayList.isEmpty()
//
//                    if(arrayList.isEmpty()){
//                        val response = RetrofitInicializador().notaService.buscaTodas(1)
//                        if (response.isSuccessful) {
//                            binding.progressBar.isVisible = false
//                            setAdapter()
//                        }
//                    } else{
//                        setAdapter()
//                    }
//                }
//            }
//        }
//
//        private fun setAdapter(){
//            binding.activityListaFilmesRecyclerview.adapter = ListaFilmesAdapter().apply {
//                populaAdapter(arrayList)
//                setOnItemClickListener(object : ListaFilmesAdapter.onItemClickListener{
//                    override fun onItemClick(position: Int) {
//                        val intent = Intent(context, FilmeInfoActivity::class.java)
//                        intent.putExtra("Filme", arrayList.get(position) as Serializable)
//                        startActivity(intent)
//                    }
//                })
//            }
//        }
//
//        override fun onDestroyView() {
//            super.onDestroyView()
//            _binding = null
//        }
//    }

    private var _binding: FragmentDashboardBinding? = null
    private val arrayList = arrayListOf<Filme>()
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private val binding get() = _binding!!

    private val mainAdapter by lazy {
        ListaFilmesAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this).get(DashboardViewModel::class.java)
    }


    override fun onResume() {
        super.onResume()
        setScrollView()
    }

    override fun onPause() {
        super.onPause()
        removeScrollView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieFromApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setObserver()

        binding.progressBar.isVisible = arrayList.isEmpty()
    }

    private fun setObserver(){
        viewModel.uiEventResponse.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            arrayList.addAll(it.resultado)
            mainAdapter.populaAdapter(arrayList)
            removeScrollView()
            setScrollView()
        }
    }

    private fun setAdapter(){
        binding.activityListaFilmesRecyclerview.adapter = mainAdapter
        mainAdapter.setOnItemClickListener(object : ListaFilmesAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(context, FilmeInfoActivity::class.java)
                intent.putExtra("Filme", arrayList.get(position) as Serializable)
                startActivity(intent)
            }
        })
    }

    private fun removeScrollView(){
        if(::scrollListener.isInitialized){
            binding.activityListaFilmesRecyclerview.removeOnScrollListener(scrollListener)
        }
    }

    private fun setScrollView(){
        scrollListener = object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0){
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemVisible = visibleItemCount + pastVisibleItems
                    val totalItemCount = layoutManager.itemCount
                    if (totalItemVisible >= totalItemCount) {
                        binding.progressBar.visibility = View.GONE
                        viewModel.uiEventResponse
                        removeScrollView()
                    }
                }
            }
        }
        binding.activityListaFilmesRecyclerview.addOnScrollListener(scrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}