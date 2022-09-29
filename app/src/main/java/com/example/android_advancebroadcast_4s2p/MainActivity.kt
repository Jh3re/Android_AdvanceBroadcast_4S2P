package com.example.android_advancebroadcast_4s2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_advancebroadcast_4s2p.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    // Definir un objeto de tipo BroadcastReceiver
    // donde directamente aplicaremos la implementacion
    // o sobreescritura de un metodo clave en la
    // configuracion de un Broadcast que es el metodo
    // onReceive
    private val getAirplaneMode = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val airplaneMode = intent?.getBooleanExtra("state", false)
            airplaneMode?.let {
                val message = if (it) "Modo Avion activado" else "Modo Avion desactivado"
                binding.txtAirplaneMode.text = message
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // Registrar el BroadcastReceiver
        registerReceiver(getAirplaneMode, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onStop() {
        super.onStop()
        // Quitar el registro
        unregisterReceiver(getAirplaneMode)
    }
}