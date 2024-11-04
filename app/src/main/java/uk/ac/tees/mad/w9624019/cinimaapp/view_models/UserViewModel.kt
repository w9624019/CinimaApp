package uk.ac.tees.mad.w9624019.cinimaapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uk.ac.tees.mad.w9624019.cinimaapp.data.User
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {

    private var _user: User? = null

    val user: User?
        get(): User? = _user

    val isLoggedIn: Boolean
        get() = _user?.isLoggedIn ?: false // ?: is the Elvis operator

    fun doLogin() {
        viewModelScope.launch {
            _user = User.createFakeUser()
        }
    }
}