package com.techascent.cleanarchitecture2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.techascent.cleanarchitecture2.R
import com.techascent.cleanarchitecture2.databinding.FragmentUserBinding
import com.techascent.cleanarchitecture2.domain.user.entity.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class UserFragment : Fragment(), View.OnClickListener {

    private val mViewModel : UserViewModel by viewModels()

    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var picasso: Picasso

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTryAgain.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        observeViewModel()
    }

    private fun observeViewModel(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mViewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
                state -> handleUiState(state)
            }.launchIn(lifecycleScope)
        }
    }

    private fun handleUiState(state: UserUiState){

        when(state){
            is UserUiState.Loading -> handleProgressBar(state.isLoading)
            is UserUiState.Success -> updateUi(state.data)
            is UserUiState.Error -> showErrorDialog()
            is UserUiState.ShowError ->showTextInSnackBar(state.error)
            is UserUiState.Init ->{}
        }
    }

    private fun showTextInSnackBar(msg : String){
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleProgressBar(shouldShow : Boolean){
        when(shouldShow){
            true -> binding.progressBar.visibility = View.VISIBLE
            false -> binding.progressBar.visibility = View.GONE
        }
    }

    private fun showErrorDialog(){

    }

    private fun updateUi(entity : UserEntity){
        with(binding){
            name.text = entity.name
            location.text = entity.location
            followers.text = getString(R.string.followers, entity.followers)
            picasso.load(entity.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_try_again ->{
                mViewModel.getUser("alik7-cmd")
            }

            R.id.btn_next ->{
                navigate(UserFragmentDirections.actionFirstFragmentToSecondFragment())
            }
        }
    }
}