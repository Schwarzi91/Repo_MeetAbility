package com.example.philipp.meetability.Database;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


/**
 * Created by Kisman on 02.06.2015.
 */


//Initiating com.example.philipp.meetability.Database

@DatabaseTable(tableName = "aktivity")
public class Aktivity{

    @DatabaseField (generatedId = true)
    private int aktivity_id;

    @DatabaseField (canBeNull = false)
    private String aktivity_name;

    @DatabaseField (canBeNull = false, foreign = true)
    private User user_id;

    @DatabaseField (canBeNull = true)
    private int allowed_sex;



    @DatabaseField (canBeNull = true)
    private String start_date;

    @DatabaseField (canBeNull = true)
    private String end_date;

    @DatabaseField (canBeNull = true)
    private String description;

    @DatabaseField (canBeNull = false)
    private int allowed_participants;


    //Konstruktors

    public Aktivity(){}

    public Aktivity(User user_id, String aktivity_name, int allowed_sex, String start_date, String end_date, String description, int allowed_participants){
        super();
        this.user_id=user_id;
        this.aktivity_name=aktivity_name;
        this.allowed_sex=allowed_sex;

        this.start_date=start_date;
        this.end_date=end_date;
        this.description=description;
        this.allowed_participants =allowed_participants;
    }

    //Aktivity Getter

    public int getAktivityId(){return aktivity_id;}
    public String getAktivityName(){return aktivity_name;}
    public User getUserId(){return user_id;}
    public int getSex() {return allowed_sex;}

    public String getStartDate() {return start_date;}
    public String getEndDate() {return end_date;}
    public String getDescription() {return description;}
    public int getMaxParticipants() {return allowed_participants;}

    //Aktivity Setter



    public void setAktivityName(String aktivity_name) {this.aktivity_name = aktivity_name;}
    public void setUser_id(User user_id) {this.user_id = user_id;}
    public void setSex(int sex) {this.allowed_sex = sex;}

    public void setStartDate(String start_date) {this.start_date = start_date;}
    public void setEndDate(String end_date) {this.end_date = end_date;}
    public void setDescription(String description) {this.description = description;}
    public void setMaxParticipants(int max_participants) {this.allowed_participants = max_participants;}



}
