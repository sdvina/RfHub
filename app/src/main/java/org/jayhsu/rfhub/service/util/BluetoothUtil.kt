package org.jayhsu.rfhub.service.util

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object BluetoothUtil {
    private var isScanning =false
    private const val REQUEST_ENABLE_BT = 1
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var blEScanner: BluetoothLeScanner
    private val handler = Looper.myLooper()?.let { Handler(it) }
    // Stops scanning after 10 seconds.
    private const val SCAN_PERIOD: Long = 10000

    fun init(context: Context): Boolean{
        if(isEnable(context)){
            val bluetoothManager = ContextCompat.getSystemService(context, BluetoothManager::class.java)!!
            bluetoothAdapter =bluetoothManager.adapter
            blEScanner = bluetoothAdapter.bluetoothLeScanner
            return true
        }
        return false
    }

    private fun isEnable(context: Context): Boolean {
        ContextCompat.getSystemService(context, BluetoothManager::class.java) ?: return false
        return true
    }

    fun openBluetooth(activity: Activity, bluetoothAdapter: BluetoothAdapter, options: Bundle): Boolean{
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            ActivityCompat.startActivityForResult(activity, enableBtIntent, REQUEST_ENABLE_BT, options)
        }
        return bluetoothAdapter.isEnabled
    }

    fun startScan(context: Context, scanCallback: ScanCallback) {
        if (!isScanning) {// Stops scanning after a pre-defined scan period.
            handler?.postDelayed({
                isScanning = false
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.BLUETOOTH_SCAN
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return@postDelayed
                }
                blEScanner.startScan(scanCallback)
                }, SCAN_PERIOD)
            isScanning = true
            blEScanner.startScan(scanCallback)
        }
    }

    fun stopScan(context: Context, scanCallback: ScanCallback) {
        if (isScanning) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            blEScanner.stopScan(scanCallback)
            isScanning = false
        }
    }
}