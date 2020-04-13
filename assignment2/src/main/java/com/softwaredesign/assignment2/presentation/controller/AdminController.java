package com.softwaredesign.assignment2.presentation.controller;

import com.softwaredesign.assignment2.JavaFxApplication;
import com.softwaredesign.assignment2.business.implementations.Functions;
import com.softwaredesign.assignment2.business.interfaces.BouquetFlowerServiceI;
import com.softwaredesign.assignment2.business.interfaces.BouquetServiceI;
import com.softwaredesign.assignment2.business.interfaces.FlowerServiceI;
import com.softwaredesign.assignment2.business.interfaces.UserServiceI;
import com.softwaredesign.assignment2.dto.BouquetDTO;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;
import com.softwaredesign.assignment2.dto.FlowerDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.presentation.ui_model.BouquetFXML;
import com.softwaredesign.assignment2.presentation.ui_model.FlowerFXML;
import com.softwaredesign.assignment2.presentation.ui_model.UserFXML;
import com.softwaredesign.assignment2.presentation.util.AlertBox;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

//import com.softwaredesign.assignment2.business.interfaces.BouquetFlowerServiceI;

@Component
@FxmlView("admin.fxml")
public class AdminController {

    private int selectedUserId;
    private int selectedFlowerId;
    private int selectedBouquetId;

    //private BouquetDTO newBouquet;
    private HashMap<String, Integer> newBouquetFlowers = new HashMap<>();
    private HashMap<String, Integer> newBouquetFlowersIds = new HashMap<>();
    private ArrayList<BouquetFlowerDTO> bouquetFlowersSelected = new ArrayList<>();

    @FXML public TextField username;
    @FXML public TextField password;
    @FXML public TextField wallet;

    @FXML public Button saveUserButton;
    @FXML public Button updateUserButton;
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

    private UserServiceI userService;
    private FlowerServiceI flowerService;
    private BouquetServiceI bouquetService;
    private BouquetFlowerServiceI bouquetFlowerService;

    @Inject
    public AdminController(UserServiceI userService, FlowerServiceI flowerService, BouquetServiceI bouquetService, BouquetFlowerServiceI bouquetFlowerService){
        this.flowerService = flowerService;
        this.userService = userService;
        this.bouquetService = bouquetService;
        this.bouquetFlowerService = bouquetFlowerService;
    }

