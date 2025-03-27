package com.students.myfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity() : AppCompatActivity() {

    companion object {
        const val MY_PARAM = "myParam"
    }

    protected lateinit var m_resultTv: TextView
    protected lateinit var m_activityResultTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MainActivity", "Hello World")

        m_resultTv = findViewById(R.id.resultTextView);

        m_activityResultTv = findViewById(R.id.activityResultTv)
    }

    fun onAddClick(view: View) {
        var resultStrg : String = m_resultTv.getText().toString()
        var result : Int = Integer.parseInt(resultStrg)
        result++
        m_resultTv.setText(Integer.toString(result))
    }

    fun onSubClick(view: View) {
        var resultStrg : String = m_resultTv.getText().toString()
        var result : Int = Integer.parseInt(resultStrg)
        result--
        m_resultTv.setText(Integer.toString(result))
    }

    fun onShowPopupClick(view: View) {
        Log.d("MainActivity",">onShowPopupClick")
        Toast.makeText(this, "Voici mon Toast", Toast.LENGTH_LONG).show()
    }

    fun onShowSnackBarClick(view: View) {
        Log.d("MainActivity",">onShowSnackBarClick");

        Snackbar.make(view, "New popup design", Snackbar.LENGTH_LONG)
            .setAction( "My Action", View.OnClickListener() {
                Log.v("MainActivity", "SnackBar Button clicked!")
            }).show();
    }

    fun onShowActivity1Click(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        val data: Intent? = result.data

        val resultStrg = data?.getStringExtra(ThirdActivity.RESULT)
        m_activityResultTv.setText(resultStrg)
        if(result.resultCode == Activity.RESULT_OK)
            m_activityResultTv.setTextColor(getColor(android.R.color.holo_green_dark))
        else
            m_activityResultTv.setTextColor(getColor(android.R.color.holo_orange_dark))
    }

    fun onShowActivity2Click(view: View) {
        val intent = Intent(this, ThirdActivity::class.java)
        intent.putExtra(MY_PARAM, "Are you ready?");
        resultLauncher.launch(intent)
    }
}