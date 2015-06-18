package com.example.philipp.meetability.Database;

import android.content.Context;
import android.util.Log;

import com.example.philipp.meetability.Aktivitys.InitializeAktivity;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kisman on 02.06.2015.
 */
public class Storage {
    private final DatabaseHelper dbHelper;
    private static Storage INSTANCE;

    //Creating com.example.philipp.meetability.Database
    public Storage(Context context) {dbHelper =new DatabaseHelper(context);}


    public static void initialize(InitializeAktivity initializeAktivity){
        INSTANCE = new Storage(initializeAktivity);
    }

    public static Storage getStorageInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(
                    "Storage must be initialized!");
        }
        return INSTANCE;
    }

    public void createTestDataIfNeccessary() {

        if (getUserList().isEmpty()) {
            try {
                saveUser(new User("muster@thi.de", "test123", "Max Muster", 2, 20, "Ich bin Dumm"));
                saveUser(new User("edgar@thi.de", "test123", "Ede Muster", 2, 20, "Ich bins nicht"));
            }catch (Exception e){
                Log.e(DatabaseHelper.class.getName(), "Cant safe Data", e);
                throw new RuntimeException(e);
            }

        }

       /* if (getAktivityList().isEmpty()) {
            saveAktivity(new Aktivity(getUserList().get(0), "Kino", "Maennlich", new Time()., 1500, 13.05, "Film schauen",5));
        }*/


        }




    //Data 2 com.example.philipp.meetability.Database Saver
    private void saveHistory(History history) {
        try {
            dbHelper.getHistoryDao().createOrUpdate(history);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    private void saveAktivity(Aktivity aktivity) {
        try {
            dbHelper.getAktivityDao().createOrUpdate(aktivity);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    private void saveBlock(Block block) {
        try {
            dbHelper.getBlockDao().createOrUpdate(block);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    public void saveUser(User user) {
        try {
            dbHelper.getUserDao().createOrUpdate(user);
        } catch (SQLException e) {
            handleEx(e);
        }
    }



    //Exception Handler

    private void handleEx(SQLException e) {
        {
            e.printStackTrace();
        }

    }
    

    //List Generater

    public List<Aktivity> getAktivityList(){
        try {
            return dbHelper.getAktivityDao().queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;}

    public List<Block> getBlockList() {
        try {
            return dbHelper.getBlockDao().queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;}

    public List<User> getUserList() {
        try {
            return dbHelper.getUserDao().queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;
    }

    public List<History> getHistoryList() {
        try {
            return dbHelper.getHistoryDao().queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;
    }


//Dao Getter

    public User getUserByEmail(String user_email){
        List<User> listUser;
        try {
            listUser = dbHelper.getUserDao().queryForEq("email", user_email);
            if (listUser.size() == 1) {
                return listUser.get(0);

            } else {
                return null;
            }
        } catch (SQLException e) {
            handleEx(e);
        }
        return null;
    }



}

