package com.softwaredesign.assignment2.presentation.ui_model;

import javafx.beans.property.SimpleStringProperty;

public class FlowerFXML {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty price = new SimpleStringProperty("");
    private final SimpleStringProperty count = new SimpleStringProperty("");

    public  FlowerFXML(){
        this(0, "", 0, 0);
    }

    public  FlowerFXML(Integer id, String name, Integer price){
        setId(Integer.toString(id));
        setName(name);
        setPrice(Integer.toString(price));
    }

    public  FlowerFXML(Integer id, String name, int price, Integer count){
        setId(Integer.toString(id));
        setName(name);
        setPrice(Integer.toString(price));
        setCount(Integer.toString(count));
    }

    public void setName(String username) {
        this.name.set(username);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getCount() {
        return count.get();
    }

    public SimpleStringProperty countProperty() {
        return count;
    }

    public void setCount(String id) {
        this.count.set(id);
    }

}
