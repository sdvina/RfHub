package org.jayhsu.rfhub.service.bluetooth
import android.bluetooth.BluetoothDevice

data class BluetoothDevice(
    val device: BluetoothDevice,
    var rssi: Int
)