package com.example.android_advancebroadcast_4s2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.example.android_advancebroadcast_4s2p.databinding.ActivityBatteryBinding

// Para que esta clase se comporte como un broadcastReceiver hay que extender una clase abstracta,
// usado 3 veces en el ejemplo
class MyBroadcast(
    private val bindingObject: ActivityBatteryBinding
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_BATTERY_CHANGED -> showBatteryLevel(intent)
            Intent.ACTION_BATTERY_LOW -> configureBatteryLow(intent)
        }
    }

    private fun configureBatteryLow(intent: Intent) {
        // El dato que les llega a travees del archivo Extras del Intent
        // es un booleano
        var batterylow = intent?.getBooleanExtra(BatteryManager.EXTRA_BATTERY_LOW,false)
        batterylow?.let {
            bindingObject.txtBatteryMessage.text = "Bateria baja"
        }


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