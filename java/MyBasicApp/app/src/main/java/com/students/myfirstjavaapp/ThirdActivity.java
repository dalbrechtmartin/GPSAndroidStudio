package com.students.myfirstjavaapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    public static final String RESULT = "msgResult";
    private TextView m_msgTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle extras = getIntent().getExtras();
        String msg = extras.getString(MainActivity.MESSAGE_PARAMETER);

        m_msgTv = findViewById(R.id.messageTv);
        m_msgTv.setText(msg);
    }

    public void onClickYes(View view) {
        Intent intent = new Intent();
        intent.putExtra(RESULT, "I am READY");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onClickNo(View view) {
        Intent intent = new Intent();
        intent.putExtra(RESULT, "I am not Ready");
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}