package com.example.asynctaskexample

import android.os.AsyncTask
import com.example.asynctaskexample.databinding.ActivityMainBinding

class MyAsyncTask(val binding: ActivityMainBinding, val stringBuilder: StringBuilder, val nombre: String, val tiempo: Double) : AsyncTask<Int, Int, Void?>(){
    override fun doInBackground(vararg params: Int?): Void? {
        var index = 0
        while (index<params[0]!! && !isCancelled){
           publishProgress(index)
            Thread.sleep((1000 * tiempo).toLong())
            index++
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        stringBuilder.append("Tarea: ${nombre}. Tratando el parametro: ${values[0]}\n")
        binding.statusText.text = "${stringBuilder.toString()}"

        binding.progressBar.progress = values[0]!!+1
    }

    override fun onPreExecute() {
        super.onPreExecute()
        stringBuilder.append("Comenzando la tarea ${nombre}\n")
        binding.statusText.text = "${stringBuilder.toString()}"
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        terminar("Terminando la tarea ${nombre}\n")
    }

    override fun onCancelled() {
        super.onCancelled()
        terminar("Cancelando la tarea ${nombre}\n")
    }

    private fun terminar(texto: String){
        stringBuilder.append(texto)
        binding.statusText.text = "${stringBuilder.toString()}"

        binding.btnAsync.isEnabled = true
        binding.btnCancel.isEnabled = false
    }
}