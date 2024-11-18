package uk.ac.tees.mad.w9624019.cinimaapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import uk.ac.tees.mad.w9624019.cinimaapp.network.TmdbDao
import uk.ac.tees.mad.w9624019.cinimaapp.network.TmdbService
import uk.ac.tees.mad.w9624019.cinimaapp.paging.FavMoviePagingSource
import uk.ac.tees.mad.w9624019.cinimaapp.paging.MoviePagingSource
import uk.ac.tees.mad.w9624019.cinimaapp.utils.QueryType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.w9624019.cinimaapp.models.Movie
import uk.ac.tees.mad.w9624019.cinimaapp.models.MovieDetail
import javax.inject.*

@Singleton
class MovieRepository @Inject constructor(private val tmdbService: TmdbService, private val tmdbDao: TmdbDao) {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(tmdbService, "8bc098faec5fcf7e3076859d91f4a5ab",
                QueryType.POPULAR,"") }
        ).flow
    }

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(tmdbService, "8bc098faec5fcf7e3076859d91f4a5ab",
                QueryType.UPCOMING,"") }
        ).flow
    }

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(tmdbService, "8bc098faec5fcf7e3076859d91f4a5ab",
                QueryType.NOWPLAYING,"") }
        ).flow
    }

    fun getMovieDetail(movieId:Int) : Single<MovieDetail> {
        return tmdbService.getMovieDetail(movieId,"8bc098faec5fcf7e3076859d91f4a5ab")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun searchMovie(query: String) : Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(tmdbService, "8bc098faec5fcf7e3076859d91f4a5ab",
                QueryType.SEARCH,query) }
        ).flow
    }

    //dao
    fun isMovieInDao(movieId: Int) : Single<Movie> {
        return tmdbDao.isMovieExists(movieId = movieId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    suspend fun addMovieToFavorites(movie: Movie) {
        withContext(Dispatchers.IO) {
            tmdbDao.insertMovie(movie)
        }
    }

    suspend fun deleteMovieFromFavorites(movie: Movie) {
        withContext(Dispatchers.IO) {
            tmdbDao.deleteMovie(movie)
        }
    }

    fun getFavMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 1,enablePlaceholders = false,prefetchDistance = 10,),
            pagingSourceFactory = { FavMoviePagingSource(tmdbDao,"") }
        ).flow
    }

    fun searchMovieInDAO(query: String) : Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { FavMoviePagingSource(tmdbDao,query) }
        ).flow
    }
}
