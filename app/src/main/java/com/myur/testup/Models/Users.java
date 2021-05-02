package com.myur.testup.Models;

public class Users {

    String profile,mail,password,userid,phonenumber;

    public Users(String profile, String mail, String password, String userid, String phonenumber) {
        this.profile = profile;
        this.mail = mail;
        this.password = password;
        this.userid = userid;
        this.phonenumber = phonenumber;
    }

    public Users(){}
    //SignuP constructor
    public Users(String mail, String password, String phoneNumber) {

        this.mail = mail;
        this.password = password;
        this.phonenumber = phoneNumber;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
