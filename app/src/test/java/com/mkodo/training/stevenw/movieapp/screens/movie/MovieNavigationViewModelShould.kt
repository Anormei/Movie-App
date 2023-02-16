package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mkodo.training.stevenw.movieapp.MainCoroutineRule
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.models.Movie
import com.mkodo.training.stevenw.movieapp.models.MovieResults
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MovieNavigationViewModelShould {

    @InjectMockKs
    private lateinit var navigationViewModel: MovieNavigationViewModel

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `make a call on repository when loading trending movies`() {
        navigationViewModel.loadTrendingMovies("movie", "week")
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }

    @Test
    fun `display trending movies`() {
        val movieResults = MovieResults(listOf(Movie("id","Title", "Overview", "Image")))
        coEvery{repository.getTrendingMovies(any(), any())} returns movieResults

        navigationViewModel.loadTrendingMovies("movie", "week")
        assertTrue(navigationViewModel.movies.value == movieResults.results)
    }

    @Test
    fun `make a call on repository when loading genre specific movies`() {
        navigationViewModel.loadGenreSpecificMovies("id", "movie")
        coVerify ( exactly = 1 ) { repository.discoverMovies()}
    }

    @Test
    fun `display genre-specific movies`() {
        val movieResults = MovieResults(listOf(Movie("id","Title", "Overview", "Image")))
        coEvery{repository.discoverMovies(includeGenres = any(), sortBy = any())} returns movieResults

        navigationViewModel.loadGenreSpecificMovies("id", "sort")
        assertTrue(navigationViewModel.categorisedMovies.value!!.contains(movieResults))

    }

    @Test
    fun `show error given network call fails`() {
        coEvery{repository.getTrendingMovies(any(), any())} throws RuntimeException()

        navigationViewModel.loadTrendingMovies("movie", "week")
        assertTrue(navigationViewModel.showError.value == R.string.error_loading_movies)
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }


}