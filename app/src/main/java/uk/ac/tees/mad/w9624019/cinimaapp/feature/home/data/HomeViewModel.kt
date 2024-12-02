package uk.ac.tees.mad.w9624019.cinimaapp.feature.home.data

import android.content.Context
import android.os.BatteryManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.CinimaAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.Screen


class HomeViewModel() : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName

    var errorMsg: String = ""
    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val emailId: MutableLiveData<String> = MutableLiveData()

    fun logOut() {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                CinimaAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                errorMsg = "Inside sign out is not complete"
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }    }

    fun getUserData(){
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }

   fun getBatteryManager(context: Context) : Int{
       val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager


       val batLevel: Int = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

       // Get the battery percentage and store it in a INT variable
       val database = FirebaseDatabase.getInstance()
       val reference = database.getReference("batLevel")

       reference.setValue(batLevel)
       return  batLevel
   }
}