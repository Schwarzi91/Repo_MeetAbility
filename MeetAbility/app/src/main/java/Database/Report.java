package de.clearconsulting.meetability.Applikation.Database.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */

@DatabaseTable(tableName = "Report")
public class Report {

    @DatabaseField (generatedId = true)
    private int report_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private User reported_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private User reporter_id;

    @DatabaseField (canBeNull = false)
    private String reason;

    @DatabaseField (canBeNull = true)
    private String description;

    public Report(){}

    public Report(User reported_id, User reporter_id, String reason, String description){
        super();
        this.reported_id=reported_id;
        this.reporter_id=reporter_id;
        this.reason=reason;
        this.description=description;

    }


}
