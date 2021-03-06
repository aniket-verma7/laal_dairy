package com.project.laaldairy.signIn_signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.project.laaldairy.R;
import com.project.laaldairy.constant.Keys;
import com.project.laaldairy.dto.UserData;

public class WelcomeActivity extends AppCompatActivity {

    private UserData data;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init(){

        getWindow().setNavigationBarColor(getResources().getColor(R.color.text_background));
        getWindow().setStatusBarColor(getResources().getColor(R.color.text_background));

        data = (UserData) getIntent().getSerializableExtra(Keys.USER_DATA);
        int index = data.getName().indexOf(' ');

        welcomeMessage = findViewById(R.id.welcomeMessage);

        if(index != -1) welcomeMessage.setText("Hello, "+data.getName().substring(0,index));
        else welcomeMessage.setText("Hello, "+data.getName());
    }
}