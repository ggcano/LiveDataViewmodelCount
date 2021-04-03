package com.example.databindingmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.databindingmvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get()


        viewModel.second().observe(this, Observer {
            binding.numberText.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finalizado", Toast.LENGTH_LONG).show()
            }
        })

        buttonStartClick()
        buttonStopClick()
    }

    private fun buttonStartClick() {
        binding.buttonStart.setOnClickListener {
            if (binding.imputTxtNumber.text.isEmpty() || binding.imputTxtNumber.text.length < 4) {
                Toast.makeText(this, "Por favor escriba un numero", Toast.LENGTH_LONG).show()
            } else {
                viewModel.timerValue.value = binding.imputTxtNumber.text.toString().toLong()
                viewModel.starTimer()
            }

        }
    }

    private fun buttonStopClick() {
        binding.buttonStop.setOnClickListener {
            binding.numberText.text = "0"
            viewModel.stopTimer()
        }
    }
}