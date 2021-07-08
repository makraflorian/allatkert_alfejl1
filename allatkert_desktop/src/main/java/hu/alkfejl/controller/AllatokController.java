package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.AllatDAO;
import hu.alkfejl.dao.AllatDAOImpl;
import hu.alkfejl.model.Allat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class AllatokController implements Initializable {

    AllatDAO dao = new AllatDAOImpl();

    @FXML
    private TableView<Allat> allatTable;

    @FXML
    private TableColumn<Allat, String> nameCol;
    @FXML
    private TableColumn<Allat, String> fajCol;

    @FXML
    private TextArea bemutatField;

    @FXML
    private TextField dateField;

    @FXML
    private TableColumn<Allat, Void> actionsCol;

    @FXML
    private ImageView allat_pic;

    // osszes allat listazasa
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allatTable.getItems().setAll(dao.listAll());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fajCol.setCellValueFactory(new PropertyValueFactory<>("faj"));

//        action gombok hozzáadása
        actionsCol.setCellFactory(param -> new TableCell<>(){

            private final Button editBtn = new Button("Szerkesztés");
            private final Button deleteBtn = new Button("Törlés");

            {
                deleteBtn.setId("delete-button");
                deleteBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    deleteAllat(allat);
                    refreshTable();
                });

                editBtn.setId("edit-button");
                editBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    editAllat(allat);
                    //System.out.println("EDITED");
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if (b){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(10.0);
                    container.setAlignment(Pos.CENTER);
                    setGraphic(container);
                }
            }
        });
    }

    private void editAllat(Allat allat){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/save_allatok.fxml"));
        SaveAllatokController controller = fxmlLoader.getController();
        controller.setAllat(allat);
    }

    public void addAllat(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/save_allatok.fxml"));
        SaveAllatokController controller = fxmlLoader.getController();
        controller.setAllat(new Allat());
    }

    @FXML
    public void allat_click(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) //Checking clicks
        {
            // ha letezik valami a sorban(nemures)
            if (!allatTable.getSelectionModel().isEmpty()){
                // amelyik sorra kattoltunk annak az adatai jelennek meg jobb oldalt
                //ha alapbol nincs kep akkor nem placeholder, ha van akkor megjelenitjuk
                    File allat_file = new File("img_allat.jpg");
                    //bytestringbol visszakonvertaljuk file-ba azutan kepbe és megjelenites
                    byte[] decodedBytes = Base64.getDecoder().decode(allatTable.getSelectionModel().getSelectedItem().getPic());
                    FileUtils.writeByteArrayToFile(allat_file, decodedBytes);
                    Image allat_img = new Image(allat_file.toURI().toString());
                    allat_pic.setImage(allat_img);

                String szoveg = allatTable.getSelectionModel().getSelectedItem().getBemutat();
                bemutatField.setText(szoveg);

                dateField.setText(allatTable.getSelectionModel().getSelectedItem().getDateOfBirth());

            }
        }
    }

    // csak az orokbefogadott allatok listazasa
    @FXML
    private void sorted_Orokbefogadott_Allat(){

        allatTable.getItems().setAll(dao.orokbefogadottAllat());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fajCol.setCellValueFactory(new PropertyValueFactory<>("faj"));

        actionsCol.setCellFactory(param -> new TableCell<>(){

            private final Button editBtn = new Button("Szerkesztés");
            private final Button deleteBtn = new Button("Törlés");

            {
                deleteBtn.setId("delete-button");
                deleteBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    deleteAllat(allat);
                    allatTable.getItems().setAll(dao.orokbefogadottAllat());
                });

                editBtn.setId("edit-button");
                editBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    editAllat(allat);
                    //System.out.println("EDITED");
                    allatTable.getItems().setAll(dao.orokbefogadottAllat());
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if (b){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(10.0);
                    container.setAlignment(Pos.CENTER);
                    setGraphic(container);
                }
            }
        });
    }

    // csak a nem orokbefogadott allatok listazasa
    @FXML
    private void sorted_Nem_Orokbefogadott_Allat(){

        allatTable.getItems().setAll(dao.nemOrokbefogadottAllat());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fajCol.setCellValueFactory(new PropertyValueFactory<>("faj"));

        actionsCol.setCellFactory(param -> new TableCell<>(){

            private final Button editBtn = new Button("Szerkesztés");
            private final Button deleteBtn = new Button("Törlés");

            {
                deleteBtn.setId("delete-button");
                deleteBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    deleteAllat(allat);
                    allatTable.getItems().setAll(dao.nemOrokbefogadottAllat());
                });

                editBtn.setId("edit-button");
                editBtn.setOnAction(event -> {
                    Allat allat = getTableRow().getItem();
                    editAllat(allat);
                    //System.out.println("EDITED");
                    allatTable.getItems().setAll(dao.nemOrokbefogadottAllat());
                });
            }

            @Override
            protected void updateItem(Void s, boolean b) {
                super.updateItem(s, b);
                if (b){
                    setGraphic(null);
                }
                else{
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(10.0);
                    container.setAlignment(Pos.CENTER);
                    setGraphic(container);
                }
            }
        });
    }

    private void deleteAllat(Allat allat){
        ButtonType buttonTypeNo = new ButtonType("Nem", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeYes = new ButtonType("Igen", ButtonBar.ButtonData.YES);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod: " + allat.getName(), buttonTypeYes, buttonTypeNo);
        confirm.setTitle("Törlés");
        confirm.setHeaderText("Megerősítés");
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(buttonTypeYes)){
                dao.delete(allat);
            }
        });
    }

    @FXML
    private void refreshTable(){
        allatTable.getItems().setAll(dao.listAll());
    }

    @FXML
    private void vissza(){
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onExit(){
        Platform.exit();
    }
}
