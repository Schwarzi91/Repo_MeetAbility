package Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.File;

import Database.*;

/**
 * Created by Kisman on 02.06.2015.
 */


@DatabaseTable(tableName = "User")
public class User {


    @DatabaseField (generatedId = true)
    private int user_id;

    @DatabaseField (foreign = true, canBeNull = false)
    private History history_id;

    @DatabaseField (canBeNull = false)
    private String email;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField (canBeNull = false)
    private String username;

    @DatabaseField (canBeNull = false)
    private String sex;

    @DatabaseField (canBeNull = false)
    private int age;

    @DatabaseField (canBeNull = true)
    private File picture;

    @DatabaseField (canBeNull = true)
    private String description;

    public User(){}

    public User(History history_id, String email, String password, String username, String sex, int age, File picture, String description){
        super();
        this.history_id=history_id;
        this.email=email;
        this.password=password;
        this.username=username;
        this.sex=sex;
        this.age=age;
        this.picture=picture;
        this.description=description;

    }

}
