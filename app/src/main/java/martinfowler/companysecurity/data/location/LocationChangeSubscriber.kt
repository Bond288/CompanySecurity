package martinfowler.companysecurity.data.location

import android.location.Location

interface LocationChangeSubscriber {
    fun notAllowed()
    fun location(location: Location?)
}