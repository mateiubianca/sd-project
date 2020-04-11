package com.softwaredesign.assignment2.presentation.ui_model;

import javafx.beans.property.SimpleStringProperty;

public class UserFXML {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty username = new SimpleStringProperty("");
    private final SimpleStringProperty password = new SimpleStringProperty("");
    private final SimpleStringProperty wallet = new SimpleStringProperty("");

    public  UserFXML(){
        this(0, "", "", 0);
    }

    public  UserFXML(Integer id, String username, String password, Integer wallet){
        setId(Integer.toString(id));
        setUsername(username);
        setPassword(password);
        setWallet(Integer.toString(wallet));
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
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

    public String getWallet() {
        return wallet.get();
    }

    public SimpleStringProperty walletProperty() {
        return wallet;
    }

    public void setWallet(String id) {
        this.wallet.set(id);
    }

}
