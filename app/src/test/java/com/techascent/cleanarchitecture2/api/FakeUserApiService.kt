package com.techascent.cleanarchitecture2.api

import com.techascent.cleanarchitecture2.JsonProvider
import com.techascent.cleanarchitecture2.data.user.api.UserApiInterface
import com.techascent.cleanarchitecture2.data.user.dto.User
import java.lang.Exception
import javax.inject.Inject

class FakeUserApiService @Inject constructor() : UserApiInterface {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun getUser(username: String): User {

        if(failUserApi) throw Exception("Api Failed")

        val user : User = JsonProvider.objectFromJsonFileWithType(USER_JSON)

        if(wrongResponse) return user.apply {
            name = ""
        }

        return user

    }

    companion object{
       const val USER_JSON = "/json/user.json"
    }
}