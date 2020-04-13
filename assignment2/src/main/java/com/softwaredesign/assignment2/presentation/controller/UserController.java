package com.softwaredesign.assignment2.presentation.controller;

import com.softwaredesign.assignment2.JavaFxApplication;
import com.softwaredesign.assignment2.business.error.InsufficientFundsException;
import com.softwaredesign.assignment2.business.interfaces.*;
import com.softwaredesign.assignment2.dto.BouquetDTO;
import com.softwaredesign.assignment2.dto.FlowerDTO;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.presentation.ui_model.BouquetFXML;
import com.softwaredesign.assignment2.presentation.ui_model.FlowerFXML;
import com.softwaredesign.assignment2.presentation.util.AlertBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.prefs.Preferences;

@Component
@FxmlView("user.fxml")
public class UserController {

    @FXML public TableView<FlowerFXML> flowerTable;
    @FXML public TableView<BouquetFXML> bouquetTable;

    @FXML public Label usernameLabel;
    @FXML public Label walletLabel;

    @FXML public Button logOutButton;
    @FXML public Button buyButton;

    @FXML public TextArea selectionTextArea;

    private UserDTO loggedInUser;
    private ItemDTO selectedItem;

    private UserServiceI userService;
    private FlowerServiceI flowerService;
    private BouquetServiceI bouquetService;
    private OrderServiceI orderService;
    private ItemServiceI itemService;
    private FunctionsI functions;

    @Inject
    public UserController(UserServiceI userService, FlowerServiceI flowerService, BouquetServiceI bouquetService, FunctionsI functions, OrderServiceI orderService, ItemServiceI itemService){
        this.flowerService = flowerService;
        this.userService = userService;
        this.bouquetService = bouquetService;
        this.orderService = orderService;
        this.itemService = itemService;
        this.functions = functions;
    }

    @FXML
    private void initialize(){
        populateTables();

        flowerTable.setRowFactory( tv -> {
            TableRow<FlowerFXML> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    FlowerFXML rowData = row.getItem();
                    selectRow(rowData);
                }
            });
            return row ;
        });

        bouquetTable.setRowFactory( tv -> {
            TableRow<BouquetFXML> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    BouquetFXML rowData = row.getItem();
                    selectRow(rowData);
                }
            });
            return row ;
        });


        Preferences userPreferences = Preferences.userRoot();
        String info = userPreferences.get(Role.USER.toString(), "");
        //System.out.println(info);

        loggedInUser = userService.getUserByUsername(info);

        walletLabel.setText(loggedInUser.getWallet().toString());
        usernameLabel.setText(loggedInUser.getUsername());
    }

    private void selectRow(FlowerFXML row){
        selectedItem = itemService.getItemTypeFlowerById(Integer.parseInt(row.getId()));
        selectionTextArea.setText("Selection:\n" + row.getName());
        buyButton.setDisable(false);
    }

    private void selectRow(BouquetFXML row){
        selectedItem = itemService.getItemTypeBouquetById(Integer.parseInt(row.getId()));
        selectionTextArea.setText("Selection:\n" + row.getName());
        buyButton.setDisable(false);
    }

    private void populateTables(){
        flowerTable.getItems().clear();

        ArrayList<FlowerDTO> flowers = flowerService.getAllFlowers();
        ObservableList<FlowerFXML> dataFlower = flowerTable.getItems();

        flowers.forEach((f) -> {
            FlowerFXML flower = new FlowerFXML(f.getId(), f.getName(), f.getPrice());
            dataFlower.add(flower);
        });

        bouquetTable.getItems().clear();

        ArrayList<BouquetDTO> bouquets = bouquetService.getAllBouquets();
        ObservableList<BouquetFXML> dataBouquet = bouquetTable.getItems();

        bouquets.forEach((b) -> {
            BouquetFXML bouquet = new BouquetFXML(b.getId(), b.getName(), b.getPrice());
            dataBouquet.add(bouquet);
        });
    }

    public void buySelection() {
        if(functions.validateOrderInput(loggedInUser, selectedItem)){
            try {
                orderService.createOrder(loggedInUser, selectedItem);

                selectionTextArea.setText("Selection:");
                selectedItem = new ItemDTO();
                loggedInUser = userService.getUserByUsername(loggedInUser.getUsername());
                walletLabel.setText(loggedInUser.getWallet().toString());
                buyButton.setDisable(true);

                AlertBox.display("Ok", "Order placed successfully");
            } catch (InsufficientFundsException e) {
                AlertBox.display("Error", e.getMessage());
            }
        } else {
            AlertBox.display("Error", "There was an error placing the order");
        }
    };

    public void logout(){
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.remove(Role.USER.toString());
        JavaFxApplication.changeScene(LoginController.class);
    }

}
