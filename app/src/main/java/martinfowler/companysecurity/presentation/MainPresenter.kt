package martinfowler.companysecurity.presentation

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.support.annotation.RequiresApi
import martinfowler.companysecurity.data.camera.CameraManager
import martinfowler.companysecurity.data.connection.MobileDataManager
import martinfowler.companysecurity.data.location.SecurityLocationManager
import martinfowler.companysecurity.data.location.SecurityLocationRepo

class MainPresenter(private val context: Context, private val mainView: MainView) {
    private val cameraManger = CameraManager(context)
    private val mobileDataManager = MobileDataManager(context)
    private val locationManager = SecurityLocationManager(SecurityLocationRepo())

    fun toggle() {
        if (cameraManger.isAdminActive()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                toggleMobileData()
            }
            toggleCamera()
            mainView.buttonImage(cameraManger.getDisabled())
        } else {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cameraManger.getAdminComponent())
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "DeviceAdmin ON/OFF")
            if (context is Activity) {
                context.startActivityForResult(intent, 1)
            }
        }
    }

    private fun toggleCamera() {
        if (!cameraManger.getDisabled()) {
            cameraManger.cameraOff()
        } else {
            cameraManger.cameraOn()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkLocation(location: Location) {
        if (locationManager.isLocationSecurity(location)) {
            toggleCamera()
            toggleMobileData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toggleMobileData() {
        if (mobileDataManager.isMobileDataEnabled()) {
            mobileDataManager.mobileDataOff()
        } else {
            mobileDataManager.mobileDataOn()
        }
    }
}