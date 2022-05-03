package com.techascent.cleanarchitecture2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techascent.cleanarchitecture2.data.common.data.WrappedResponse
import com.techascent.cleanarchitecture2.data.user.dto.User
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.common.UseCases
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import com.techascent.cleanarchitecture2.domain.user.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(/*val userUseCase: UserUseCase*/ val useCase : UseCases) : ViewModel() {

    private val _uiState: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Init)
    val uiState: StateFlow<UserUiState> = _uiState


    private fun setLoading() {
        _uiState.value = UserUiState.Loading(true)
    }

    private fun hideLoading() {
        _uiState.value = UserUiState.Loading(false)
    }


    fun getUser(username: String) {

        viewModelScope.launch {
            useCase.userUseCase.execute(username).onStart {
                setLoading()
            }.catch { exception ->
                hideLoading()
                _uiState.value = UserUiState.ShowError(exception.message.toString())
            }.collect {
                hideLoading()
                when (it) {
                    is BaseResult.Success -> _uiState.value = UserUiState.Success(it.data)
                    is BaseResult.Error -> _uiState.value = UserUiState.Error(it.errorResponse)
                }
            }
        }


    }


}


sealed class UserUiState {

    object Init : UserUiState()
    class Loading(val isLoading: Boolean) : UserUiState()
    class Success(val data: UserEntity) : UserUiState()
    class Error(val errorResponse: WrappedResponse<User>) : UserUiState()
    class ShowError(val error: String) : UserUiState()
}