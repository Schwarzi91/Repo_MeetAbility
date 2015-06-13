package Database;

import android.media.Image;

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
    private Image picture;

    @DatabaseField (canBeNull = true)
    private String description;

    //Konstruktors

    public User(){}

    public User(History history_id, String email, String password, String username, String sex, int age, Image picture, String description){
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

    //Getter

    public int getUser_id() {return user_id;}
    public History getHistory_id() {return history_id;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getUsername() {return username;}
    public String getSex() {return sex;}
    public int getAge() {return age;}
    public Image getPicture() {return picture;}
    public String getDescription() {return description;}

    //Setter

    public void setHistory_id(History history_id) {this.history_id = history_id;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setUsername(String username) {this.username = username;}
    public void setSex(String sex) {this.sex = sex;}
    public void setAge(int age) {this.age = age;}
    public void setPicture(Image picture) {this.picture = picture;}
    public void setDescription(String description) {this.description = description;}
}