package com.acadgild.kotlinlogindemo

class HomeActivity : android.support.v7.app.AppCompatActivity() {
    private var mUsernameTv: android.widget.TextView? = null
    private var _username: String? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.acadgild.kotlinlogindemo.R.layout.activity_home)

        mUsernameTv = findViewById(com.acadgild.kotlinlogindemo.R.id.textView_homeActivity_username) as android.widget.TextView
        val intentData = intent
        _username = intentData.getStringExtra("USERNAME")
        mUsernameTv!!.text = "Welcome to " + _username!!
        android.widget.Toast.makeText(this, "Welcome to " + _username!!, android.widget.Toast.LENGTH_SHORT).show()
    }
}
