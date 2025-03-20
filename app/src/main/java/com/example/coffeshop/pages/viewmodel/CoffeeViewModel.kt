package com.example.coffeshop.pages.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeViewModel: ViewModel() {

    private var _count = MutableLiveData<Int>(1)
    var count: LiveData<Int> = _count

    fun  increment() {
        _count.value = (count.value ?: 1) +1
    }

    fun  decrement() {
        _count.value = (count.value ?: 1) -1
    }

}