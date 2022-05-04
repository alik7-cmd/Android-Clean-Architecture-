package com.techascent.cleanarchitecture2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techascent.cleanarchitecture2.data.common.data.WrappedListResponse
import com.techascent.cleanarchitecture2.data.repo.dto.Repos
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.common.Resource
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import com.techascent.cleanarchitecture2.domain.repo.usecase.GitRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(private val useCase: GitRepoUseCase) : ViewModel() {


    private val _uiState : MutableStateFlow<RepoUiState> = MutableStateFlow(RepoUiState.Init)
    val uiState get() = _uiState

    private fun setLoading() {
        _uiState.value = RepoUiState.Loading(true)
    }

    private fun hideLoading() {
        _uiState.value = RepoUiState.Loading(false)
    }


    fun loadRepos(org : String){

        viewModelScope.launch {
            useCase.execute(org).onStart {
                setLoading()

            }.catch { ex ->
                hideLoading()
                _uiState.value = RepoUiState.ShowErrorMessage(ex.message.toString())

            }.collect {
                hideLoading()
                when(it){
                    is BaseResult.Success -> _uiState.value = RepoUiState.Success(it.data)
                    is BaseResult.Error -> _uiState.value = RepoUiState.Error(it.errorResponse)
                }

            }
        }

        /*viewModelScope.launch {
            useCase.execute1(org).onStart {
                setLoading()

            }.catch { ex ->
                hideLoading()
                _uiState.value = RepoUiState.ShowErrorMessage(ex.message.toString())

            }.collect {

                when(it){

                    is Resource.Success -> _uiState.value = RepoUiState.Success(it.data ?: emptyList())
                    is Resource.Error -> _uiState.value = RepoUiState.ShowErrorMessage(it.message ?: "")
                }

            }
        }*/
    }
}


sealed class RepoUiState{

    object Init : RepoUiState()
    class Loading(val isLoading : Boolean) : RepoUiState()
    class Success(val data : List<RepoItemEntity>) : RepoUiState()
    class Error(val errorResponse : WrappedListResponse<Repos>) : RepoUiState()
    class ShowErrorMessage(val msg : String) :  RepoUiState()
}