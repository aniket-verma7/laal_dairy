package com.project.laaldairy.signIn_signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;
import com.project.laaldairy.R;
import com.project.laaldairy.constant.Keys;
import com.project.laaldairy.dto.UserData;

import java.security.Key;

public class VerifyOTP extends AppCompatActivity {

    private TextView numberIndicator,verifyNumber;
    private Pinview otp;
    private boolean newUser;
    private UserData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        init();
        applyClickListener();
    }

    //finding all id here...
    private void init(){
        getWindow().setNavigationBarColor(getResources().getColor(R.color.background_color));
        data = (UserData) getIntent().getSerializableExtra(Keys.USER_DATA);

        numberIndicator = findViewById(R.id.numberIndicator);
        verifyNumber = findViewById(R.id.verifyNumber);
        otp = findViewById(R.id.optView);

        numberIndicator.setText(data.getPhone());
        newUser = getIntent().getBooleanExtra(Keys.NEW_USER,false);
    }

    private void applyClickListener() {
        verifyNumber.setOnClickListener(verifyNumber->{
            if(otp.getValue().length()<5){
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }else{
                if(newUser) {
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    intent.putExtra(Keys.USER_DATA,data);
                    startActivity(intent);
                }else{
                    //TODO: call home page here
                }
            }
        });
    }
}