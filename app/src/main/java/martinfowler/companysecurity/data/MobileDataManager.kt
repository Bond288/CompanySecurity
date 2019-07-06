package martinfowler.companysecurity.data

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.telephony.TelephonyManager
import java.lang.reflect.InvocationTargetException


class MobileDataManager(context: Context) {

    private var telephonyService: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


    @RequiresApi(Build.VERSION_CODES.O)
    fun mobileDataOn() {
        setMobileDataEnabled(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mobileDataOff() {
        setMobileDataEnabled(false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isMobileDataEnabled() =
        try {
            val method = telephonyService.javaClass.getMethod("getDataEnabled")
            method.invoke(telephonyService) as Boolean
        } catch (illegalAccess: IllegalAccessException) {
            false
        } catch (illegalArgument: IllegalArgumentException) {
            false
        } catch (illegalInvocationTarget: InvocationTargetException) {
            false
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMobileDataEnabled(enabled: Boolean) {
        try {
            val method = telephonyService.javaClass.getMethod("setDataEnabled", Boolean::class.java)
            method.invoke(telephonyService, enabled)
        } catch (illegalAccess: IllegalAccessException) {

        } catch (illegalArgument: IllegalArgumentException) {

        } catch (illegalInvocationTarget: InvocationTargetException) {

        }
    }
}