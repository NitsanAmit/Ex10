package com.namit.ex10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.namit.ex10.networking.UsersRepository
import com.namit.ex10.networking.models.DataHolder
import com.namit.ex10.networking.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    companion object {
        val BASE_URL: String = "https://hujipostpc2019.pythonanywhere.com"
    }

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prettyNameView = findViewById<TextView>(R.id.txt_pretty_name)
        val editUserName = findViewById<EditText>(R.id.edit_username)

        if (intent?.extras == null) {
            return
        }
        user = if (savedInstanceState?.getSerializable("user") != null)
            (savedInstanceState.getSerializable("user") as User)
        else (intent.extras!!["user"] as User)
        val token = intent.extras!!["token"] as String
        Glide
            .with(this)
            .load(BASE_URL + user!!.imageUrl)
            .into(findViewById(R.id.img_profile_img))
        prettyNameView.text =
            if (TextUtils.isEmpty(user!!.prettyName)) user!!.username else user!!.prettyName
        findViewById<TextView>(R.id.txt_user_name).text = user!!.username


        prettyNameView.setOnClickListener {
            editUserName.setText(prettyNameView.text)
            prettyNameView.visibility = View.GONE
            editUserName.visibility = View.VISIBLE
        }

        findViewById<LinearLayout>(R.id.layout_root).setOnClickListener {
            if (editUserName.visibility == View.VISIBLE) {
                val newUserName = editUserName.text.toString()
                if (!TextUtils.isEmpty(newUserName) && !newUserName.equals(user!!.prettyName)) {
                    UsersRepository
                        .updatePrettyName(token, newUserName)
                        .enqueue(nameUpdateCallback())
                }
            }
        }
    }

    private fun nameUpdateCallback() = object : Callback<DataHolder<User?>> {
        override fun onFailure(call: Call<DataHolder<User?>>?, t: Throwable?) {
            Toast.makeText(this@ProfileActivity, "Error updating name", Toast.LENGTH_SHORT).show()
            findViewById<TextView>(R.id.txt_pretty_name).visibility = View.GONE
            findViewById<EditText>(R.id.edit_username).visibility = View.VISIBLE
        }

        override fun onResponse(
            call: Call<DataHolder<User?>>,
            response: Response<DataHolder<User?>>
        ) {
            if (!response.isSuccessful || response.body()?.data == null) {
                Toast.makeText(this@ProfileActivity, "Error updating name", Toast.LENGTH_SHORT)
                    .show()
            }
            user?.prettyName = response.body()!!.data!!.prettyName
            findViewById<TextView>(R.id.txt_pretty_name).text = response.body()!!.data!!.prettyName
            findViewById<TextView>(R.id.txt_pretty_name).visibility = View.VISIBLE
            findViewById<EditText>(R.id.edit_username).visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("user", user)
        super.onSaveInstanceState(outState)
    }

}