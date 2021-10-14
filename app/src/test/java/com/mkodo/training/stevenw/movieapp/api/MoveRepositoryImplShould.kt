package com.mkodo.training.stevenw.movieapp.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mkodo.training.stevenw.movieapp.MainCoroutineRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MoveRepositoryImplShould {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `check if movie has loaded`() {

    }
}