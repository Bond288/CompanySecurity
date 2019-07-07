package martinfowler.companysecurity.data.location

import android.location.Location

class SecurityLocationRepo: SecurityLocation {

    //TODO get security location list from server
    private val securityLocation = "location from server"

    override fun checkLocation(location: Location): Boolean {
        return location.equals(securityLocation)
    }

}