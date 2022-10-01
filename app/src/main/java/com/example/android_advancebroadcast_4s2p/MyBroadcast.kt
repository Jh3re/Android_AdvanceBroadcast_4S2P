package com.example.android_advancebroadcast_4s2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

// Para que esta clase se comporte como un broadcastReceiver hay que extender una clase abstracta,
// usado 3 veces en el ejemplo
class MyBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        TODO("Configurar nivel de bateria")
        when(intent?.action){
            Intent.ACTION_BATTERY_CHANGED -> showBatteryLevel(intent)
        }
    }

    private fun showBatteryLevel(intent: Intent) {

    }
}