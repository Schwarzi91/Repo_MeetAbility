package SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "MeetAbilityDB";
    private static final String TABLE_USER_DETAIL = "User_Detail";
    private static final String TABLE_BLOCK = "Block";
    private static final String TABLE_USER = "User";
    private static final String TABLE_PARTICIPANT = "Participant";
    private static final String TABLE_AKTIVITY = "Aktivity";
    private static final String TABLE_HISTORY = "History";
    private static final String TABLE_OBJECT = "Object";
    private static final String TABLE_CHAT = "Chat";

    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER_DETAIL + " (_id integer primary key autoincrement, " +
                    "TrunkStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BLOCK + " (_id integer primary key autoincrement, " +
                    "WindowStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PARTICIPANT + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_AKTIVITY + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_OBJECT + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHAT + " (_id integer primary key autoincrement, " +
                    "CarDoorStatus varchar(20))");

        } finally {
            if(db == null)
            {
                db.close();
            }
        }
    }

    public void insert(String strComponent, String strCommand)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        if(strComponent.equals(TABLE_USER_DETAIL))
        {
            value.put("TrunkStatus", strCommand);
            db.insert(TABLE_USER_DETAIL, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_BLOCK))
        {
            value.put("WindowStatus", strCommand);
            db.insert(TABLE_BLOCK, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_USER))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_USER, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_PARTICIPANT))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_PARTICIPANT, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_AKTIVITY))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_AKTIVITY, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_HISTORY))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_HISTORY, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_OBJECT))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_OBJECT, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }

        else if(strComponent.equals(TABLE_CHAT))
        {
            value.put("CarDoorStatus", strCommand);
            db.insert(TABLE_CHAT, null, value);
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub

    }

}
