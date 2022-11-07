package com.example.food_order

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository):ViewModel() {

    fun insert (items: FoodItems)=GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items: FoodItems)=GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllFoodItems()=repository.getAllItems()
}