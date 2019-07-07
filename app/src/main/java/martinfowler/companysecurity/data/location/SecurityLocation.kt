package martinfowler.companysecurity.data.location

import android.location.Location

interface SecurityLocation {

    fun checkLocation(location: Location): Boolean
}