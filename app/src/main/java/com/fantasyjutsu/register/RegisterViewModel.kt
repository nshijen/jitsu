package com.fantasyjutsu.register

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.qualifiers.ActivityContext

class RegisterViewModel @ViewModelInject constructor(@ActivityContext context: Context) :
    ViewModel() {
    private var mGoogleSignInClient: GoogleSignInClient? = null
    fun googleLogin() {
        /*val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso)
        mGoogleSignInClient?.signOut()
        view!!.googleSignIn(mGoogleSignInClient)*/
    }

    fun facebookLogin() {

    }
}