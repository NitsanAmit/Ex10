package com.namit.ex10

import android.widget.EditText
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], manifest=Config.NONE)
class MainActivityTest : TestCase() {

    var controller: ActivityController<MainActivity>? = null

    @Before
    fun setup() {
        controller = Robolectric.buildActivity(MainActivity::class.java).create().visible()
    }

    @Test
    fun when_activityStarts_editTextShouldBeEmpty() {
        val mainActivity = controller!!.get();
        val editText = mainActivity.findViewById<EditText>(R.id.edit_username)
        val userInput = editText.text.toString()
        assertTrue(userInput.isEmpty())
    }

}