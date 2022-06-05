package data

class UserRegistrationData {
    var email = "eve.holt@reqres.in"
    var password = "pistol"


    fun fillUnSuccessData() {
        email = "sydney@fife"
    }
}

class RegistrationResponseData {
    var id = 0
    var token: String? = null
}

