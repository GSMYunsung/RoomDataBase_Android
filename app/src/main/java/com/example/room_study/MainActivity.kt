package com.example.room_study

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_study.DB.UserEntity
import com.example.room_study.MainActivity.Companion.numplus
import com.example.room_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.RowClickListener {
    companion object {
        var numplus : Int = 0
    }
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var mainbinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainbinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainbinding.btnobservable = BtnOnClick()

        mainbinding.recyclerView.apply {
            //현재 액티비티의 레이아웃을 가져온다.
            layoutManager = LinearLayoutManager(this@MainActivity)
            //어뎁터에 현재 Activity를 넣어준다.
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            //recyclerViewAdapter를 가져온다.
            adapter = recyclerViewAdapter

            var divider = DividerItemDecoration(applicationContext,VERTICAL)
            //구분선 지정
            addItemDecoration(divider)

        }

    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClikListener(user: UserEntity) {
        TODO("Not yet implemented")
    }
}

class BtnOnClick : BaseObservable(){
    var BtnText : ObservableField<String> = ObservableField("값 추가하기")

    fun onCilck(){
        numplus++
        BtnText.set("데이터가 " + numplus.toString() + "번 들어갔습니다.")
    }
}