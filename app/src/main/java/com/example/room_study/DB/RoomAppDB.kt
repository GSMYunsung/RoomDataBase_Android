package com.example.room_study.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//데이터베이스주석으로 데이터베이스라는것을 알려줌
//또한 엔티티속성을 가져옴


@Database(entities = [UserEntity::class],version = 1)
abstract class RoomAppDB : RoomDatabase()  {


    abstract fun userDao() : UserDao?

    companion object{
        private var INSTANCEROOM : RoomAppDB? = null

        fun getAppDatabase(context: Context) : RoomAppDB?{
            //만약 room변수가 null값일경우 DatabaseBuilder를 이용해 RoomDB를 생성한다.
            if (INSTANCEROOM == null){
                INSTANCEROOM = Room.databaseBuilder<RoomAppDB>(
                        context.applicationContext,RoomAppDB::class.java,"AppDB")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCEROOM
        }
    }
}