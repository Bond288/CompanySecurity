package martinfowler.companysecurity.data.access

class CheckPassword {

    fun validationPassword(password: String): Boolean = password.length > 2
}