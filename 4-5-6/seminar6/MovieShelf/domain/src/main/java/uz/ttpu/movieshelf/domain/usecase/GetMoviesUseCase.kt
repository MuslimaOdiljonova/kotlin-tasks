package uz.ttpu.movieshelf.domain.usecase

import uz.ttpu.movieshelf.domain.model.Movie
import uz.ttpu.movieshelf.domain.model.MoviesResult
import uz.ttpu.movieshelf.domain.repository.MovieRepository

// In Task 5 this use case took MovieRepositoryImpl, so the domain imported a class from the data
// layer. Domain depended on data, which is an outer layer, and that contradicts the Dependency Rule
// because dependencies must point inward. In Task 6 I fixed it with the MovieRepository interface.
class GetMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(): MoviesResult {
        val result = repository.getMovies()
        return result.copy(
            movies = result.movies.sortedWith(
                compareByDescending<Movie> { it.rating }.thenBy { it.title }
            )
        )
    }
}
