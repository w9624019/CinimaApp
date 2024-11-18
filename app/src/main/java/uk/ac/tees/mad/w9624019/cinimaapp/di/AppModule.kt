package uk.ac.tees.mad.w9624019.cinimaapp.di

import android.content.Context
import androidx.room.Room
import uk.ac.tees.mad.w9624019.cinimaapp.network.TmdbDao
import uk.ac.tees.mad.w9624019.cinimaapp.network.TmdbService
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.w9624019.cinimaapp.network.FavoriteDatabase
import uk.ac.tees.mad.w9624019.cinimaapp.repositories.MovieRepository
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideTmdbService(): TmdbService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(TmdbService::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoriteDB(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            "favorite_movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideFavortieDao(database: FavoriteDatabase): TmdbDao {
        return database.daoInterface()
    }



    @Provides
    @Singleton
    fun provideMovieRepository(tmdbService: TmdbService, tmdbDao: TmdbDao): MovieRepository {
        return MovieRepository(tmdbService, tmdbDao)
    }
}