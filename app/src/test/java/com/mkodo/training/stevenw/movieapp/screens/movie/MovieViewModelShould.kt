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
import retrofit2.http.GET
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class MovieViewModelShould {

    @InjectMockKs
    private lateinit var viewModel: MovieViewModel

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
        viewModel.loadTrendingMovies("movie", "week")
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }

    @Test
    fun `make a call on repository when loading a movie`() {
        viewModel.loadMovie("666243")
        coVerify(exactly = 1) { repository.getMovie("666243")}
    }

    @Test
    fun `display trending movies`() {
        val movieResults = MovieResults(listOf(Movie("Title", "Overview", "Image")))
        coEvery{repository.getTrendingMovies(any(), any())} returns movieResults

        viewModel.loadTrendingMovies("movie", "week")
        assertTrue(viewModel.movies.value == movieResults.results)
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }

    @Test
    fun `show error given network call fails`() {
        coEvery{repository.getTrendingMovies(any(), any())} throws RuntimeException()

        viewModel.loadTrendingMovies("movie", "week")
        assertTrue(viewModel.showError.value == "Something went wrong")
        coVerify(exactly = 1) { repository.getTrendingMovies("movie", "week") }
    }
}