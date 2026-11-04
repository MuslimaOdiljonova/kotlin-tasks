package uz.ttpu.movieshelf.data.local

import android.content.Context
import uz.ttpu.movieshelf.data.remote.MovieDto

class SharedPrefsMovieLocalDataSource(context: Context) : MovieLocalDataSource {

    private val prefs = context.getSharedPreferences("movieshelf", Context.MODE_PRIVATE)
    private var cached: List<MovieDto>? = null

    override suspend fun getCachedMovies(): List<MovieDto>? = cached

    override suspend fun saveMovies(movies: List<MovieDto>) {
        cached = movies
    }

    override suspend fun getFavoriteIds(): Set<Int> =
        prefs.getStringSet("favorites", emptySet()).orEmpty().map { it.toInt() }.toSet()

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        val favorites = prefs.getStringSet("favorites", emptySet()).orEmpty().toMutableSet()
        if (favorite) favorites.add(id.toString()) else favorites.remove(id.toString())
        prefs.edit().putStringSet("favorites", favorites).apply()
    }
}
