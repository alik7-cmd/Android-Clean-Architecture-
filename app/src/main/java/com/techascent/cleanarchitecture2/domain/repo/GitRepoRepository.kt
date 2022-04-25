package com.techascent.cleanarchitecture2.domain.repo

import com.techascent.cleanarchitecture2.data.common.data.WrappedListResponse
import com.techascent.cleanarchitecture2.data.repo.dto.Repos
import com.techascent.cleanarchitecture2.data.repo.dto.ReposItem
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import kotlinx.coroutines.flow.Flow

interface GitRepoRepository {

    suspend fun getGitRepos(org : String, queryMap: Map<String, String>) : Flow<BaseResult<List<RepoItemEntity>, WrappedListResponse<Repos>>>
}