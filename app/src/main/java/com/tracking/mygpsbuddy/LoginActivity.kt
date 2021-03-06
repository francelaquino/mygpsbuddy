package com.tracking.mygpsbuddy

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnregister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}
