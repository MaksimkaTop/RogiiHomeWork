package data

class UserListData {
    var page = 0
    var per_page = 0
    var total = 0
    var total_pages = 0
    var data: ArrayList<User>? = null
    var support: Support? = null
}

class SingleUserData {
    var data: User? = null
    var support: Support? = null
}

class User {
    var id = 0
    var email: String? = null
    var first_name: String? = null
    var last_name: String? = null
    var avatar: String? = null
}

class Support {
    var url: String? = null
    var text: String? = null
}
