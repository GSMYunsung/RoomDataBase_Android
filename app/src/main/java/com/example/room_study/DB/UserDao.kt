package com.example.room_study.DB

import androidx.room.*

@Dao
interface UserDao {

    //Query = Dao에서 사용하는 기본적인 주석, 데이터베이스에서 읽고 쓰기가 가능.
    //Insert = 데이터베이스에서 값을 꺼내거나 값을 넣을때 인자값으로 끼워넣을 수 있다.
    //Query로 데이터베이스에서 사용자 Entity를 가져온다.

    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserInfo() : List<UserEntity>

    @Insert
    fun insertUser(user : UserEntity?)

    @Delete
    fun deleteUser(user : UserEntity)

    @Update
    fun UpdateUser(user : UserEntity?)

}