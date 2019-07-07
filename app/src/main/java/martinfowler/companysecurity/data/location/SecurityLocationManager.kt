package martinfowler.companysecurity.data.location

import android.location.Location

class SecurityLocationManager(private val securityLocationRepo: SecurityLocationRepo) {

    fun isLocationSecurity(location: Location): Boolean {
        return securityLocationRepo.checkLocation(location)
    }
}