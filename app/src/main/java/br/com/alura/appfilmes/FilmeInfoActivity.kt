package br.com.alura.appfilmes

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.appfilmes.database.FavoritosDatabase
import br.com.alura.appfilmes.database.dao.FilmeFavoritoDao
import br.com.alura.appfilmes.databinding.FragmentNotificationsBinding
import br.com.alura.appfilmes.recyclerview.adapter.ListaFilmesAdapter
import br.com.alura.appfilmes.recyclerview.adapter.ListaFilmesFavoritosAdapter
import br.com.alura.appfilmes.webclient.model.Filme
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.io.Serializable


class FilmeInfoActivity : AppCompatActivity() {
    private lateinit var dao: FilmeFavoritoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme_info)

        dao = FavoritosDatabase.FavoritosDatabase.getInstance(this).getFilmeFavoritoDao()


        var filme = intent.getSerializableExtra("Filme") as Filme
        val imagem = findViewById<ImageView>(R.id.filme_info_imagem)
        Glide.with(imagem).load("https://image.tmdb.org/t/p/w500${filme.imagemVertical}").into(imagem)

        val filmeTitulo = findViewById<TextView>(R.id.filme_info_titulo)
        filmeTitulo.text = filme.titulo

        val filmeLancamento = findViewById<TextView>(R.id.filme_info_data_lancamento)
        filmeLancamento.text = filme.dataLancamento

        val filmeSinopse = findViewById<TextView>(R.id.filme_info_sinopse)
        filmeSinopse.text = filme.sinopse

        val filmeLingua = findViewById<TextView>(R.id.filme_info_lingua)
        filmeLingua.text = filme.linguagem

        val botaoFavoritos = findViewById<FloatingActionButton>(R.id.fab)
        botaoFavoritos.setImageResource(R.drawable.ic_baseline_favorite_branco);

        botaoFavoritos.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch{
                dao.salva(filme)
                Toast.makeText(this@FilmeInfoActivity, "Filme salvo nos favoritos", Toast.LENGTH_SHORT).show()
            }

        })



    }



}