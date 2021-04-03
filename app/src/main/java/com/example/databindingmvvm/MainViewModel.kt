package com.example.databindingmvvm

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()

    var finished = MutableLiveData<Boolean>()
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
                finished.value = true
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }


}