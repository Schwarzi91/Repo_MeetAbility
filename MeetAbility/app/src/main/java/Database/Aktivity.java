package de.clearconsulting.meetability.Applikation.Database.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Kisman on 02.06.2015.
 */

@DatabaseTable(tableName = "Aktivity")
public class Aktivity {

    @DatabaseField (generatedId = true)
    private int aktivity_id;

    @DatabaseField (canBeNull = false, foreign = true)
    private User user_id;

    @DatabaseField (canBeNull = false)
    private String objekt;

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

    public Aktivity(){}

    public Aktivity(User user_id, String objekt, String sex, Time start_time, Time end_time, Date start_date, String description, int max_participants){
        super();
        this.user_id=user_id;
        this.objekt=objekt;
        this.sex=sex;
        this.start_time=start_time;
        this.end_time=end_time;
        this.start_date=start_date;
        this.description=description;
        this.max_participants=max_participants;
    }


}
