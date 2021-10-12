package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mkodo.training.stevenw.movieapp.MainCoroutineRule
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.models.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieViewModelShould {

    @InjectMockKs
    private lateinit var navigationViewModel: MovieViewModel

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
    fun `make a call on repository when loading a movie`() {
        navigationViewModel.loadMovie("666243")
        coVerify(exactly = 1) { repository.getMovie("666243") }
    }

    @Test
    fun `display a movie`() {
        val movie = Movie("id", "title", "overview", "poster_path")
        coEvery { repository.getMovie(any()) } returns movie

        navigationViewModel.loadMovie("1")
        Assert.assertTrue(navigationViewModel.movie.value == movie)
    }
}