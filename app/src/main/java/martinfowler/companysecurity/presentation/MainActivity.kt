package martinfowler.companysecurity.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import martinfowler.companysecurity.data.camera.CameraManager
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.ImageButton
import martinfowler.companysecurity.R
import martinfowler.companysecurity.data.connection.MobileDataManager


class MainActivity : AppCompatActivity() {
    private lateinit var button: ImageButton
    private lateinit var cameraManger: CameraManager
    private lateinit var mobileDataManager: MobileDataManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        cameraManger = CameraManager(this)
        mobileDataManager = MobileDataManager(this)
        button = findViewById(R.id.button)
        buttonImage()
        button.setOnClickListener{
            if (cameraManger.isAdminActive()) {
                toggleMobileData()
                if (!cameraManger.getDisabled()) {
                    cameraManger.cameraOff()
                } else {
                    cameraManger.cameraOn()
                }
                buttonImage()
            } else {
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cameraManger.getAdminComponent())
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "DeviceAdmin ON/OFF")
                startActivityForResult(intent, 1)
            }
        }
    }

    fun buttonImage(){
        button.isSelected = cameraManger.getDisabled()
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