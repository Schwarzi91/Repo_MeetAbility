package com.example.philipp.meetability.Database;

import android.content.Context;
import android.provider.Telephony;
import android.util.Log;

import com.example.philipp.meetability.Aktivitys.LoginActivity;

import java.lang.reflect.Array;
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
            if (getUserList().isEmpty())
            {
                saveUser(new User("muster@thi.de", "test123", "Max Muster", 2,"20-06-1992" , "Ich bin Dumm"));
                saveUser(new User("edgar@thi.de", "test123", "Ede Muster", 2, "01-01-1995", "Ich bins nicht"));
            }
            if(getAktivityList().isEmpty()){
                saveActivity(new Aktivity(getUserList().get(0), "Kino", 2,"Movieplex", "20-06-2015 18:35", "27-06-2015 17:40", "Heute ins Kino Gehen", 10, false));
                saveActivity(new Aktivity(getUserList().get(1), "Kino", 2,"Cinestar", "20-06-2015 18:35", "27-06-2015 18:40", "Heute Fischen Gehen", 5, false));
                saveActivity(new Aktivity(getUserList().get(1), "Kino", 2,"Cinestar", "20-06-2015 18:35", "27-06-2015 18:40", "Heute Fischen Gehen", 5, false));
                saveActivity(new Aktivity(getUserList().get(1), "Fischen", 2,"Donau", "19-06-2015 18:35", "20-06-2015 18:40", "Heute Fischen Gehen", 5, false));
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
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public Date onlyDateFormatter(String date)
    {
        format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateFormatted = format.parse(date);
            return dateFormatted;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Date dateFormatter(String date)
    {
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date dateFormatted = format.parse(date);
            return dateFormatted;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<Participant> getParticipantByLoggedUser ()
    {
        List<Participant> listParticipant = Storage.getStorageInstance().getParticipantList();
        List<Participant> listParticipantByUser = new ArrayList<Participant>();

        if(listParticipant.size()>0)
        {
            for (int x = 0; x < listParticipant.size(); x++)
            {
                if (listParticipant.get(x).getUserId().getUser_id() == LoginActivity.usercheckItem.getUser_id())
                {
                    listParticipantByUser.add(listParticipant.get(x));
                }
            }
            if(listParticipantByUser.size() > 0)
            {
                return listParticipantByUser;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }

    }

    public List<Aktivity> getActivtiesByLoggedUser ()
    {
        List<Participant> listParticipantByUser = Storage.getStorageInstance().getParticipantByLoggedUser();
        List<Aktivity> listAktivity = Storage.getStorageInstance().getAktivityList();
        List<Aktivity> listAktivitiesByUser = new ArrayList<>();

        if(listAktivity.size() > 0 && listParticipantByUser !=null)
        {
            for (int x = 0; x < listParticipantByUser.size(); x++)
            {
                for (int y = 0; y < listAktivity.size(); y++)
                {
                    if (listParticipantByUser.get(x).getUserId().getUser_id() == listAktivity.get(y).getUserId().getUser_id())
                    {
                        listAktivitiesByUser.add(listAktivity.get(y));
                    }
                }
            }
            if (listAktivitiesByUser.size() > 0)
            {
                return listAktivitiesByUser;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public List<History> listFilteredHistoryByUser ()
    {
        List<History> listHistory = Storage.getStorageInstance().getHistoryList();
        List<Aktivity> listAktivitiesByUser = Storage.getStorageInstance().getActivtiesByLoggedUser();
        List<History> listFilteredHistoryByUser = new ArrayList<>();
        if(listAktivitiesByUser!=null) {
            if (listAktivitiesByUser.size() > 0) {
                for (int x = 0; x < listHistory.size(); x++) {
                    for (int y = 0; y < listAktivitiesByUser.size(); y++) {

                        if (listHistory.get(x).getAktivityId().getAktivityId() == listAktivitiesByUser.get(y).getAktivityId()) {
                            listFilteredHistoryByUser.add(listHistory.get(x));
                        }
                    }
                }
                if (listFilteredHistoryByUser.size() > 0) {
                    return listFilteredHistoryByUser;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }else
            {
                return null;
         }

    }

    public List<Aktivity> listAktivitiesForHistorysById ()
    {
        List<History> listHistory = Storage.getStorageInstance().listFilteredHistoryByUser();
        List<Aktivity> listAktivitiesByUser = Storage.getStorageInstance().getActivtiesByLoggedUser();
        List<Aktivity> listAktivitiesForHistorysById = new ArrayList<>();
        if(listHistory!=null){
            if (listHistory.size() > 0)
            {
                for (int x = 0; x < listHistory.size(); x++)
                {
                    for (int y = 0; y <listAktivitiesByUser.size(); y++)
                    {
                        if (listHistory.get(x).getAktivityId().getAktivityId() == listAktivitiesByUser.get(y).getAktivityId())
                        {
                            listAktivitiesForHistorysById.add(listAktivitiesByUser.get(x));
                        }
                    }
                }
                if (listAktivitiesForHistorysById.size() > 0)
                {
                    return listAktivitiesForHistorysById;
                }
                else
                {
                    return null;
                }
        }
        else
        {
            return null;
        }}
        else
        {
            return null;
        }
    }
/*
    public List<Aktivity> getCurrentActivitiesByLoggedUser()
    {
        List<Aktivity> listActivitiesByLoggedUser = new ArrayList<>();
        List<Aktivity> listCurrentActivities = new ArrayList<>();

        listActivitiesByLoggedUser = getActivtiesByLoggedUser();

        for(int i = 0; i < listActivitiesByLoggedUser.size(); i++)
        {
            if()
            {
                listCurrentActivities.add(listActivitiesByLoggedUser.get(i));
            }
        }

        return listCurrentActivities;
    }
    */
}



