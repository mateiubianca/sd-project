package com.softwaredesign.assignment2.presentation.controller;

import com.softwaredesign.assignment2.JavaFxApplication;
import com.softwaredesign.assignment2.business.implementations.Functions;
import com.softwaredesign.assignment2.business.interfaces.UserServiceI;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.presentation.util.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

@Component
@FxmlView("login.fxml")
public class LoginController {

    @FXML
    public Button loginButton;

    @FXML
    public TextField mail;

    @FXML
    public TextField password;

    private UserServiceI userService;

    @Inject
    public LoginController(UserServiceI userService){
        this.userService = userService;
    }

    public void login() throws IOException {

        if(Functions.validateLoginInput(mail.getText(), password.getText())){
            UserDTO loginResult = userService.login(mail.getText(), password.getText());
            if(loginResult != null){
                if(loginResult.getRole().equals(Role.ADMIN)){
                    JavaFxApplication.changeScene(AdminController.class);
                }

            }

        } else{
            AlertBox.display("No input", "You forgot to write your mail/password");
        }


    }

}