    @FXML
    private void initialize(){
        populateTables();
        loadFlowerList();

        reportComboBox.getItems().add("txt");
        reportComboBox.getItems().add("pdf");

        userTable.setRowFactory(new Callback<TableView<UserFXML>, TableRow<UserFXML>>() {
            @Override
            public TableRow<UserFXML> call(TableView<UserFXML> tableView) {
                final TableRow<UserFXML> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Delete");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteUser(Integer.parseInt(row.getItem().getId()));
                    }
                });
                final MenuItem editMenuItem = new MenuItem("Edit");
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        selectRow(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                contextMenu.getItems().add(editMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );

                return row ;
            }
        });

        flowerTable.setRowFactory(new Callback<TableView<FlowerFXML>, TableRow<FlowerFXML>>() {
            @Override
            public TableRow<FlowerFXML> call(TableView<FlowerFXML> tableView) {
                final TableRow<FlowerFXML> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Delete");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteFlower(Integer.parseInt(row.getItem().getId()));
                    }
                });
                final MenuItem editMenuItem = new MenuItem("Edit");
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        selectRow(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                contextMenu.getItems().add(editMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );

                return row ;
            }
        });

        bouquetTable.setRowFactory(new Callback<TableView<BouquetFXML>, TableRow<BouquetFXML>>() {
            @Override
            public TableRow<BouquetFXML> call(TableView<BouquetFXML> tableView) {
                final TableRow<BouquetFXML> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Delete");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteBouquet(Integer.parseInt(row.getItem().getId()));
                    }
                });
                final MenuItem editMenuItem = new MenuItem("Edit");
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        selectRow(row.getItem());
                    }
                });
                contextMenu.getItems().add(removeMenuItem);
                contextMenu.getItems().add(editMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );

                return row ;
            }
        });

        flowerBouquetTable.setRowFactory(new Callback<TableView<FlowerFXML>, TableRow<FlowerFXML>>() {
            @Override
            public TableRow<FlowerFXML> call(TableView<FlowerFXML> tableView) {
                final TableRow<FlowerFXML> row = new TableRow<>();
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem removeMenuItem = new MenuItem("Delete");
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteFlowerFromBouquet(Integer.parseInt(row.getItem().getId()), row.getItem().getName());
                    }
                });

                contextMenu.getItems().add(removeMenuItem);
                // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then((ContextMenu)null)
                                .otherwise(contextMenu)
                );

                return row ;
            }
        });

    }


    private void clearTextFieldsUser(){
        username.setText("");
        password.setText("");
        wallet.setText("");
    }

    private void clearTextFieldsFlower(){
        flowerName.setText("");
        flowerPrice.setText("");
    }

    private void clearTextFieldsBouquet(){
        bouquetName.setText("");
        flowerCount.setText("");
        flowerComboBox.setValue(null);
        flowerBouquetTable.getItems().clear();
    }

    private void selectRow(UserFXML rowData){
        username.setText(rowData.getUsername());
        password.setText(rowData.getPassword());
        wallet.setText(rowData.getWallet());
        selectedUserId = Integer.parseInt(rowData.getId());
        updateUserButton.setDisable(false);
    }

    private void selectRow(FlowerFXML rowData){
        flowerName.setText(rowData.getName());
        flowerPrice.setText(rowData.getPrice());
        selectedFlowerId = Integer.parseInt(rowData.getId());
        updateFlowerButton.setDisable(false);
    }


    private void selectRow(BouquetFXML rowData){
        bouquetName.setText(rowData.getName());
        selectedBouquetId = Integer.parseInt(rowData.getId());

        //resetBouquetFlowerMap();

        bouquetFlowersSelected = bouquetFlowerService.getBouquetFlowersByBouquetId(selectedBouquetId);

        populateTableFlowerBouquets();

        updateBouquetButton.setDisable(false);
    }


    private void populateTables(){
        populateTableUsers();
        populateTableFlowers();
        //populateTableBouquets();
    }

    private void populateTableFlowers(){
        flowerTable.getItems().clear();

        ArrayList<FlowerDTO> flowers = flowerService.getAllFlowers();
        ObservableList<FlowerFXML> dataFlower = flowerTable.getItems();

        flowers.forEach((f) -> {
            FlowerFXML flower = new FlowerFXML(f.getId(), f.getName(), f.getPrice());
            dataFlower.add(flower);
        });

        populateTableBouquets();
    }

    private void populateTableUsers(){
        userTable.getItems().clear();

        ArrayList<UserDTO> users = userService.getAllUsers();
        ObservableList<UserFXML> data = userTable.getItems();

        users.forEach((u) -> {
            UserFXML user = new UserFXML(u.getId(), u.getUsername(), u.getPassword(), u.getWallet());
            data.add(user);
        });
    }

    private void populateTableBouquets(){
        bouquetTable.getItems().clear();

        ArrayList<BouquetDTO> bouquets = bouquetService.getAllBouquets();
        ObservableList<BouquetFXML> dataBouquet = bouquetTable.getItems();

        bouquets.forEach((b) -> {
            if(b.getPrice() == 0){
                bouquetService.deleteBouquet(b.getId());
            }else {
                BouquetFXML bouquet = new BouquetFXML(b.getId(), b.getName(), b.getPrice());
                dataBouquet.add(bouquet);
            }
        });
        flowerBouquetTable.getItems().clear();
    }

    private void populateTableFlowerBouquets(){
        flowerBouquetTable.getItems().clear();

        ObservableList<FlowerFXML> dataFlowerBouquet = flowerBouquetTable.getItems();

        bouquetFlowersSelected.forEach(bf -> {
            //FlowerDTO flowerDTO = flowerService.getFlowerByName(key);
            FlowerFXML flower = new FlowerFXML(bf.getId(), bf.getFlower().getName(), bf.getFlower().getPrice(), bf.getQuantity());
            dataFlowerBouquet.add(flower);
        });
    }

    private void loadFlowerList(){
        flowerComboBox.getItems().clear();
        List<FlowerDTO> flowers = flowerService.getAllFlowers();

        flowers.forEach((f) -> {
           flowerComboBox.getItems().add(f.getName());
        });
    }

    public void saveNewUser(){
        if(Functions.validateNewUserInput(username.getText(), password.getText(), wallet.getText())){
            userService.createUser(username.getText(), password.getText(), Integer.parseInt(wallet.getText()));
            AlertBox.display("OK", "New User created");
            populateTableUsers();
            clearTextFieldsUser();
            updateUserButton.setDisable(true);
        } else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    public void updateUser(){
        if(Functions.validateNewUserInput(username.getText(), password.getText(), wallet.getText())){
            userService.updateUser(selectedUserId, username.getText(), password.getText(), Integer.parseInt(wallet.getText()));
            AlertBox.display("OK", "User updated");
            populateTableUsers();
            clearTextFieldsUser();
            updateUserButton.setDisable(true);
        } else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    private void deleteUser(int id){
        userService.deleteUser(id);
        populateTableUsers();
    }

    public void saveNewFlower(){
        if(Functions.validateNewFlowerInput(flowerName.getText(), flowerPrice.getText())){
            flowerService.createFlower(flowerName.getText(), Integer.parseInt(flowerPrice.getText()));
            AlertBox.display("OK", "Flower created");
            populateTableFlowers();
            loadFlowerList();
            clearTextFieldsFlower();
            updateFlowerButton.setDisable(true);
        }else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    public void updateFlower(){
        if(Functions.validateNewFlowerInput(flowerName.getText(), flowerPrice.getText())){
            flowerService.updateFlower(selectedFlowerId, flowerName.getText(), Integer.parseInt(flowerPrice.getText()));
            AlertBox.display("OK", "Flower updated");
            populateTableFlowers();
            loadFlowerList();
            clearTextFieldsFlower();
            updateFlowerButton.setDisable(true);
        }else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    private void deleteFlower(int id){
        flowerService.deleteFlower(id);
        populateTableFlowers();
        loadFlowerList();
    }

    public void generateReport(){

    }

    public void saveNewBouquet(){
        if(Functions.validateNewBouquetInput(bouquetName.getText(), bouquetFlowersSelected.size())){

            bouquetService.createBouquet(bouquetName.getText(), bouquetFlowersSelected);
            populateTableBouquets();
            clearTextFieldsBouquet();
            bouquetFlowersSelected = new ArrayList<>();
            updateBouquetButton.setDisable(true);
        }else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    public void updateNewBouquet(){
        if(Functions.validateNewBouquetInput(bouquetName.getText(), bouquetFlowersSelected.size())){

            bouquetService.updateBouquet(selectedBouquetId, bouquetName.getText(), bouquetFlowersSelected);
            populateTableBouquets();
            clearTextFieldsBouquet();
            bouquetFlowersSelected = new ArrayList<>();
            updateBouquetButton.setDisable(true);
        }else{
            AlertBox.display("Error", "Invalid data");
        }
    }

    private void deleteBouquet(int id){
        bouquetService.deleteBouquet(id);
        populateTableBouquets();
    }

    public void addFlowerToBouquet(){
        String flowerName = (String) flowerComboBox.getValue();
        if(Functions.validateNewFlowerToBouquetInput(flowerName, flowerCount.getText())){
            int no = Integer.parseInt(flowerCount.getText());
            AtomicBoolean found = new AtomicBoolean(false);

            bouquetFlowersSelected.forEach(bfs ->{
                if(bfs.getFlower().getName().equals(flowerName)){
                    if(bfs.getQuantity() + no > 0){
                        bfs.setQuantity(bfs.getQuantity() + no);
                    } else{
                        AlertBox.display("Error", "YInvalid quantity");
                    }
                   found.set(true);
                }
            });

            if(!found.get()){
                if(no > 0){

                    FlowerDTO flowerDTO = flowerService.getFlowerByName(flowerName);
                    BouquetFlowerDTO bouquetFlowerDTO = new BouquetFlowerDTO(flowerDTO, no);
                    bouquetFlowersSelected.add(bouquetFlowerDTO);

                    //newBouquetFlowers.put(flowerId, Integer.parseInt(flowerCount.getText()));
                }else {
                    AlertBox.display("Error", "You must choose a positive quantity");
                }
            }
            
            populateTableFlowerBouquets();
        } else {
            AlertBox.display("Error", "You must choose a flower and a quantity");
        }
    }

    private void deleteFlowerFromBouquet(int id, String name){
        bouquetFlowerService.deleteBouquetFlowersById(id);
        System.out.println(id);

        bouquetFlowersSelected = bouquetFlowerService.getBouquetFlowersByBouquetId(selectedBouquetId);

        populateTableFlowerBouquets();
    }


    public void logout(){
        JavaFxApplication.changeScene(LoginController.class);
    }

}
