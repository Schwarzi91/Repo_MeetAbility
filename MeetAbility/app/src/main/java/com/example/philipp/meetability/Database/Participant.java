package com.example.philipp.meetability.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */
@DatabaseTable(tableName = "participant")
public class Participant {

    @DatabaseField(generatedId = true)
    private int participant_id;

    @DatabaseField(canBeNull = false)
    private int user_id;

    @DatabaseField(canBeNull = false)
    private int aktivity_id;

    @DatabaseField(canBeNull = false)
    private boolean creator;

    @DatabaseField(canBeNull = true)
    private int rating;

    //Konstruktors

    public Participant() {
    }

    public Participant(int user_id, int aktivity_id, boolean creator, int rating) {
        super();
        this.user_id = user_id;
        this.aktivity_id = aktivity_id;
        this.creator = creator;
        this.rating = rating;

    }

    //Getter

    public int getParticipantId() {
        return participant_id;
    }

    public int getUserId() {
        return user_id;
    }

    public int getAktivityID() {
        return aktivity_id;
    }

    public boolean isCreator() {
        return creator;
    }

    public int getRating() {
        return rating;
    }

    //Setter

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setAktivityId(int aktivity_id) {
        this.aktivity_id = aktivity_id;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
