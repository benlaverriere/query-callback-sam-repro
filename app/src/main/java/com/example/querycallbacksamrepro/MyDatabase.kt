package com.example.querycallbacksamrepro

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Entity
data class MyEntity(val name: String)

@Database(version = 1, entities = [MyEntity::class])
abstract class MyDatabase(context: Context): RoomDatabase() {
    init {
        Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase")
            .setQueryCallback({ query, bindArgs ->
                Log.d("DB", "executing query callback with $query and $bindArgs")
            },
            Executors.newSingleThreadExecutor())
    }
}