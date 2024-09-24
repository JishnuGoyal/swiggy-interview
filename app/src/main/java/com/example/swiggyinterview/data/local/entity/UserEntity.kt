package com.example.swiggyinterview.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(val name: String, @PrimaryKey val id: Int)