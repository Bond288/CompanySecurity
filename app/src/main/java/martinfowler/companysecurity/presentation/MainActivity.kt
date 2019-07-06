package martinfowler.companysecurity.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import martinfowler.companysecurity.data.CameraManager
import android.app.admin.DevicePolicyManager
import android.content.Intent
import martinfowler.companysecurity.R


class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var cameraManger: CameraManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        cameraManger = CameraManager(this)
        button = findViewById(R.id.button)
        buttonText()
        button.setOnClickListener{
            if (cameraManger.isAdminActive()) {
                if (!cameraManger.getDisabled()) {
                    cameraManger.cameraOff()
                } else {
                    cameraManger.cameraOn()
                }
                buttonText()
            } else {
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cameraManger.getAdminComponent())
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "DeviceAdmin ON/OFF")
                startActivityForResult(intent, 1)
            }
        }
    }

    fun buttonText(){
        if (cameraManger.getDisabled()) {
            button.text = getString(R.string.unblock)
        } else {
            button.text = getString(R.string.block)
        }
    }
}