package com.techascent.cleanarchitecture2.domain.repo.entity

data class RepoItemEntity(val id: Int?, val name: String?, val description: String?,
                          val private: Boolean, val watchersCount: Int?, val avatarUrl : String?)

