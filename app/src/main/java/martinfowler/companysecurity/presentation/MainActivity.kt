package martinfowler.companysecurity.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import martinfowler.companysecurity.data.camera.CameraManager
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.location.Location
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import martinfowler.companysecurity.R
import martinfowler.companysecurity.data.connection.MobileDataManager
import martinfowler.companysecurity.data.location.SecurityLocationManager

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var icon: ImageView
    private lateinit var button: Button
    private lateinit var password: EditText
    private lateinit var passwordOkButton: Button
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainPresenter = MainPresenter(context = this, mainView = this)
        icon = findViewById(R.id.icon)
        button = findViewById(R.id.button)
        password = findViewById(R.id.password)
        passwordOkButton = findViewById(R.id.passwordOk)
        mainPresenter.checkState()
        button.setOnClickListener { mainPresenter.toggle() }
        passwordOkButton.setOnClickListener { mainPresenter.toggle() }
    }

    override fun toggleState(isLocked: Boolean) {
        icon.isSelected = isLocked
        button.text = if (isLocked) {
            getString(R.string.unblock)
        } else {
            getString(R.string.block)
        }
        password.visibility = View.INVISIBLE
        passwordOkButton.visibility = View.INVISIBLE
        password.setText("")
    }

    override fun password(): String = password.text.toString()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun showPassword() {
        password.visibility = View.VISIBLE
        password.requestFocus()
        passwordOkButton.visibility = View.VISIBLE
    }

    override fun initState(disabled: Boolean) {
        button.text = if (disabled) {
            getString(R.string.unblock)
        } else {
            getString(R.string.block)
        }
    }
}