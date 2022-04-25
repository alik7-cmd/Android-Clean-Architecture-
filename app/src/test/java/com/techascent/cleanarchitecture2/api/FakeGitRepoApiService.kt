package com.techascent.cleanarchitecture2.api

import com.techascent.cleanarchitecture2.JsonProvider
import com.techascent.cleanarchitecture2.data.repo.api.RepoApiInterface
import com.techascent.cleanarchitecture2.data.repo.dto.Repos
import java.lang.Exception
import javax.inject.Inject

class FakeGitRepoApiService @Inject constructor() : RepoApiInterface {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false


    override suspend fun getRepos(org: String, map: Map<String, String>): Repos {
       if(failUserApi) throw Exception("api error")
        val listOfRepos : Repos = JsonProvider.objectFromJsonFileWithType(REPOS_JSON)

        if(wrongResponse) return listOfRepos.apply {
            forEach {
                //it.owner.login = ""
            }
        }

        return listOfRepos
    }


    companion object{
        const val REPOS_JSON = "/json/repos.json"
    }
}