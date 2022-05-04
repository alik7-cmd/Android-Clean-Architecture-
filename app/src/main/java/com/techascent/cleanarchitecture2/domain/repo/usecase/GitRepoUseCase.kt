package com.techascent.cleanarchitecture2.domain.repo.usecase

import com.techascent.cleanarchitecture2.data.common.data.WrappedListResponse
import com.techascent.cleanarchitecture2.data.repo.dto.Repos
import com.techascent.cleanarchitecture2.data.repo.dto.ReposItem
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.common.Resource
import com.techascent.cleanarchitecture2.domain.repo.GitRepoRepository
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepoUseCase @Inject constructor(private val gitRepoRepository: GitRepoRepository) {
    suspend fun execute(org : String) :  Flow<BaseResult<List<RepoItemEntity>, WrappedListResponse<Repos>>> {
        val queryMap = mutableMapOf<String, String>()
        queryMap["type"] = "all"
        return gitRepoRepository.getGitRepos(org, queryMap)
    }


    suspend fun execute1(org : String) : Flow<Resource<List<RepoItemEntity>>>{
        val queryMap = mutableMapOf<String, String>()
        queryMap["type"] = "all"
        return gitRepoRepository.getGitRepos1(org, queryMap)
    }
}