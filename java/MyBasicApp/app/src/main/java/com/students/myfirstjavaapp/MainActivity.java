package com.students.myfirstjavaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MESSAGE_PARAMETER = "message";
    private Button m_addBt;
    private Button m_subBt;
    private TextView m_resultTv;
    private TextView m_openActivityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_addBt = findViewById(R.id.addBt);
        m_addBt.setOnClickListener(this);
        m_subBt = findViewById(R.id.subBt);
        m_subBt.setOnClickListener(this);
        m_resultTv = findViewById(R.id.resultTv);

        m_openActivityResult = findViewById(R.id.openActivityResultTv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity", "onPause");
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.addBt) {
            Log.v("MainActivity", "Add Button Clicked");
            String resStrg = m_resultTv.getText().toString();
            int resValue = Integer.valueOf(resStrg);
            resValue++;
            m_resultTv.setText(String.valueOf(resValue));
        }
        else if( v.getId() == R.id.subBt) {
            Log.v("MainActivity", "Subtract Button Clicked");
            String resStrg = m_resultTv.getText().toString();
            int resValue = Integer.valueOf(resStrg);
            resValue--;
            m_resultTv.setText(String.valueOf(resValue));
        }
    }

    public void onClickShowToast(View view) {
        Toast.makeText(this,
                "Voici mon text que je veux afficher",
                Toast.LENGTH_LONG).show();
    }

    public void onClickShowSnackBar(View view) {
        Snackbar.make(view,
                        "My SnackBar title",
                        Snackbar.LENGTH_LONG)
                .setAction("Click here", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("MainActivity",
                                "SnackBar Button Clicked");
                    }
                })
                .show();
    }

    public void onClickOpenActivity1(View view) {
        Intent intent = new Intent(this,
                MainActivity1.class);
        startActivity(intent);
    }

//    public void onClickOpenThirdActivity(View view) {
//        Intent intent = new Intent(this,
//                ThirdActivity.class);
//        intent.putExtra(MESSAGE_PARAMETER, "Are you ready?");
//        startActivityForResult(intent, 1234);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            m_openActivityResult.setTextColor(this.getColor(android.R.color.holo_green_light));
//        } else {
//            m_openActivityResult.setTextColor(this.getColor(android.R.color.holo_red_light));
//        }
//        m_openActivityResult.setText(data.getStringExtra(ThirdActivity.RESULT));
//    }


    private final ActivityResultLauncher<Intent> m_selectFileResult =
            registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        m_openActivityResult.setTextColor(MainActivity.this.getColor(android.R.color.holo_green_light));
                    } else {
                        m_openActivityResult.setTextColor(MainActivity.this.getColor(android.R.color.holo_red_light));
                    }
                    assert result.getData() != null;
                    String resultText = result.getData().getStringExtra(ThirdActivity.RESULT);
                    m_openActivityResult.setText(resultText);
                }
            });

    public void onClickOpenThirdActivity(View view) {
        Intent intent = new Intent(this,
                ThirdActivity.class);
        intent.putExtra(MESSAGE_PARAMETER, "Are you ready?");
        m_selectFileResult.launch(intent);
    }
}