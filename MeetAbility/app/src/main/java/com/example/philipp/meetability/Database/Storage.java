package com.example.philipp.meetability.Database;

import android.content.Context;
import android.util.Log;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Kisman on 02.06.2015.
 */
public class Storage {
    private final DatabaseHelper dbHelper;
    private static Storage INSTANCE;
    private SimpleDateFormat format;
    //Creating com.example.philipp.meetability.Database
    public Storage(Context context) {dbHelper =new DatabaseHelper(context);}


    public static void initialize(Context context){
        INSTANCE = new Storage(context);
    }

    public static Storage getStorageInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException(
                    "Storage must be initialized!");
        }
        return INSTANCE;
    }

    public void createTestDataIfNeccessary() {
        try {
        if (getUserList().isEmpty()) {

                saveUser(new User("muster@thi.de", "test123", "Max Muster", 2, 20, "Ich bin Dumm"));
                saveUser(new User("edgar@thi.de", "test123", "Ede Muster", 2, 20, "Ich bins nicht"));
            }
            if(getAktivityList().isEmpty()){
                saveActivity(new Aktivity(getUserList().get(0), "Kino", 2, "20-06-2015 18:35", "27-06-2015 17:40", "Heute ins Kino Gehen", 10));
                saveActivity(new Aktivity(getUserList().get(1), "Fischen", 1, "25-06-2015 18:35", "27-06-2015 18:40", "Heute Fischen Gehen", 5));
            }
            if(getHistoryList().isEmpty()){
                saveHistory(new History(getAktivityList().get(0), 5, "War super"));
                saveHistory(new History(getAktivityList().get(1), 0, "War kacke"));
            }
            if(getParticipantList().isEmpty()){
                saveParticipant(new Participant(getUserList().get(0),getAktivityList().get(0),false));
                saveParticipant(new Participant(getUserList().get(1),getAktivityList().get(1),false));
            }

        }catch (Exception e){
            Log.e(DatabaseHelper.class.getName(), "Cant safe Data", e);
            throw new RuntimeException(e);

        }

        }




    //Data 2 com.example.philipp.meetability.Database Saver

    public void saveHistory(History history) {
        try {
            dbHelper.getHistoryDao().createOrUpdate(history);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    public void saveActivity(Aktivity aktivity) {
        try {
            dbHelper.getAktivityDao().createOrUpdate(aktivity);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    public void saveReport(Report report) {
        try {
            dbHelper.getReportDao().createOrUpdate(report);
        } catch (SQLException e) {
            handleEx(e);
        }
    }

    public void saveBlock(Block block) {
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

    public void saveParticipant(Participant participant) {
        try {
            dbHelper.getParticipantDao().createOrUpdate(participant);
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

    public List<Report> getReportList(){
        try {
            return dbHelper.getReportDao().queryForAll();
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

    public List<Participant> getParticipantList() {
        try {
            return dbHelper.getParticipantDao().queryForAll();
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
        }catch(SQLException e){
            handleEx(e);
        }
        return null;
    }

    public List<Aktivity> getAktivityByUserId(int user_id){
        List<Aktivity> listAktivity;
        try {
            listAktivity = dbHelper.getAktivityDao().queryForEq("user_id_id", user_id);
            if (listAktivity.size() == 1) {
                return listAktivity;

            } else {
                return null;
            }
        } catch (SQLException e) {
            handleEx(e);
        }
        return null;
    }

    public List<Aktivity> getFilteredAktivity(String activityName, int sex, String dateFrom, String dateTo)
    {
        List<Aktivity> listAktivity = Storage.getStorageInstance().getAktivityList();
        List<Aktivity> listResultAktivity = new ArrayList<>();
        Date dbStartDate;
        Date dbEndDate;
        Date searchStartDate;
        Date searchEndDate;

        if(listAktivity.size() > 0)
        {
            for (int x = 0; x < listAktivity.size(); x++)
            {
                dbStartDate = dateFormatter(listAktivity.get(x).getStartDate());
                dbEndDate = dateFormatter(listAktivity.get(x).getEndDate());
                searchStartDate = dateFormatter(dateFrom);
                searchEndDate = dateFormatter(dateTo);

                if (listAktivity.get(x).getAktivityName().equals(activityName)){
                        if( listAktivity.get(x).getSex() == sex) {
                            if(searchStartDate.getTime() <= dbEndDate.getTime()) {
                                if(searchEndDate.getTime() >= dbStartDate.getTime()) {
                                    {
                                        listResultAktivity.add(listAktivity.get(x));
                                    }
                                }}}}
            }
            if(listResultAktivity.size() > 0) {
                return listResultAktivity;
            }
            else{
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public Date dateFormatter(String date)
    {
        format = new SimpleDateFormat("dd-mm-yyyy HH:mm");
        try {
            Date dateFormatted = format.parse(date);
            return dateFormatted;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}

