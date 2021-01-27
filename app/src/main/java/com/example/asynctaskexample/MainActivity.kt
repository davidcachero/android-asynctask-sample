package com.example.asynctaskexample

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.example.asynctaskexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var stringBuilder= StringBuilder()
    private val parametroTarea1 = 5
    private val parametroTarea2 = 10

    private lateinit var task1: MyAsyncTask
    private lateinit var task2: MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        task1 = MyAsyncTask(binding, stringBuilder, "TAREA 1", 1.0)
        task2 = MyAsyncTask(binding, stringBuilder, "TAREA 2", 0.5)

        binding.statusText?.movementMethod= ScrollingMovementMethod()
        stringBuilder = StringBuilder("Empezando actividad\n")
        stringBuilder.append("Esperando click.\n")
        binding.statusText.text = "${stringBuilder.toString()}"

        binding.progressBar.max = parametroTarea1 + parametroTarea2

        binding.btnAsync.setOnClickListener(View.OnClickListener {
            binding.btnCancel.isEnabled = true
            binding.btnAsync.isEnabled = false
            task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,parametroTarea1)
            task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,parametroTarea2)
        })

        binding.btnCancel.setOnClickListener(View.OnClickListener {
            task1.cancel(false)
            task2.cancel(false)
            binding.btnCancel.isEnabled = false
            binding.btnAsync.isEnabled = true
        })
    }
}