package com.students.myfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    companion object {
        const val RESULT = "result"
    }

    protected lateinit var m_messageTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val extras = getIntent().getExtras()
        val msg = extras?.getString(MainActivity.MY_PARAM)

        m_messageTv = findViewById(R.id.messageTv);
        m_messageTv.setText(msg);
    }

    fun onClickYes(view: View) {
        val returnIntent = Intent()
        returnIntent.putExtra(RESULT, "I am READY");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    fun onClickNo(view: View) {
        val returnIntent = Intent()
        returnIntent.putExtra(RESULT, "I am NOT READY");
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}