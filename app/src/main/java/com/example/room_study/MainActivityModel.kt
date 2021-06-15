package com.example.room_study

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.room_study.DB.RoomAppDB
import com.example.room_study.DB.UserEntity

//Activity와 Fragment 에서 사용되는 UI관련 설정을 보관하고 관리하기 위한곳.
//엑티비티가 재생성되는 과정에서도 ViewModle을 유지함으로써 데이터를 안전하게 다룰 수 있다.

class  MainActivityModel(app : Application) : AndroidViewModel(app) {
    //라이브 데이터를 위해
     var allUsers : MutableLiveData<List<UserEntity>>


    init{
        allUsers = MutableLiveData()
        getAllUsers()
    }

    fun getAllUsersObservers() : MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    //라이브데이터를 모두 모아오는곳.
    //해당 어플리케이션에 context 를 넣어주어 RoomDatabase 객체를 생성해준다!
    fun getAllUsers() {
      val userDao = RoomAppDB.getAppDatabase(getApplication())?.userDao()
      val list = userDao?.getAllUserInfo()

        //백그라운드 스레드에서 라이브데이터 값을 변경해주어야할때 쓴다.
        //데이터를 넘겨줘서!
        allUsers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity){
       val userDao = RoomAppDB.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDB.getAppDatabase(getApplication())?.userDao()
        userDao?.UpdateUser(entity)
        getAllUsers()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDB.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }
}