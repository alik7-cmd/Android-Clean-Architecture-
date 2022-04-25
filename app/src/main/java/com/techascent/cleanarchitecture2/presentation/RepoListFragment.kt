package com.techascent.cleanarchitecture2.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.techascent.cleanarchitecture2.BuildConfig
import com.techascent.cleanarchitecture2.databinding.FragmentRepoListBinding
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class RepoListFragment : Fragment() {

    private lateinit var mAdapter : RepoAdapter

    private val mViewModel : RepoViewModel by viewModels ()

    private val listener = object : OnRepoClickListener<RepoItemEntity>{
        override fun onRepoClick(data: RepoItemEntity) {

        }
    }

    private var _binding: FragmentRepoListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = RepoAdapter(listener)
        mViewModel.loadRepos("alik7-cmd")
        binding.rvRepoList.layoutManager = LinearLayoutManager(requireActivity())
        observeUiStateFlow()
    }

    private fun observeUiStateFlow(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mViewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
                state -> handleUiState(state)
            }.launchIn(lifecycleScope)
        }
    }

    private fun displayData(data : List<RepoItemEntity>){
        mAdapter.submitList(data)
        binding.rvRepoList.adapter = mAdapter

    }

    private fun handleUiState(state : RepoUiState){
        when(state){
            is RepoUiState.Success -> displayData(state.data)
            is RepoUiState.Error -> showErrorDialog()
            is RepoUiState.ShowErrorMessage -> showTextInSnackBar(state.msg)
        }

    }
    private fun showErrorDialog(){

    }

    private fun showTextInSnackBar(msg : String){
        Log.d("error", msg)
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}