package com.softwaredesign.assignment2.presentation.controller;

import com.softwaredesign.assignment2.JavaFxApplication;
import com.softwaredesign.assignment2.business.interfaces.FunctionsI;
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
import java.util.prefs.Preferences;

@Component
@FxmlView("login.fxml")
public class LoginController {

    @FXML
    public Button loginButton;

    @FXML
    public TextField mail;

    @FXML
    public TextField password;

    private FunctionsI functions;
    private UserServiceI userService;

    @Inject
    public LoginController(FunctionsI functions, UserServiceI userService){
        this.functions = functions;
        this.userService = userService;
    }

    public void login() throws IOException {

        if(functions.validateLoginInput(mail.getText(), password.getText())){
            UserDTO loginResult = userService.login(mail.getText(), password.getText());
            if(loginResult != null){
                //save user in preferences
                Preferences userPreferences = Preferences.userRoot();
                userPreferences.put(loginResult.getRole().toString(), loginResult.getUsername());

                if(loginResult.getRole().equals(Role.ADMIN)){
                    JavaFxApplication.changeScene(AdminController.class);
                } else {
                    JavaFxApplication.changeScene(UserController.class);
                }

            }else{
                AlertBox.display("Error", "Invalid mail/password");
            }

        } else{
            AlertBox.display("No input", "You forgot to write your mail/password");
        }


    }

}
