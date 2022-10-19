package br.com.alura.appfilmes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alura.appfilmes.webclient.model.Filme

@Dao
interface FilmeFavoritoDao {
    @Insert(onConflict = REPLACE)
    fun salva(filme: Filme)

    @Query("SELECT * FROM Filme")
    fun busca(): List<Filme>?

    @Delete
    fun exclui(filme: Filme)


}