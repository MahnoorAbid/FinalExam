package com.example.finalexam;

public class Data {
    private String id;
    private String image;
    private String name;
    private String type;
    private String quantity;
    private String discount;
    private String price;
    private String location;

    public Data(){

    }


    public Data(String id, String name, String type, String quantity, String price, String discount, String location, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.location = location;
        this.image = image;
    }

    public Data( String name,String type, String quantity, String price, String discount, String location, String image) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.location = location;
        this.image = image;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String pass) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price= price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String usertype) {
        this.location =location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


