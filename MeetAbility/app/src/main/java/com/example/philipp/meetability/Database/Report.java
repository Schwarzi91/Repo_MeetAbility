package com.example.philipp.meetability.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */

@DatabaseTable(tableName = "report")
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

    //Konstruktors

    public Report(){}

    public Report(User reported_id, User reporter_id, String reason, String description){
        super();
        this.reported_id=reported_id;
        this.reporter_id=reporter_id;
        this.reason=reason;
        this.description=description;

    }

    //Getter

    public int getReportId() {return report_id;}
    public User getReportedId() {return reported_id;}
    public User getReporterId() {return reporter_id;}
    public String getReason() {return reason;}
    public String getDescription() {return description;}

    //Setter

    public void setReportedId(User reported_id) {this.reported_id = reported_id;}
    public void setReporterId(User reporter_id) {this.reporter_id = reporter_id;}
    public void setReason(String reason) {this.reason = reason;}
    public void setDescription(String description) {this.description = description;}
}
