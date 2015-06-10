package de.clearconsulting.meetability.Applikation.Database.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */
@DatabaseTable(tableName = "Participant")
public class Participant {

    @DatabaseField (generatedId = true)
    private int participant_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private User user_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private Aktivity aktivity_id;

    @DatabaseField (canBeNull = false)
    private boolean creator;

    public Participant(){}

    public Participant(User user_id, Aktivity aktivity_id, boolean creator){
        super();
        this.user_id=user_id;
        this.aktivity_id=aktivity_id;
        this.creator=creator;

    }
}
