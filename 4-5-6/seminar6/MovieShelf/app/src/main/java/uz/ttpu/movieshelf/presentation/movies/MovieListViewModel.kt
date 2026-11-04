// Task 15 analysis
//
// (1) Order of calls when I tap a heart:
// MovieItem (UI) -> MovieListViewModel.onFavoriteClick -> ToggleFavoriteUseCase.invoke
// -> MovieRepositoryImpl.toggleFavorite -> SharedPrefsMovieLocalDataSource.setFavorite
//
// (2) Dependency arrows between the same classes:
// MovieItem gets only a lambda and a Movie, MovieListViewModel -> ToggleFavoriteUseCase
// -> MovieRepository (interface in domain) <- MovieRepositoryImpl -> MovieLocalDataSource
// <- SharedPrefsMovieLocalDataSource
// The arrow from MovieRepositoryImpl to the MovieRepository interface points the opposite way to the
// call. The call goes from domain out to data, but in the source code data depends on domain, because
// the interface is owned by the domain and the implementation follows it. This is Dependency Inversion.
//
// (3) The use case cannot call Log.d because the domain is a pure Kotlin module and android.util.Log
// is not on its classpath. The clean way is an interface Logger declared in the domain and implemented
// in an outer layer, which is Dependency Inversion again.

package uz.ttpu.movieshelf.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.ttpu.movieshelf.domain.model.Movie
import uz.ttpu.movieshelf.domain.usecase.GetFavoriteMoviesUseCase
import uz.ttpu.movieshelf.domain.usecase.GetMoviesUseCase
import uz.ttpu.movieshelf.domain.usecase.ToggleFavoriteUseCase
import java.io.IOException

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(
        val movies: List<Movie>,
        val isFromCache: Boolean,
        val favoritesOnly: Boolean = false,
    ) : MovieListUiState
    data class Error(val message: String) : MovieListUiState
}

class MovieListViewModel(
    private val getMovies: GetMoviesUseCase,
    private val getFavoriteMovies: GetFavoriteMoviesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val state: StateFlow<MovieListUiState> = _state.asStateFlow()

    private var favoritesOnly = false
    private var loadJob: Job? = null

    init { load() }

    fun onRefresh() = load()

    fun onFavoritesOnlyChange(enabled: Boolean) {
        favoritesOnly = enabled
        load()
    }

    fun onFavoriteClick(movieId: Int) {
        viewModelScope.launch {
            val isFavorite = toggleFavorite(movieId)
            _state.update { current ->
                if (current is MovieListUiState.Success) {
                    val updated = current.movies.map {
                        if (it.id == movieId) it.copy(isFavorite = isFavorite) else it
                    }
                    current.copy(
                        movies = if (current.favoritesOnly) updated.filter { it.isFavorite } else updated
                    )
                } else {
                    current
                }
            }
        }
    }

    private fun load() {
        loadJob?.cancel()
        _state.value = MovieListUiState.Loading
        loadJob = viewModelScope.launch {
            try {
                val result = if (favoritesOnly) getFavoriteMovies() else getMovies()
                _state.value = MovieListUiState.Success(result.movies, result.isFromCache, favoritesOnly)
            } catch (e: IOException) {
                _state.value = MovieListUiState.Error("Can't load movies. Check your connection and try again.")
            }
        }
    }
}
