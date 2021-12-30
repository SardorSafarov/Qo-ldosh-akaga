package com.example.app1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
class User
    (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var ustun1:String,
    var ustun2:String,
    var ustun3:String,
    var ustun4:String,

            )