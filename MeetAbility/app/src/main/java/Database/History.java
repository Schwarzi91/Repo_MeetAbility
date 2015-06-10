package Database;;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

/**
 * Created by Kisman on 02.06.2015.
 */
@DatabaseTable(tableName = "History")
public class History {

    @DatabaseField (generatedId = true)
    private int history_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private Aktivity aktivity_id;

    @DatabaseField (canBeNull = false)
    private Timestamp ended;

    @DatabaseField (canBeNull = true)
    private int rating;

    @DatabaseField (canBeNull = true)
    private String description;

   public  History(){}

    public History(Aktivity aktivity_id, Timestamp ended, int rating, String description){
        super();
        this.aktivity_id=aktivity_id;
        this.ended=ended;
        this.rating=rating;
        this.description=description;
    }


}
