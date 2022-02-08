package com.example.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.taskmanagement.databinding.ActivityEditBinding
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class EditActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        realm = Realm.getDefaultInstance()

        val dataId = intent.getLongExtra("id", 0L)
        if(dataId > 0L){
            val todoData = realm.where<Todo>().equalTo("id", dataId).findFirst()
            binding.editText.setText(todoData?.todoText.toString())
            when (todoData?.status.toString()) {
                "TODO" -> {
                    binding.radioGroup.check(R.id.RadioButton01)
                }
                "WIP" -> {
                    binding.radioGroup.check(R.id.RadioButton02)
                }
            }
            binding.deleteBtn.visibility = View.VISIBLE
        }else{
            binding.deleteBtn.visibility = View.INVISIBLE
        }


        binding.saveBtn.setOnClickListener {
            var status:String = ""
            var contentText:String = ""

            if (!binding.editText.text.isNullOrEmpty()){
                contentText = binding.editText.text.toString()
            }

            val checkId = binding.radioGroup.checkedRadioButtonId
            when (checkId){
                R.id.RadioButton01 ->{
                    status = "TODO"
                }
                R.id.RadioButton02 ->{
                    status = "WIP"
                }
                else ->{
                    Toast.makeText(applicationContext, "ステータスを選択してください", Toast.LENGTH_SHORT).show()
                }
            }

            if (checkId != -1){
                when (dataId){
                    0L ->{
                        realm.executeTransaction {
                            val maxId = realm.where<Todo>().max("id")
                            val nextId = (maxId?.toLong() ?: 0L) + 1L
                            val todoList = realm.createObject<Todo>(nextId)
                            todoList.todoText = contentText
                            todoList.status = status
                        }
                    }
                    else ->{
                        realm.executeTransaction {
                            val todoData = realm.where<Todo>()
                                .equalTo("id", dataId).findFirst()
                            todoData?.todoText = contentText
                            todoData?.status = status
                        }
                    }
                }
                Toast.makeText(applicationContext, "保存しました", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

        binding.deleteBtn.setOnClickListener {
            realm.executeTransaction {
                val todoData = realm.where<Todo>()
                    .equalTo("id", dataId).findFirst()?.deleteFromRealm()
            }
            Toast.makeText(applicationContext, "削除しました", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}