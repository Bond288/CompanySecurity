package martinfowler.companysecurity.data

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context

class CameraManager(context: Context) {
    private var devicePolicyManager: DevicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    private val adminDeviceReceiver = ComponentName(context, CompanyDeviceAdminReceiver::class.java)

    fun cameraOff(){
        devicePolicyManager.setCameraDisabled(adminDeviceReceiver, true)
    }

    fun cameraOn(){
        devicePolicyManager.setCameraDisabled(adminDeviceReceiver, false)
    }

    fun isAdminActive() = devicePolicyManager.isAdminActive(adminDeviceReceiver)

    fun getDisabled() = devicePolicyManager.getCameraDisabled(adminDeviceReceiver)

    fun getAdminComponent(): ComponentName {
        return adminDeviceReceiver
    }
}