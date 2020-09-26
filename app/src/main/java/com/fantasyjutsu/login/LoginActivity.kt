package com.fantasyjutsu.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.fantasyjutsu.main.MainActivity
import com.fantasyjutsu.R
import com.fantasyjutsu.Utility
import com.fantasyjutsu.databinding.ActivityLoginBinding
import com.fantasyjutsu.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout

    private lateinit var callbackManager: CallbackManager
    private val RC_SIGN_IN: Int = 100
    private val TAG = LoginActivity::class.java.simpleName
    private val loginType: Int = 1

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(com.fantasyjutsu.login.LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initializeToolBar()
        initialize()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.googleSignInClientLiveData.observe(this, Observer{
            googleSignIn(it)
        })
    }

    private fun initialize() {
        callbackManager = CallbackManager.Factory.create()
        binding.llRegister.setOnClickListener{startActivity(Intent(this, RegisterActivity::class.java))}
        binding.btNext.setOnClickListener{viewModel.validateLoginFields(
            binding.etEmail.text.toString().trim(),
            binding.etPassword.text.toString())}
        binding.btFacebook.setOnClickListener{viewModel.facebookLogin(callbackManager)}
        binding.btGoogle.setOnClickListener{viewModel.googleLogin()}
    }

    private fun initializeToolBar() {
        /*val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)*/
        //val textView = toolbar.findViewById<TextView>(R.id.tvToolbarTitle)
        //textView.setText(R.string.login)
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        //toolbar.setNavigationOnClickListener { view: View? -> onBackPressed() }
    }

    override fun onStart() {
        super.onStart()
        val lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this)
        if(lastSignedInAccount!=null){

        }
    }

    fun googleSignIn(mGoogleSignInClient: GoogleSignInClient?) {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun onUserNameError() {
        tilEmail.error = getString(R.string.invalid_user_name)
    }

    fun onUserNameEmpty() {
        tilEmail.error = getString(R.string.user_name_empty)
    }

    fun onPasswordError() {
        tilPassword.error = getString(R.string.invalid_password)
    }

    fun onPasswordEmpty() {
        tilPassword.error = getString(R.string.password_empty)
    }

    fun onValidationSuccess() {
        if (Utility.isNetworkAvailable(this)) viewModel.login(
            binding.etEmail.text.toString().trim(),
            binding.etPassword.text.toString()
        ) else Toast.makeText(
            this,
            getString(R.string.please_check_your_internet_connection),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onSuccessLogin() {
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
    }
/*


    @OnTextChanged(value = R.id.etEmail, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    fun onEmailTextChange(editable: Editable) {
        if (editable.toString().trim { it <= ' ' }.length > 0) tilEmail!!.error = null
    }

    @OnTextChanged(value = R.id.etPassword, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    fun onPasswordTextChange(editable: Editable) {
        if (editable.toString().trim { it <= ' ' }.length > 0) tilPassword!!.error = null
    }
*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val displayName = account?.displayName;
            val email = account?.email
            Log.d(TAG, "handleSignInResult: displayname, email:"+displayName+email)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }
}