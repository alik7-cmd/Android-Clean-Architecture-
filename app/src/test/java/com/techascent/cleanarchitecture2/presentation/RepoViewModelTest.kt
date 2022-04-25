package com.techascent.cleanarchitecture2.presentation

import android.os.Looper
import com.techascent.cleanarchitecture2.api.FakeGitRepoApiService
import com.techascent.cleanarchitecture2.domain.repo.usecase.GitRepoUseCase
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
@Config(sdk = [25], application = HiltTestApplication::class )
@LooperMode(LooperMode.Mode.PAUSED)
class RepoViewModelTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var useCase: GitRepoUseCase

    @Mock
    lateinit var repoViewModel: RepoViewModel

    @BindValue
    @JvmField
    val fakeApiService: FakeGitRepoApiService = FakeGitRepoApiService()

    @Before
    fun setup(){
        hiltRule.inject()
        repoViewModel = RepoViewModel(useCase)
    }

    @Test
    fun `test repo data success`() = runBlocking{
        repoViewModel.loadRepos("")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = repoViewModel.uiState.value
        assertTrue(value is RepoUiState.Success)
        assertNotNull(value)
    }

    @Test
    fun `test repo data error`() = runBlocking {
        fakeApiService.failUserApi = true
        repoViewModel.loadRepos("")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = repoViewModel.uiState.value
        assertTrue(value is RepoUiState.ShowErrorMessage)
    }

}