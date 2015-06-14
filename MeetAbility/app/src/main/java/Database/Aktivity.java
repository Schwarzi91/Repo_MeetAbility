package Database;


import android.os.Bundle;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Kisman on 02.06.2015.
 */


//Initiating Database

@DatabaseTable(tableName = "Aktivity")
public class Aktivity {

    @DatabaseField (generatedId = true)
    private int aktivity_id;

    @DatabaseField (canBeNull = false)
    private String aktivity_name;

    @DatabaseField (canBeNull = false, foreign = true)
    private User user_id;

    @DatabaseField (canBeNull = true)
    private String sex;

    @DatabaseField (canBeNull = false)
    private Time start_time;

    @DatabaseField (canBeNull = false)
    private Time end_time;

    @DatabaseField (canBeNull = false)
    private Date start_date;

    @DatabaseField (canBeNull = true)
    private String description;

    @DatabaseField (canBeNull = false)
    private int max_participants;


    //Konstruktors

    public Aktivity(){}

    public Aktivity(User user_id, String objekt, String sex, Time start_time, Time end_time, Date start_date, String description, int max_participants){
        super();
        this.user_id=user_id;
        this.aktivity_name=aktivity_name;
        this.sex=sex;
        this.start_time=start_time;
        this.end_time=end_time;
        this.start_date=start_date;
        this.description=description;
        this.max_participants=max_participants;
    }

    //Aktivity Getter

    public int getAktivityId(){return aktivity_id;}
    public String getAktivityName(){return aktivity_name;}
    public User getUserId(){return user_id;}
    public String getSex() {return sex;}
    public Time getStartTime() {return start_time;}
    public Time getEndTime() {return end_time;}
    public Date getStartDate() {return start_date;}
    public String getDescription() {return description;}
    public int getMaxParticipants() {return max_participants;}

    //Aktivity Setter



    public void setAktivityName(String aktivity_name) {this.aktivity_name = aktivity_name;}
    public void setUser_id(User user_id) {this.user_id = user_id;}
    public void setSex(String sex) {this.sex = sex;}
    public void setStartTime(Time start_time) {this.start_time = start_time;}
    public void setEndTime(Time end_time) {this.end_time = end_time;}
    public void setStartDate(Date start_date) {this.start_date = start_date;}
    public void setDescription(String description) {this.description = description;}
    public void setMaxParticipants(int max_participants) {this.max_participants = max_participants;}



}
