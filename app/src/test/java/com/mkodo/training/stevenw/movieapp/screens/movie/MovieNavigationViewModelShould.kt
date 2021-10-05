package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mkodo.training.stevenw.movieapp.MainCoroutineRule
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
    fun `make a call on repository when loading a movie`() {
        navigationViewModel.loadMovie("666243")
        coVerify(exactly = 1) { repository.getMovie("666243")}
    }

    @Test
    fun `display a movie`() {
        val movie = Movie("title", "overview", "poster_path")
        coEvery{repository.getMovie(any())} returns movie

        navigationViewModel.loadMovie("1")
        assertTrue(navigationViewModel.movie.value == movie)
        coVerify(exactly = 1) { repository.getMovie("1")}
    }

    @Test
    fun `display trending movies`() {
        val movieResults = MovieResults(listOf(Movie("Title", "Overview", "Image")))
        coEvery{repository.getTrendingMovies(any(), any())} returns movieResults

        navigationViewModel.loadTrendingMovies("movie", "week")
        assertTrue(navigationViewModel.movies.value == movieResults.results)
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }

    @Test
    fun `show error given network call fails`() {
        coEvery{repository.getTrendingMovies(any(), any())} throws RuntimeException()

        navigationViewModel.loadTrendingMovies("movie", "week")
        assertTrue(navigationViewModel.showError.value == "Something went wrong")
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }
}