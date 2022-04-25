package com.techascent.cleanarchitecture2.data.repo

import com.techascent.cleanarchitecture2.data.common.data.WrappedListResponse
import com.techascent.cleanarchitecture2.data.repo.api.RepoApiInterface
import com.techascent.cleanarchitecture2.data.repo.dto.Repos
import com.techascent.cleanarchitecture2.data.repo.dto.ReposItem
import com.techascent.cleanarchitecture2.domain.common.BaseResult
import com.techascent.cleanarchitecture2.domain.repo.GitRepoRepository
import com.techascent.cleanarchitecture2.domain.repo.entity.RepoItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitRepoRepositoryImpl @Inject constructor(private val repoApiInterface: RepoApiInterface) : GitRepoRepository {
    override suspend fun getGitRepos(
        org: String,
        queryMap: Map<String, String>
    ): Flow<BaseResult<List<RepoItemEntity>, WrappedListResponse<Repos>>> {

        return flow {
            val repos = repoApiInterface.getRepos(org, queryMap)
            val listOfRepos = mutableListOf<RepoItemEntity>()
            repos.forEach {
                val entity = RepoItemEntity(it.id, it.name, it.description, it.private, it.watchers_count, it.owner.avatar_url)
                listOfRepos.add(entity)
            }
            emit(BaseResult.Success(listOfRepos))
        }
    }
}