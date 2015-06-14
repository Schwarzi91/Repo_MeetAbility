package Database;

import android.content.Context;

import com.example.philipp.meetability.InitializeAktivity;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kisman on 02.06.2015.
 */
public class Storage {
    private final DatabaseHelper dbHelper;
    private static Storage INSTANCE;

    //Creating Database
    public Storage(Context context) {dbHelper =new DatabaseHelper();}



    public static Storage getStorageInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(
                    "Storage must be initialized");
        }
        return INSTANCE;
    }

    public void createTestDataIfNeccessary() {
        if (getAktivityList().isEmpty()) {
            saveAktivity(new Aktivity());
        }
        if (getBlockList().isEmpty()) {
            saveBlock(new Block());
        }
        }





    //Data 2 Database Saver

    private void saveAktivity(Aktivity aktivity) {
        try {
            dbHelper.aktivityDao.createOrUpdate(aktivity);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    private void saveBlock(Block block) {
        try {
            dbHelper.blockDao.createOrUpdate(block);
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
            return dbHelper.aktivityDao.queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;}

    public List<Block> getBlockList() {
        try {
            return dbHelper.blockDao.queryForAll();
        } catch (SQLException e) {
            handleEx(e);
        }
        return Collections.EMPTY_LIST;}


//Dao Getter

    public User getUserByEmail(String user_email){
        try {
            List<User> listUser = dbHelper.userDao.queryForEq(
                    "email", user_email);
            if (listUser.size() == 1) {
                return listUser.get(0);
            } else {
                return new User();
            }
        } catch (SQLException e) {
            handleEx(e);
        }
        return null;
    }


    }

