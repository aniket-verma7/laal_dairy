package com.project.laaldairy.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.laaldairy.R;
import com.project.laaldairy.fragments.CardFragment;
import com.project.laaldairy.fragments.HomeFragment;
import com.project.laaldairy.fragments.ProfileFragment;
import com.project.laaldairy.fragments.TimeLineFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private CardFragment cardFragment;
    private TimeLineFragment timeLineFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Custom);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init(){
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mListener);

        homeFragment = new HomeFragment();
        cardFragment = new CardFragment();
        timeLineFragment = new TimeLineFragment(getSupportFragmentManager());
        profileFragment = new ProfileFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,homeFragment).commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case R.id.menu_card:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,cardFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case R.id.menu_timeline:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,timeLineFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case R.id.menu_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,profileFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;
            }
            return true;
        }
    };

}