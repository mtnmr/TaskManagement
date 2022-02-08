package com.example.taskmanagement

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Todo: RealmObject() {
    @PrimaryKey
    var id:Long = 0
    var todoText :String = ""
    var status: String = ""

}
