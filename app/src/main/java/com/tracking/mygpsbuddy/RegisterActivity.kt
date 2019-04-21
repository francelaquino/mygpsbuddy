package com.tracking.mygpsbuddy

import android.Manifest
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnimage.setOnClickListener{
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    //permission granted
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)

                }else{
                    //already allowed

                }
            }else{
                //system os is < current os
            }
        }

        btnlogin.setOnClickListener{
            onBackPressed()
        }


        btnregister.setOnClickListener {
            if(!isEditTextEmpty(firstname.text.toString(),"Please fill first name")){
                firstname.requestFocus()
                return@setOnClickListener
            }

            if(!isEditTextEmpty(lastname.text.toString(),"Please fill last name")){
                lastname.requestFocus()
                return@setOnClickListener
            }

            if(!isEditTextEmpty(emailaddress.text.toString(),"Please fill email address")){
                emailaddress.requestFocus()
                return@setOnClickListener
            }

            if(!isEditTextEmpty(password.text.toString(),"Please fill password")){
                password.requestFocus()
                return@setOnClickListener
            }

            if(password.text.toString()!=retypepassword.text.toString()){
                password.requestFocus()
                password.setText("")
                retypepassword.setText("")
                Toast.makeText(this@RegisterActivity, "Password mismatched!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
    }

    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE->{
                if(grantResults.isEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission from popgranted
                    pickImageFromGallery()
                }else{
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show()
                }
            }
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK && requestCode== IMAGE_PICK_CODE){
            btnimage.setImageURI(data?.data)
        }
    }


    companion object{
        private  val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private fun isEditTextEmpty(value:String , message:String) : Boolean{
        if (value.trim().isNullOrEmpty()) {
            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }

    }
}
