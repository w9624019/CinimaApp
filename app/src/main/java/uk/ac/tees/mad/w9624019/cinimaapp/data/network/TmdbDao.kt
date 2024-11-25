package uk.ac.tees.mad.w9624019.cinimaapp.data.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.Movie
import io.reactivex.rxjava3.core.Single


@Dao
interface TmdbDao {

    @Query("SELECT * FROM Favorite")
    suspend fun getFavorite(): List<Movie>

    @Query("SELECT * FROM Favorite WHERE id IN (:movieId)")
    fun isMovieExists(movieId: Int): Single<Movie>

    @Query("SELECT * FROM Favorite WHERE title LIKE :searchTitle || '%'")
    suspend fun findMoviesByTitle(searchTitle: String): List<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie) : Long

    @Delete
    suspend fun deleteMovie(movie: Movie)



}