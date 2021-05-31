package com.namit.ex10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.namit.ex10.networking.UsersRepository
import com.namit.ex10.networking.models.DataHolder
import com.namit.ex10.networking.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userNameEditText = findViewById<EditText>(R.id.edit_username)
        val saveButton = findViewById<Button>(R.id.btn_save_username)
        saveButton.setOnClickListener {
            val userName = userNameEditText.text.toString()
            if (TextUtils.isEmpty(userName)) return@setOnClickListener
            showProgressBar()
            UsersRepository
                .getToken(userName)
                .enqueue(tokenCallback())
        }
    }

    private fun tokenCallback() = object : Callback<DataHolder<String?>> {
        override fun onFailure(call: Call<DataHolder<String?>>?, t: Throwable?) {
            Toast.makeText(this@MainActivity, "Error getting token", Toast.LENGTH_SHORT).show()
            hideProgressBar()
        }

        override fun onResponse(
            call: Call<DataHolder<String?>>,
            response: Response<DataHolder<String?>>
        ) {
            if (!response.isSuccessful || response.body()?.data == null) {
                hideProgressBar()
                Toast.makeText(
                    this@MainActivity,
                    "Error getting token",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            getUserDetails(response.body()!!.data!!)
        }
    }

    private fun getUserDetails(token: String) {
        UsersRepository
            .getUser(token)
            .enqueue(userCallback(token))
    }

    private fun userCallback(token: String) = object : Callback<DataHolder<User?>> {
        override fun onFailure(call: Call<DataHolder<User?>>?, t: Throwable?) {
            Toast.makeText(this@MainActivity, "Error getting user", Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        }

        override fun onResponse(
            call: Call<DataHolder<User?>>,
            response: Response<DataHolder<User?>>
        ) {
            if (!response.isSuccessful || response.body()?.data == null) {
                Toast.makeText(this@MainActivity, "Error getting user", Toast.LENGTH_SHORT).show()
                hideProgressBar()
            } else {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                intent.putExtra("token", token)
                intent.putExtra("user", response.body()!!.data)
                startActivity(intent)
            }
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        findViewById<Button>(R.id.btn_save_username).visibility = View.GONE
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
        findViewById<Button>(R.id.btn_save_username).visibility = View.VISIBLE
    }
}