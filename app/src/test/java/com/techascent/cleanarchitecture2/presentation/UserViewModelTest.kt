package com.techascent.cleanarchitecture2.presentation

import android.os.Looper
import com.techascent.cleanarchitecture2.api.FakeUserApiService
import com.techascent.cleanarchitecture2.domain.user.usecase.UserUseCase
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
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
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class UserViewModelTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var  userUseCase : UserUseCase


    @BindValue
    @JvmField
    val fakeApiService: FakeUserApiService = FakeUserApiService()

    @Mock
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        userViewModel = UserViewModel(userUseCase)
    }

    @Test
    fun `test user data success`() = runBlockingTest {
        userViewModel.getUser("alik7-cmd")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = userViewModel.uiState.value
        assertTrue(value is UserUiState.Success)
        assertNotNull(value)
        assertEquals("alik7-cmd",(value as UserUiState.Success).data.name)
    }


    @Test
    fun `test user data fail`() = runBlockingTest {
        fakeApiService.failUserApi = true
        userViewModel.getUser("alik7-cmd")
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = userViewModel.uiState.value
        assertTrue(value is UserUiState.ShowError)
    }

    @After
    fun tearDown(){

    }

}