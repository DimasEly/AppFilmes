package br.com.alura.appfilmes.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.appfilmes.databinding.FilmeItemBinding
import br.com.alura.appfilmes.webclient.model.Filme
import com.bumptech.glide.Glide

class ListaFilmesFavoritosAdapter {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    val listaFilmesFavoritosAdapter : MutableList<Filme> = mutableListOf()

    inner class ViewHolder (private val binding : FilmeItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(filme : Filme){
            val imageUrl = ("https://image.tmdb.org/t/p/w500${filme.imagemVertical}")
            Glide.with(binding.root).load(imageUrl).into(binding.filmeItemImagem)
        }


        init {
            itemView.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FilmeItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaFilmesFavoritosAdapter[position])
    }

    fun getItemCount(): Int {
        return listaFilmesFavoritosAdapter.size
    }



}