package martinfowler.companysecurity.data

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context

class CameraManager(context: Context) {
    private var devicePolicyManager: DevicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    private val componentName = ComponentName(context, CompanyDeviceAdminReceiver::class.java)

    fun cameraOff(){
        devicePolicyManager.setCameraDisabled(componentName, true)
    }

    fun cameraOn(){
        devicePolicyManager.setCameraDisabled(componentName, true)
    }

    fun isAdminActive() = devicePolicyManager.isAdminActive(componentName)

    fun getDisabled() = devicePolicyManager.getCameraDisabled(componentName)


}