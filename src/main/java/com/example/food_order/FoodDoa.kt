package com.example.food_order

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@FoodDoa.Doa
abstract interface FoodDoa {
    annotation class Doa

    @Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(item:FoodItems)

@Delete
suspend fun delete(item:FoodItems)

@Query("SELECT *FROM Food_Items")
fun getAllFoodItems():LiveData<List<FoodItems>>
}


