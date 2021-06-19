package com.namit.ex10

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.namit.ex10.networking.models.User
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest=Config.NONE)
class ProfileActivityTest : TestCase() {

    @Test
    fun when_activityStarts_namesShouldMatchInput() {
        val user = User("Nitsan", "Amit", "https://avatars.githubusercontent.com/u/29373422 ")
        val intent = Intent()
        intent.putExtra("user", user)
        intent.putExtra("token", "")
        val profileActivity: ProfileActivity = Robolectric.buildActivity(ProfileActivity::class.java, intent).create().visible().get()
        val prettyNameTextView = profileActivity.findViewById<TextView>(R.id.txt_pretty_name)
        val userNameTextView = profileActivity.findViewById<TextView>(R.id.txt_user_name)
        val prettyNameText = prettyNameTextView.text.toString()
        val userNameText = userNameTextView.text.toString()

        assertEquals(userNameText, "Nitsan")
        assertEquals(prettyNameText, "Amit")
    }

    @Test
    fun when_prettyNameClicked_shouldMakeEditTextVisibleInsteadOfTextView() {
        val user = User("Nitsan", "Amit", "https://avatars.githubusercontent.com/u/29373422 ")
        val intent = Intent()
        intent.putExtra("user", user)
        intent.putExtra("token", "")
        val profileActivity: ProfileActivity = Robolectric.buildActivity(ProfileActivity::class.java, intent).create().visible().get()
        val prettyNameTextView = profileActivity.findViewById<TextView>(R.id.txt_pretty_name)
        val editUserName = profileActivity.findViewById<EditText>(R.id.edit_username)

        assertEquals(prettyNameTextView.visibility, View.VISIBLE)
        assertEquals(editUserName.visibility, View.GONE)
        prettyNameTextView.performClick()
        assertEquals(prettyNameTextView.visibility, View.GONE)
        assertEquals(editUserName.visibility, View.VISIBLE)
        assertEquals(editUserName.text.toString(), "Amit")
    }

}