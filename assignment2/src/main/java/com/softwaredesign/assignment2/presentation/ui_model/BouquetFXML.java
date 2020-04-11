package com.softwaredesign.assignment2.presentation.ui_model;

import javafx.beans.property.SimpleStringProperty;

public class BouquetFXML {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty price = new SimpleStringProperty("");

    public  BouquetFXML(){
        this(0, "", 0);
    }

    public  BouquetFXML(int id, String name, int price){
        setId(Integer.toString(id));
        setName(name);
        setPrice(Integer.toString(price));
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



}
