package com.example.philipp.meetability.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Kisman on 02.06.2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABAS_NAME="meetability.db";

    public Dao<Aktivity, ?> aktivityDao;
    public Dao<Block, ?> blockDao;
    public Dao<History, ?> historyDao;
    public Dao<Participant, ?> participantDao;
    public Dao<Report, ?> reportDao;
    public Dao<User, String> userDao;

 public DatabaseHelper(Context context){
     super(context, DATABAS_NAME,null,DATABASE_VERSION);
 }



    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
           TableUtils.createTable(connectionSource, Aktivity.class);
          //  TableUtils.createTable(connectionSource, Block.class);
           // TableUtils.createTable(connectionSource, History.class);
           // TableUtils.createTable(connectionSource, Participant.class);
            //TableUtils.createTable(connectionSource, Report.class);
            TableUtils.createTable(connectionSource, User.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Cant create com.example.philipp.meetability.Database",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource source, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }
        for (int v = oldVersion; v < newVersion; v++) {
            upgradeOneStep(database, source, v);
        }
    }



    private void upgradeOneStep(SQLiteDatabase database, ConnectionSource source, int oldVersion) {
        try {
            if (oldVersion == 1) {
                //TODO: Datentabellen Implementieren die immer neu erstellt werden sollen
                TableUtils.dropTable(source, User.class, true);
                TableUtils.createTable(source, User.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.setVersion(oldVersion++);
    }

    public Dao<Aktivity, ?> getAktivityDao() throws SQLException {
        if(aktivityDao==null){
            aktivityDao=getDao(Aktivity.class);
        }
        return aktivityDao;
    }
    public Dao<Block, ?> getBlockDao() throws SQLException {
        if (blockDao == null) {
            blockDao = getDao(Block.class);
        }
        return blockDao;
    }
    public Dao<History, ?> getHistoryDao() throws SQLException {
        if(historyDao==null){
            historyDao=getDao(History.class);
        }
        return historyDao;
    }
    public Dao<Participant, ?> getParticipantDao() throws SQLException {
        if(participantDao==null){
            participantDao=getDao(Participant.class);
        }
        return participantDao;
    }
    public Dao<Report, ?> getReportDao() throws SQLException {
        if(reportDao==null){
            reportDao=getDao(Report.class);
        }
        return reportDao;
    }
    public Dao<User, String> getUserDao() throws SQLException {
        if(userDao==null){
            userDao=getDao(User.class);
        }
        return userDao;
    }


    //Dao Setter

    public void setUserDao(Dao<User, String> userDao) {
        this.userDao = userDao;
    }


    public void close(Dao dao) {
        super.close();
        dao = null;
    }
}


