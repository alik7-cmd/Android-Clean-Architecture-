package com.techascent.cleanarchitecture2.data.user.dao

import androidx.room.*
import com.techascent.cleanarchitecture2.domain.user.entity.LocalUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: LocalUserEntity?)

    @Delete
    fun delete(data: LocalUserEntity?)

    @Query("SELECT * FROM LocalUserEntity")
    fun observeAllUser(): Flow<List<LocalUserEntity>>
}