package com.example.philipp.meetability.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */
@DatabaseTable(tableName = "participant")
public class Participant {

    @DatabaseField (generatedId  = true)
    private int participant_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private User user;

    @DatabaseField (foreign = true, canBeNull = false)
    private Aktivity aktivity;

    @DatabaseField (canBeNull = false)
    private boolean creator;


    //Konstruktors

    public Participant(){}

    public Participant(User user_id, Aktivity aktivity_id, boolean creator){
        super();
        this.user=user_id;
        this.aktivity=aktivity_id;
        this.creator=creator;

    }

    //Getter

    public int getParticipantId() {return participant_id;}
    public User getUser() {return user;}
    public Aktivity getAktivity() {return aktivity;}
    public boolean isCreator() {return creator;}

    //Setter

    public void setUserId(User user_id) {this.user = user_id;}
    public void setAktivityId(Aktivity aktivity_id) {this.aktivity = aktivity_id;}
    public void setCreator(boolean creator) {this.creator = creator;}
}
