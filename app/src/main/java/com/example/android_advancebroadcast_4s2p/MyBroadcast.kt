package com.example.android_advancebroadcast_4s2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.provider.Settings
import com.example.android_advancebroadcast_4s2p.databinding.ActivityBatteryBinding

// Para que esta clase se comporte como un broadcastReceiver hay que extender una clase abstracta,
// usado 3 veces en el ejemplo
class MyBroadcast(
    private val bindingObject: ActivityBatteryBinding
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_BATTERY_CHANGED -> showBatteryLevel(intent)
            Intent.ACTION_BATTERY_LOW -> configureBatteryLow(context, intent)
        }
    }

    private fun configureBatteryLow(context: Context?,intent: Intent) {
        // El dato que les llega a travees del archivo Extras del Intent
        // es un booleano
        var batterylow = intent?.getBooleanExtra(BatteryManager.EXTRA_BATTERY_LOW,false)
        batterylow?.let {
            bindingObject.txtBatteryMessage.text = "Hey, bateria baja"
            modifyScreenBrightness(context)
        }


    }

    private fun modifyScreenBrightness(context: Context?) {
        // Brillo es parte de los Settings o configuraciones del celular
        // 1: Por codigo se maneja en niveles de 0 a 255
        //    donde 255 es el maximo brillo
        // 2: Por defecto el celular el brillo se gestiona automaticamente
        // Para que puedan alterar el brillo por codigo dtienen que cambiar el modo
        // de brillo de automatico a manual

        // Cambio de automatico a manual
        Settings.System.putInt(
            context?.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )
        // Cambio del brillo de pantalla
        Settings.System.putInt(
            context?.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            20
        )

    }

    private fun showBatteryLevel(intent: Intent) {
        // Un valor entero que llega en extras del Intent
        // que el valor del nivel de la bateria
        // La bateria tiene un gestor llamado BatteryManager
        val batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
        batteryLevel?.let {
            val message = "Porcentaje de bateria: $it%"
            bindingObject.txtBatteryPercentage.text = message
            bindingObject.pbBatteryLevel.progress = it
        }
    }
}