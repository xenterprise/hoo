package com.hoocontrols.hoodb;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.Room;

import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase appDatabase;
    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdb")
                .allowMainThreadQueries()
                .build();

        textInfo = findViewById(R.id.textView);



    }

    public void save(View v){

        User user = new User();
        user.setFirstName("Mobeen");
        user.setLastName("Younis");
        user.setAge(24);

        appDatabase.myDao().addUser(user);
        Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();
    }

    public void read(View v){
        List<User> users = appDatabase.myDao().getUser();
        
        String info = "";
        for (User user : users) {
            int id = user.getUid();
            String fn = user.getFirstName();
            String ln = user.getLastName();
            int age = user.getAge();
            info = info + "\n\n" + "id: " + id + " Fn: " + fn + " Ln: " + ln + " Age: " + age;
        }

        textInfo.setText(info);
    }
    public void toas(View v){


        Toast.makeText(getApplicationContext(), "Switch Button Test", Toast.LENGTH_SHORT).show();
    }

}
