package com.fantasyjutsu.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fantasyjutsu.databinding.ActivityRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(){
    lateinit var binding: ActivityRegisterBinding

    lateinit var tilMobile: TextInputLayout
    lateinit var tilEmail: TextInputLayout

    lateinit var tilPassword: TextInputLayout
    lateinit var viewModel: RegisterViewModel

    private val RC_SIGN_IN = 100
    private val TAG: String = com.fantasyjutsu.login.LoginActivity::class.java.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeToolBar()
        initialize()
    }

    private fun initializeToolBar() {
        /*val toolbar: Toolbar = findViewById(R.id.registerToolBar)
        setSupportActionBar(toolbar)
        getSupportActionBar().setDisplayShowHomeEnabled(true)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        getSupportActionBar().setDisplayShowTitleEnabled(false)
        val textView = toolbar.findViewById<TextView>(R.id.tvToolbarTitle)
        textView.setText(R.string.register)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener { view: View? -> onBackPressed() }*/
    }

    private fun initialize() {
        binding.btNext.setOnClickListener {}
        binding.btFacebook.setOnClickListener { viewModel.facebookLogin() }
        binding.btGoogle.setOnClickListener { viewModel.googleLogin() }
    }

    fun googleSignIn(mGoogleSignInClient: GoogleSignInClient?) {
        val signInIntent: Intent? = mGoogleSignInClient?.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            Log.i(TAG, "handleSignInResult: " + account?.getDisplayName())
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
        }
    }
}