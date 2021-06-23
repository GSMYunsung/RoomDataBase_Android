package com.example.room_study

import android.app.Activity
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.BaseObservable
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_study.DB.UserEntity
import com.example.room_study.MainActivity.Companion.mainbinding
import com.example.room_study.MainActivity.Companion.numplus
import com.example.room_study.MainActivity.Companion.viewModel
import com.example.room_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.RowClickListener {
    companion object {
        var numplus : Int = 0
        lateinit var mainbinding : ActivityMainBinding
        lateinit var viewModel : MainActivityModel
    }
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainbinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainbinding.btnobservable = BtnOnClick()

        //객체의 초기화를 같이함. 수신객체 함수를 사용하지 않고 자신을 다시 반환함.
        mainbinding.recyclerView.apply {
            //현재 액티비티의 레이아웃을 가져온다.
            layoutManager = LinearLayoutManager(this@MainActivity)
            //어뎁터에 현재 Activity를 넣어준다.
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            //recyclerViewAdapter를 가져온다.
            adapter = recyclerViewAdapter
        }

        // ViewModelStoreOwner 타입이기 때문에 액티비티나 프래그먼트 this! , 생성하고자하는 뷰모델 클래스
        viewModel = ViewModelProvider(this).get(MainActivityModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setlistData(ArrayList(it))
            //이걸하면서 새로운 배열에 들어감.
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClikListener(user: UserEntity) {
        mainbinding.saveBtn.setText("값 수정하기")
       mainbinding.fristNameInput.setText(user.name)
       mainbinding.emailInput.setText(user.email)
        //key , value 방식으로 가져온다.
        //밑에 User에서 id를 받아와서 넣어준다!
       mainbinding.fristNameInput.setTag(mainbinding.fristNameInput.id,user.id)
    }
}

class BtnOnClick : BaseObservable(){

    fun onCilck(){
        val name = mainbinding.fristNameInput.text.toString()
        val email = mainbinding.emailInput.text.toString()

        if(mainbinding.saveBtn.text.equals("값추가하기"))
        {
            val user = UserEntity(0, name, email)
            viewModel.insertUserInfo(user)
        }
        else if(mainbinding.saveBtn.text.equals("값 수정하기"))
        {
            //해당 id로 값을 다시 뿌려주어야하기때문에 필요하다!
            val user = UserEntity(mainbinding.fristNameInput
                .getTag(mainbinding.fristNameInput.id)
                .toString().toInt(), name, email)
                mainbinding.saveBtn.setText("값추가하기")
            viewModel.updateUserInfo(user)
        }

        mainbinding.fristNameInput.setText("")
        mainbinding.emailInput.setText("")
    }
}