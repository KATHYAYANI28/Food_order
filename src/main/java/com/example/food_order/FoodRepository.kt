package com.example.food_order

class FoodRepository(private val db:FoodDatabase) {
    suspend fun insert(items:FoodItems)=db.getFoodDoa().insert(items)
    suspend fun delete(items:FoodItems)=db.getFoodDoa().delete(items)

    fun getAllItems()=db.getFoodDoa().getAllFoodItems()
}