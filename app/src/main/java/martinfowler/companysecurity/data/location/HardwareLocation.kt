package martinfowler.companysecurity.data.location

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat.checkSelfPermission
import java.util.concurrent.TimeUnit

class HardwareLocation(private val context: Context) {
    private val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
    private val updatePeriod = TimeUnit.SECONDS.toMillis(10)
    private val updateMeters = 10f
    private var subscriber: LocationChangeSubscriber? = null

    fun subscribe(subscriber: LocationChangeSubscriber){
        this.subscriber = subscriber
        if (checkPermission()) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                updatePeriod,
                updateMeters,
                gpsLocationListener
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                updatePeriod,
                updateMeters,
                networkLocationListener
            )
        }
    }

    fun unsubscribe(){
        locationManager.removeUpdates(gpsLocationListener);
        locationManager.removeUpdates(networkLocationListener)
        subscriber = null
    }

    private fun checkPermission() = when{
        checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED 
                && checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> true
        else -> false
    }

    private val gpsLocationListener = object: LocationListener {
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            
        }

        override fun onProviderEnabled(provider: String?) {
            
        }

        override fun onProviderDisabled(provider: String?) {
            
        }

        override fun onLocationChanged(location: Location?) {
            subscriber?.apply {
                location(location)
            }
        }
    }

    private val networkLocationListener = object: LocationListener {
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

        override fun onLocationChanged(location: Location?) {
            subscriber?.apply {
                location(location)
            }
        }
    }
}