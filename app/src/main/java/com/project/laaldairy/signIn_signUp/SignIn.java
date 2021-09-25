package com.project.laaldairy.signIn_signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.hbb20.CountryCodePicker;
import com.project.laaldairy.R;
import com.project.laaldairy.adapter.ImageSliderAdapter;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

public class SignIn extends AppCompatActivity {

    private SliderView sliderView;
    private CountryCodePicker codePicker;
    private EditText userNumber;
    private TextView getOTP,codeIndicator;

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
        codePicker = findViewById(R.id.codePicker);
        userNumber = findViewById(R.id.userNumber);
        getOTP = findViewById(R.id.getOTP);
        codeIndicator = findViewById(R.id.codeIndicator);
        sliderView = findViewById(R.id.imageSlider);
    }

    //setting image cycle adapter here...
    private void initImageSlider(){
        sliderView.setSliderAdapter(new ImageSliderAdapter());
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.startAutoCycle();
    }

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
                intent.putExtra("mob",codeIndicator.getText().toString()+" "+number);
                startActivity(intent);
            }
        });
    }
}