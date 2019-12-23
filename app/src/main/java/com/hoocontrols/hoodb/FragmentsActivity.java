package com.hoocontrols.hoodb;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentManager;

public class FragmentsActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

//        fragmentManager = getSupportFragmentManager();
//        if(findViewById(R.id.fragment_container)!=null){
//            if(savedInstanceState!=null){
//                return;
//            }
//        }

//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        HomeFragment homeFragment = new HomeFragment();
//        fragmentTransaction.add(R.id.fragment_container, homeFragment, null );
//        fragmentTransaction.commit();

    }
}
