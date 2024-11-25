package uk.ac.tees.mad.w9624019.cinimaapp.feature.battery_status.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.ViewModel

class BatteryViewModel(private val context: Context) : ViewModel() {
    private var batteryLevel: Int = 0

    private val batteryStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
                batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            }
        }
    }

    fun getBatteryLevel(): Int {
        return batteryLevel
    }

    fun registerBatteryReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryStatusReceiver, filter)
    }

    fun unregisterBatteryReceiver() {
        context.unregisterReceiver(batteryStatusReceiver)
    }
}

