package martinfowler.companysecurity.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import martinfowler.companysecurity.data.camera.CameraManager
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.location.Location
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.ImageButton
import martinfowler.companysecurity.R
import martinfowler.companysecurity.data.connection.MobileDataManager
import martinfowler.companysecurity.data.location.SecurityLocationManager

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var button: ImageButton
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainPresenter = MainPresenter(context = this, mainView = this)
        button = findViewById(R.id.button)
        button.setOnClickListener { mainPresenter.toggle() }
    }

    override fun buttonImage(isLocked: Boolean) {
        button.isSelected = isLocked
    }
}