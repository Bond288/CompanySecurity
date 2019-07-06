package martinfowler.companysecurity.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import martinfowler.companysecurity.R
import martinfowler.companysecurity.data.CameraManager

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var cameraManger: CameraManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        cameraManger = CameraManager(this)
        button = findViewById(R.id.button)
        button.setOnClickListener{
            if (cameraManger.isAdminActive()) {
                if (cameraManger.getDisabled()) {
                    cameraManger.cameraOn()
                } else {
                    cameraManger.cameraOff()
                }
            }
        }
    }


}