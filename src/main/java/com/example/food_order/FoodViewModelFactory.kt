package com.example.food_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FoodViewModelFactory(private val repository: FoodRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodViewModel(repository) as T
    }

}