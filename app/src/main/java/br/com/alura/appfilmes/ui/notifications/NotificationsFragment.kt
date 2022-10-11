package br.com.alura.appfilmes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.appfilmes.database.FavoritosDatabase
import br.com.alura.appfilmes.database.dao.FilmeFavoritoDao
import br.com.alura.appfilmes.databinding.FragmentNotificationsBinding
import br.com.alura.appfilmes.recyclerview.adapter.ListaFilmesAdapter
import br.com.alura.appfilmes.webclient.model.Filme

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var dao: FilmeFavoritoDao
    private var arrayList : ArrayList<Filme> = ArrayList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recycler : RecyclerView
    private val mainAdapter by lazy {
        ListaFilmesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        dao = FavoritosDatabase.FavoritosDatabase.getInstance(inflater.context).getFilmeFavoritoDao()

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.favoritosRecyclerview
        recycler.adapter = mainAdapter
        arrayList = dao.busca() as ArrayList<Filme> /* = java.util.ArrayList<br.com.alura.appfilmes.webclient.model.Filme> */
        mainAdapter.populaAdapter(dao.busca())
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}