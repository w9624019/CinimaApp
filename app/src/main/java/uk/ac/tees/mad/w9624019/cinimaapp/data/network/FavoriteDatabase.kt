package uk.ac.tees.mad.w9624019.cinimaapp.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase: RoomDatabase() {

    abstract fun daoInterface(): TmdbDao
}