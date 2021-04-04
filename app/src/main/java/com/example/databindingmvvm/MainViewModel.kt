package com.example.databindingmvvm

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class MainViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()

    var finished = MutableLiveData<String>()
    var finishAllBoolean = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Long>()


    fun second(): LiveData<Int> {
        return _seconds
    }


    fun starTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(timeTotal: Long) {
                val timeLeft = timeTotal / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value = "itsOk"
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }

    fun checkCorrutines (){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(5000L)
               finishAllBoolean.value
            }
         finishAllBoolean.postValue(true)
        }
    }


}