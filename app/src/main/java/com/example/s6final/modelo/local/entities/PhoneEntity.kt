package com.example.s6final.modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_PHONE")
data class PhoneEntity (
    @PrimaryKey//(autoGenerate = true)
    val id:String,
    val name:String,
    val price:String,
    val image:String,
)