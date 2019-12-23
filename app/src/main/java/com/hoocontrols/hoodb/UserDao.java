package com.hoocontrols.hoodb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
//    @Query("SELECT * FROM user")
//    List<User> getAll();
//    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    User findByName(String firstName, String lastName);
//    @Query("SELECT COUNT(*) from user")
//    int countUsers();
//    @Insert
//    void insertAll(User... users);
//    @Delete
//    void delete(User user);

    @Insert
    public void addUser(User user);

    @Query("select * from user")
    public List<User> getUser();
}
