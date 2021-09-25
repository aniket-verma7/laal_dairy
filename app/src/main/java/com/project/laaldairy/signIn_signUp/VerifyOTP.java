package com.project.laaldairy.signIn_signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.project.laaldairy.R;

public class VerifyOTP extends AppCompatActivity {

    private TextView numberIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        init();
    }

    //finding all id here...
    private void init(){
        numberIndicator = findViewById(R.id.numberIndicator);
        numberIndicator.setText(getIntent().getStringExtra("mob"));
    }
}