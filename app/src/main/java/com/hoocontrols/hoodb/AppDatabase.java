package com.hoocontrols.hoodb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

//import androidx.room.Database;
//import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao myDao();
}
