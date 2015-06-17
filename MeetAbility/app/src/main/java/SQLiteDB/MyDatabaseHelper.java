package SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String TABLE_REPORT = "Report";


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
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER_DETAIL + " (UserID integer primary key autoincrement, Username varchar(20), Sex boolean, Age date, Picture file, Descrition varchar(20),foreign key(UserID) references User(ID) )");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BLOCK + " (ID integer primary key , UserID varchar(20), Violations integer, Until date, foreign key(UserID ) references User(ID))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (ID integer primary key autoincrement, Email varchar(40), Passwort varchar(16), Acitv boolean)");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PARTICIPANT + " (ID integer primary key, UserId integer, Creator boolean, primary key(ID, UserID), foreign key(UserID) reference User(ID)");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_OBJECT + " (ID integer primary key autoincrement, Name varchar(20), Avaible boolean)");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHAT + " (ID integer primary key autoincrement, FileID varchar(40))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_AKTIVITY + " (ID integer primary key autoincrement, ParticipantID integer, ObjectID integer, ChatID integer, Location varchar(50), Sex boolean, Start_Time time, End_Time time, Start_Date date, Description varchar(250), Max_Participants integer, foreign key(ParticipantID ) references Participant(ID), foreign key(ObjectID) references Object(ID),foreign key(ChatID ) references Chat(ID))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + " (ID integer primary key autoincrement, AktivityID integer, Rating integer, foreign key(AktivityID) references Aktvity(ID))");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_REPORT + " (ID integer primary key autoincrement, UserID integer, Reason varchar(30), Description varchar(30))");

        } finally {
            if(db == null)
            {
                db.close();
            }
        }
    }

    public void insert(String strComponent, String strCommand)
    {
        SQLiteDatabase db = this.getWritableDatabase(); // holt sich Datenbankreferenz

        ContentValues value = new ContentValues();

        if(strComponent.equals(TABLE_USER_DETAIL))
        {
            value.put("TrunkStatus", strCommand); // erste ist Name von Spalte und zweite ist der Wert -> man kann auch ehrere Spalten ansprechen
            db.insert(TABLE_USER_DETAIL, null, value); // schreibt es auf Datenbank // erste ist Tabellenname // zweite bleibt null // dritte ist der Wert
            Log.i("Datenbank Table " + strComponent + "_", strCommand);
            db.close(); // datenbank schließen
            //db.rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
            //Cursor mCursor
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
