
package com.example.sonika.pushnotificationapp.firebase

import android.util.Log
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().getToken().toString()
        Log.d("token", "Refreshed token: " + refreshedToken)
        Toast.makeText(applicationContext, refreshedToken, Toast.LENGTH_SHORT).show()

    }
}
