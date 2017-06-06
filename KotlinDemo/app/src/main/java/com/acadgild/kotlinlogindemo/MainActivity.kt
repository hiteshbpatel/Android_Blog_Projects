package com.acadgild.kotlinlogindemo

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.acadgild.kotlinlogindemo.R.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mEmailEt: EditText? = null
    private var mPasswordEt: EditText? = null
    private var mLoginBtn: Button? = null
    private var _userName: String? = null
    private var _password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        mEmailEt = findViewById(id.editText_mainActivity_email) as EditText
        mPasswordEt = findViewById(id.editText2_mainActivity_password) as EditText
        mLoginBtn = findViewById(id.button_mainActivity_login) as Button


        mLoginBtn!!.setOnClickListener(this)

        registerForContextMenu(mLoginBtn)
    }

    /*  For Option menu */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == id.action_home) {
            Toast.makeText(this, "Home option selected", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        when (v.id) {
            id.button_mainActivity_login -> collectData()


        }
    }

    private fun signIn() {
        if (_userName == "hitesh@acadgild.com" && _password == "hitesh") {
            val myIntent = Intent(this, HomeActivity::class.java)
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val progressDialog = ProgressDialog(this,
                    style.AppTheme_Dark_Dialog)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("SignIn...")
            progressDialog.show()

            android.os.Handler().postDelayed(
                    {
                        myIntent.putExtra("USERNAME", _userName)
                        startActivity(myIntent)
                        progressDialog.dismiss()
                        finish()
                    }, 3000)       // just for user feel


        } else {
            mEmailEt!!.setText("")
            mPasswordEt!!.setText("")
            Toast.makeText(this, "E_mail : hitesh@acadgild.com \n password : hitesh", Toast.LENGTH_LONG).show()
        }
    }

    private fun collectData() {
        _userName = mEmailEt!!.text.toString()
        _password = mPasswordEt!!.text.toString()
        validate()
    }

    private fun validate() {
        if (_userName == "" && _password == "") {
            mEmailEt!!.error = "Email_Id is Mandatory"
            mPasswordEt!!.error = "Password is Mandatory"
            mEmailEt!!.requestFocus()
        } else if (_userName == "") {
            mEmailEt!!.error = "Please enter your email_Id"
            mEmailEt!!.requestFocus()
        } else if (_password == "") {
            mPasswordEt!!.error = "Please enter your password"
            mPasswordEt!!.requestFocus()
        } else
            signIn()
    }
}
