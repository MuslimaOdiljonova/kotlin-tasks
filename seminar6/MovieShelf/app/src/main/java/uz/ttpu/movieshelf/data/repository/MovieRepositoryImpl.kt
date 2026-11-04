package uz.ttpu.movieshelf.data.repository

import uz.ttpu.movieshelf.data.local.MovieLocalDataSource
import uz.ttpu.movieshelf.data.mapper.toDomain
import uz.ttpu.movieshelf.data.remote.MovieRemoteDataSource
import uz.ttpu.movieshelf.domain.model.MoviesResult
import uz.ttpu.movieshelf.domain.repository.MovieRepository
import java.io.IOException

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
) : MovieRepository {

    override suspend fun getMovies(): MoviesResult {
        val favoriteIds = local.getFavoriteIds()
        return try {
            val fresh = remote.fetchMovies()
            local.saveMovies(fresh)
            MoviesResult(fresh.map { it.toDomain(it.id in favoriteIds) }, isFromCache = false)
        } catch (e: IOException) {
            val cached = local.getCachedMovies() ?: throw e
            MoviesResult(cached.map { it.toDomain(it.id in favoriteIds) }, isFromCache = true)
        }
    }

    override suspend fun toggleFavorite(movieId: Int): Boolean {
        val newValue = movieId !in local.getFavoriteIds()
        local.setFavorite(movieId, newValue)
        return newValue
    }
}
