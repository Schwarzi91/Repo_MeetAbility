package com.example.philipp.meetability.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kisman on 02.06.2015.
 */


@DatabaseTable(tableName = "user")
public class User {


    @DatabaseField (generatedId = true)
    private int user_id;

    @DatabaseField (canBeNull = false)
    private String email;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField (canBeNull = true)
    private String username;

    @DatabaseField (canBeNull = true)
    private int sex;

    @DatabaseField (canBeNull = true)
    private int age;

    //@DatabaseField (canBeNull = true)
   // private Image picture;

    @DatabaseField (canBeNull = true)
    private String description;

    //Konstruktors

    public User(){}

    public User(String email, String password, String username, int sex, int age/*, Image picture*/, String description){
        super();
        this.email=email;
        this.password=password;
        this.username=username;
        this.sex=sex;
        this.age=age;
        //this.picture=picture;
        this.description=description;

    }

    //Getter

    public int getUser_id() {return user_id;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getUsername() {return username;}
    public int getSex() {return sex;}
    public int getAge() {return age;}
   // public Image getPicture() {return picture;}
    public String getDescription() {return description;}

    //Setter

  //  public void setHistory_id(History history_id) {this.history_id = history_id;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setUsername(String username) {this.username = username;}
    public void setSex(int sex) {this.sex = sex;}
    public void setAge(int age) {this.age = age;}
  //  public void setPicture(Image picture) {this.picture = picture;}
    public void setDescription(String description) {this.description = description;}
}
