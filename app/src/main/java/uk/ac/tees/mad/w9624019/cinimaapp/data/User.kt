package uk.ac.tees.mad.w9624019.cinimaapp.data

data class User(val fname: String, val sname: String, val isLoggedIn: Boolean = false){
    companion object {
        fun createFakeUser() : User {
            return User("Fake", "User", true)
        }
    }
}
