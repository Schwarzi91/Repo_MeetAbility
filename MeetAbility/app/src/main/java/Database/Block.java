

package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Time;

/**
 * Created by Kisman on 02.06.2015.
 */

@DatabaseTable(tableName = "Block")
public class Block {

    @DatabaseField(generatedId = true)
    private int block_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private User user_id;

    @DatabaseField(canBeNull = false)
    private int violations;

    @DatabaseField (canBeNull = true)
    private Time until;

    //Konstruktors

    public Block(){}

    public  Block(User user_id, int violations, Time until){
        super();
        this.user_id=user_id;
        this.violations=violations;
        this.until=until;
    }

    //Gettter

    public int getBlockId() {return block_id;}
    public User getUserId() {return user_id;}
    public int getViolations() {return violations;}
    public Time getUntil() {return until;}

    //Setter

    public void setUserId(User user_id) {this.user_id = user_id;}
    public void setUntil(Time until) {this.until = until;}
    public void setViolations(int violations) {this.violations = violations;}
}
