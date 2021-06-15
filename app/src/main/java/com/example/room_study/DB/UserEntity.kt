package com.example.room_study.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Room의 DB에 담을 데이터 변수형식을 정의
// Class 이름은 UserEnitiy지만 tablename을 Userinfo로 지정해주었기때문에 데이터베이스의 이름은 Userinfo이다.

@Entity(tableName = "userinfo")
data class UserEntity (
    //기본키설정
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    //필드명 즉, 열의 이름을 다르게
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "email") val email : String
)