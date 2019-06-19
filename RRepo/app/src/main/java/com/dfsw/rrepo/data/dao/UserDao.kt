package com.dfsw.rrepo.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.dfsw.rrepo.data.model.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Insert
    fun insertAll(users: List<User>): List<Long>

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId: Int): LiveData<User>
}