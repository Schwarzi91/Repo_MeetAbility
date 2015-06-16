package Database;

import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Kisman on 02.06.2015.
 */
public class DatabaseHelper {

    public Dao<Aktivity, ?> aktivityDao;
    public Dao<Block, ?> blockDao;
    public Dao<History, ?> historyDao;
    public Dao<Participant, ?> participantDao;
    public Dao<Report, ?> reportDao;
    public Dao<User, ?> userDao;
    private ConnectionSource connectionSource;
    private SQLiteOpenHelper helper;



    {
        if (connectionSource == null) {
            try {

                connectionSource = new AndroidConnectionSource(helper);
                aktivityDao = DaoManager.createDao(connectionSource, Aktivity.class);
                blockDao = DaoManager.createDao(connectionSource, Block.class);
                historyDao = DaoManager.createDao(connectionSource, History.class);
                participantDao = DaoManager.createDao(connectionSource, Participant.class);
                reportDao = DaoManager.createDao(connectionSource, Report.class);
                userDao = DaoManager.createDao(connectionSource, User.class);


            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                TableUtils.createTableIfNotExists(connectionSource, Aktivity.class);
                TableUtils.createTableIfNotExists(connectionSource, Block.class);
                TableUtils.createTableIfNotExists(connectionSource, History.class);
                TableUtils.createTableIfNotExists(connectionSource, Participant.class);
                TableUtils.createTableIfNotExists(connectionSource, Report.class);
                TableUtils.createTableIfNotExists(connectionSource, User.class);

            } catch (SQLException e) {
                System.out.print(e);
            }
        }
    }
}
