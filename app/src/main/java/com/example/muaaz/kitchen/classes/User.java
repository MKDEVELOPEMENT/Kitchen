package com.example.muaaz.kitchen.classes;

/**
 * Created by muaaz on 2017/08/23.
 */

public class User {
    public String email;
    public String name;
    public String surname;
    public String phoneNo;
    public String password;

    public User(){}

    public User(String email, String name, String surname,String phoneNo, String password){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.password = password;
    }

}
