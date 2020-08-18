package com.example.finalexam;

public class User {


    private String id;
    private String email;
    private String pass;
    private String fullname;
    private String username;
    private String usertype;
    private String marketingsector;
    private String date;
    private String image;





    public User() {
    }

    public User(String id, String email, String pass, String fullname, String username, String usertype, String marketingsector, String date, String image) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.fullname = fullname;
        this.username = username;
        this.usertype = usertype;
        this.marketingsector= marketingsector;
        this.date = date;
        this.image = image;
    }

    public User(String email, String pass, String fullname, String username, String usertype, String marketingsector, String date, String image) {
        this.email = email;
        this.pass = pass;
        this.fullname = fullname;
        this.username = username;
        this.usertype = usertype;
        this.marketingsector= marketingsector;
        this.date = date;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype =usertype;
    }

    public String getMarketingsector() {
        return marketingsector;
    }

    public void setmarketingsector(String marketingsector) {
        this.marketingsector = marketingsector;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dob) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
