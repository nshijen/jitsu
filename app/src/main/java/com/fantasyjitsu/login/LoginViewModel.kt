package com.fantasyjitsu.login

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.fantasyjitsu.api.Api
import com.fantasyjitsu.api.Network
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.qualifiers.ActivityContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import java.util.*

class LoginViewModel @ViewModelInject constructor(@ActivityContext val context: Context) :
    ViewModel() {
    private var mGoogleSignInClient: GoogleSignInClient? = null
    val googleSignInClientLiveData: MutableLiveData<GoogleSignInClient> = MutableLiveData();
    fun validateLoginFields(
        userName: String?,
        password: String?
    ) {
        //if (userName!!.isEmpty()) view!!.onUserNameEmpty() else if (password!!.isEmpty()) view!!.onPasswordEmpty() else view!!.onValidationSuccess()
    }

    fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
        mGoogleSignInClient?.signOut()
        googleSignInClientLiveData.value = mGoogleSignInClient
    }

    fun facebookLogin(callbackManager: CallbackManager?) {
        val loginManager: LoginManager = LoginManager.getInstance()
        loginManager.logOut()

        loginManager.logInWithReadPermissions(
            context as LoginActivity,
            Arrays.asList("public_profile")
        )
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                val accessToken = loginResult?.accessToken
                val isLoggedIn = accessToken != null && accessToken.isExpired
                val request =
                    GraphRequest.newMeRequest(accessToken) { jsonObject: JSONObject, graphResponse: GraphResponse ->
                        Log.d("Shijen", "onSuccess: " + jsonObject.toString())

                    }

                request.executeAsync()
            }

            override fun onCancel() {
                Log.d("Shijen", "onCancel: ")
            }

            override fun onError(error: FacebookException?) {
                Log.d("Shijen", "onError: " + error?.message)
            }
        })
    }

    fun login(userName: String?, password: String?) {
        val api: Api = Network.createRetrofitService(Api::class.java)
        val observable = api.login(userName, password)
        observable?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe()
    }
}