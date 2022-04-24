package com.techascent.cleanarchitecture2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.techascent.cleanarchitecture2.R
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import com.techascent.cleanarchitecture2.presentation.UserUiState
import com.techascent.cleanarchitecture2.presentation.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mViewModel : UserViewModel by viewModels ()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_try_again.setOnClickListener {
            mViewModel.getUser("alik7-cmd")
        }
        //
        observeViewModel()
    }

    private fun observeViewModel(){
        mViewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state : UserUiState){
        when(state){
            is UserUiState.Success -> updateUI(state.data)
            is UserUiState.Error -> makeToast("Error")
            is UserUiState.Loading ->{
                when(state.isLoading){
                    true -> i_progressBar.visibility = View.VISIBLE
                    false -> i_progressBar.visibility = View.GONE
                }
            }
            is UserUiState.ShowError -> {
                makeToast(state.error)
                Log.d("ERROR",state.error )
            }
        }
    }

    private fun updateUI(entity: UserEntity){
        name.text = entity.name
        Glide.with(requireActivity()).load(entity.avatarUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)
    }

    private fun makeToast(msg : String){
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}