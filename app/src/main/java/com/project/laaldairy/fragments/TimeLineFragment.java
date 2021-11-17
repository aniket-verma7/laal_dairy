package com.project.laaldairy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.project.laaldairy.R;
import com.project.laaldairy.adapter.ViewPagerAdapter;

import lombok.var;

public class TimeLineFragment extends Fragment {

    private TabLayout tabLayout;
    private FragmentManager supportManager;

    public TimeLineFragment(FragmentManager getSupportFragmentManager) {
        this.supportManager = getSupportFragmentManager;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_fragment, container, false);
        tabLayout = view.findViewById(R.id.statusTab);
        tabLayout.addOnTabSelectedListener(mListener);

        supportManager.beginTransaction().replace(R.id.transactionStatus,new CreditFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        return view;
    }

    private TabLayout.OnTabSelectedListener mListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()){
                case 0:
                    supportManager.beginTransaction().replace(R.id.transactionStatus,new CreditFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 1:
                    supportManager.beginTransaction().replace(R.id.transactionStatus,new DebitFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;

                case 2:
                    supportManager.beginTransaction().replace(R.id.transactionStatus,new AllFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            System.out.println("Here");
        }
    };

}
