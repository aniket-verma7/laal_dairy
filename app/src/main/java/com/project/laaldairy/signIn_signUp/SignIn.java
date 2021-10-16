package com.project.laaldairy.signIn_signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.hbb20.CountryCodePicker;
import com.project.laaldairy.R;
import com.project.laaldairy.adapter.ImageSliderAdapter;
import com.project.laaldairy.constant.Keys;
import com.project.laaldairy.dto.UserData;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity {

    private SliderView sliderView;
    private CountryCodePicker codePicker;
    private EditText userName,userNumber,userEmail;
    private TextView getOTP,codeIndicator,loginLink;
    private View newUserLayout;
    private LinearLayout nameLayout,emailLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        initImageSlider();
        applyClickListener();
    }

    //finding all id here...
    private void init(){
        newUserLayout = findViewById(R.id.new_user_layout);

        nameLayout = newUserLayout.findViewById(R.id.nameParent);
        emailLayout = newUserLayout.findViewById(R.id.emailParent);

        codePicker = newUserLayout.findViewById(R.id.codePicker);
        userName = newUserLayout.findViewById(R.id.userName);
        userNumber = newUserLayout.findViewById(R.id.userNumber);
        userEmail = newUserLayout.findViewById(R.id.userEmail);
        getOTP = newUserLayout.findViewById(R.id.getOTP);
        codeIndicator = newUserLayout.findViewById(R.id.codeIndicator);
        loginLink = newUserLayout.findViewById(R.id.loginLink);

        sliderView = findViewById(R.id.imageSlider);
    }

    //setting image cycle adapter here...
    private void initImageSlider(){
        sliderView.setSliderAdapter(new ImageSliderAdapter());
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.startAutoCycle();
    }

    //applying click callback to views
    private void applyClickListener() {
        codePicker.setOnCountryChangeListener(()->{
            codeIndicator.setText("+"+codePicker.getFullNumber());
        });

        getOTP.setOnClickListener(clicked->{
            String number = userNumber.getText().toString().trim();
            if(number.isEmpty() || number.length() < 10){
                userNumber.setError("Invalid Number");
            }else{
                Intent intent = new Intent(this,VerifyOTP.class);
                UserData data = new UserData();
                if(nameLayout.getVisibility() == View.VISIBLE){
                   data.setName(userName.getText().toString());
                   data.setEmail(userEmail.getText().toString());
                   intent.putExtra(Keys.NEW_USER,true);
                }
                data.setPhone(codeIndicator.getText().toString()+number);

                intent.putExtra(Keys.USER_DATA,data);
                startActivity(intent);
            }
        });

        loginLink.setOnClickListener(changeView->{

            if(nameLayout.getVisibility() == View.VISIBLE) {
                nameLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.GONE);
                loginLink.setText(R.string.sign_up_text);
            }else {
                nameLayout.setVisibility(View.VISIBLE);
                emailLayout.setVisibility(View.VISIBLE);
                loginLink.setText(R.string.login_text);
            }
        });
    }
}