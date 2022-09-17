package br.com.alura.appfilmes.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.appfilmes.databinding.FilmeItemBinding
import br.com.alura.appfilmes.webclient.model.Filme
import com.bumptech.glide.Glide


class ListaFilmesAdapter : RecyclerView.Adapter<ListaFilmesAdapter.ViewHolder>(){

    val listaFilmesAdapter : MutableList<Filme> = mutableListOf()

    inner class ViewHolder (private val binding : FilmeItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(filme : Filme){
            val imageUrl = ("https://image.tmdb.org/t/p/w500${filme.imagemVertical}")
            Glide.with(binding.root).load(imageUrl).into(binding.filmeItemImagem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FilmeItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaFilmesAdapter[position])
    }

    override fun getItemCount(): Int {
        return listaFilmesAdapter.size
    }

    fun populaAdapter(listaPopulaAdapter : List<Filme>){
        listaFilmesAdapter.clear()
        listaFilmesAdapter.addAll(listaPopulaAdapter)
        notifyDataSetChanged()
    }
}
