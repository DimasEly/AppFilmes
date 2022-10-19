package br.com.alura.appfilmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import br.com.alura.appfilmes.database.dao.FilmeFavoritoDao
import br.com.alura.appfilmes.webclient.model.Filme

class FavoritosDatabase {
    @Database(
        entities = [Filme::class],
        version = 1,
        exportSchema = false
    )
    abstract class FavoritosDatabase : RoomDatabase() {

        abstract fun getFilmeFavoritoDao(): FilmeFavoritoDao

        companion object factory {

            private lateinit var db: FavoritosDatabase

            fun getInstance(context: Context): FavoritosDatabase {
                if (::db.isInitialized) return db
                db = databaseBuilder(context, FavoritosDatabase::class.java, "FilmeFavorito.db")
                    .addMigrations(Migration(1, 2) {
                        it.execSQL("DROP TABLE IF EXISTS 'Filme'")
                        it.execSQL(
                            "CREATE TABLE IF NOT EXISTS Filme (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "title TEXT NOT NULL, " +
                                    "original_language TEXT NOT NULL, " +
                                    "poster_path TEXT NOT NULL, " +
                                    "overview TEXT NOT NULL, " +
                                    "release_date TEXT NOT NULL, " +
                                    "backdrop_path TEXT NOT NULL)"
                        );
                    }).allowMainThreadQueries().build()
                return db
            }
        }
    }


}