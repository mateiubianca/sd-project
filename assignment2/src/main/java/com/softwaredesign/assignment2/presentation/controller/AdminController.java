package com.softwaredesign.assignment2.presentation.controller;

import com.softwaredesign.assignment2.JavaFxApplication;
import com.softwaredesign.assignment2.business.UserService;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.presentation.ui_model.BouquetFXML;
import com.softwaredesign.assignment2.presentation.ui_model.FlowerFXML;
import com.softwaredesign.assignment2.presentation.ui_model.UserFXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

@Component
@FxmlView("admin.fxml")
public class AdminController {

    @FXML public TextField username;
    @FXML public TextField password;
    @FXML public TextField wallet;

    @FXML public Button saveUserButton;
    @FXML public Button updateUserButton;
    @FXML public Button deleteUserButton;
    @FXML public Button logOutButton;

    @FXML private TableView<UserFXML> userTable;
    @FXML private TableView<FlowerFXML> flowerTable;
    @FXML private TableView<FlowerFXML> flowerBouquetTable;
    @FXML private TableView<BouquetFXML> bouquetTable;

    @FXML public TextField flowerPrice;
    @FXML public TextField flowerName;

    @FXML public Button deleteFlowerButton;
    @FXML public Button updateFlowerButton;
    @FXML public Button saveFlowerButton;
    @FXML public Button generateReportButton;

    @FXML public ComboBox reportComboBox;
    @FXML public ComboBox flowerComboBox;

    @FXML public TextField flowerCount;
    @FXML public TextField bouquetName;

    @FXML public Button saveBouquetButton;
    @FXML public Button updateBouquetButton;
    @FXML public Button deleteBouquetButton;
    @FXML public Button addFlowerToBouquetButton;

    private UserService userService;

    @Inject
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @FXML
    private void initialize(){

        ArrayList<UserDTO> users = userService.getAllUsers();
        ObservableList<UserFXML> data = userTable.getItems();

        System.out.println(users);

        users.forEach((u) -> {
            UserFXML user = new UserFXML(u.getId(), u.getUsername(), u.getPassword(), u.getWallet());
            data.add(user);
        });

    }

    public void saveNewUser(){

    }

    public void updateUser(){

    }

    public void deleteUser(){

    }

    public void saveNewFlower(){

    }

    public void updateFlower(){

    }

    public void deleteFlower(){

    }

    public void generateReport(){

    }

    public void saveNewBouquet(){

    }

    public void updateNewBouquet(){

    }

    public void deleteNewBouquet(){

    }

    public void addFlowerToBouquet(){

    }


    public void logout(){
        JavaFxApplication.changeScene(LoginController.class);
    }

}
