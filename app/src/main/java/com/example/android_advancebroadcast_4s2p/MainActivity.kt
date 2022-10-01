package com.example.android_advancebroadcast_4s2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.android_advancebroadcast_4s2p.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var tts: TextToSpeech
    private var c = 0

    // Definir un objeto de tipo BroadcastReceiver
    // donde directamente aplicaremos la implementacion
    // o sobreescritura de un metodo clave en la
    // configuracion de un Broadcast que es el metodo
    // onReceive
    private val getAirplaneMode = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val airplaneMode = intent?.getBooleanExtra("state", false)
            airplaneMode?.let {
                val message = if (it) "Modo Avión activado"  else "Modo Avión desactivado"
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null,"")
                binding.txtAirplaneMode.text = message
            }
        }
    }

    // Configurar un BroadcastReceiver para el evento de cambio de tiempo a este se le denomina
    // Time Tick
    private val getTimeChange = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            c++
            if(c == 1){
                val message = "El tiempo ha cambiado $c vez"
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null,"")
                binding.txtTimeTick.text = message
            }else{
                val message = "El tiempo ha cambiado $c veces"
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null,"")
                binding.txtTimeTick.text = message
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this, this)
    }

    override fun onStart() {
        super.onStart()
        // Registrar el BroadcastReceiver
        registerReceiver(getAirplaneMode, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(getTimeChange, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun onStop() {
        super.onStop()
        // Quitar el registro
        unregisterReceiver(getAirplaneMode)
        unregisterReceiver(getTimeChange)
    }

    override fun onInit(status: Int) {
        val result = if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale("es")
            "Suba el volumen para el uso de voz"
        } else "Algo salio mal."
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}