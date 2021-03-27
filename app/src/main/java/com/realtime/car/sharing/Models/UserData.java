package com.realtime.car.sharing.Models;

public class UserData {
    public String name, email, password1, Gender, mobile;
    public UserData(){

    }

    public UserData(String name, String email, String password1, String Gender, String mobile){
        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.Gender = Gender;
        this.mobile = mobile;

    }
}
