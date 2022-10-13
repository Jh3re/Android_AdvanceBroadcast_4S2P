package com.example.android_advancebroadcast_4s2p

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.android_advancebroadcast_4s2p.databinding.ActivityBatteryBinding

class BatteryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBatteryBinding
    private lateinit var myBroadcast: MyBroadcast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myBroadcast = MyBroadcast(binding)
        binding.fabSettings.setOnClickListener {
            openSystemSettings()
        }
    }

    private fun openSystemSettings() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(myBroadcast, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        registerReceiver(myBroadcast, IntentFilter(Intent.ACTION_BATTERY_LOW))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcast)
    }
}