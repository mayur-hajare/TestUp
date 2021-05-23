package com.myur.testup;

public class Member2 {
    String image;
    String title;
    String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Member2(String image, String title, String des) {
        this.image = image;
        this.title = title;
        this.des = des;
    }

    public Member2(){}

    public Member2(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
