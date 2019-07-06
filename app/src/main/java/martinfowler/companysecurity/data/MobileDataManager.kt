package martinfowler.companysecurity.data

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.telephony.TelephonyManager



class MobileDataManager(context: Context) {

    private var telephonyService: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @TargetApi(Build.VERSION_CODES.O)
    fun mobileDataOn() {
        val method = telephonyService.javaClass.getMethod("setDataEnabled", Boolean.javaClass)
        method.invoke(telephonyService, true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mobileDataOff() {
        val method = telephonyService.javaClass.getMethod("setDataEnabled", Boolean.javaClass)
        method.invoke(telephonyService, false)
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun isMobileDataEnabled(): Boolean {
        val method = telephonyService.javaClass.getMethod("getDataEnabled")
        return method.invoke(telephonyService) as Boolean
    }
}