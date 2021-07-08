package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.OrokbefogadoDAO;
import hu.alkfejl.dao.OrokbefogadoDAOImpl;
import hu.alkfejl.model.Allat;
import hu.alkfejl.model.Orokbefogado;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrokbefogadokController implements Initializable {

    OrokbefogadoDAO dao = new OrokbefogadoDAOImpl();

    @FXML
    private TableView<Orokbefogado> orokbefogadoTable;

    @FXML
    private TableColumn<Orokbefogado, String> nameCol;
    @FXML
    private TableColumn<Orokbefogado, String> emailCol;
    @FXML
    private TableColumn<Orokbefogado, Void> actionsCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        orokbefogadoTable.getItems().setAll(dao.listAll());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

//        action buttons
        actionsCol.setCellFactory(param -> new TableCell<>(){

            private final Button editBtn = new Button("Szerkesztés");
            private final Button deleteBtn = new Button("Törlés");

            {
                deleteBtn.setId("delete-button");
                deleteBtn.setOnAction(event -> {
                    Orokbefogado egyed = getTableRow().getItem();
                    deleteOrokbefogado(egyed);
                    refreshTable();
                });

                editBtn.setId("edit-button");
                editBtn.setOnAction(event -> {
                    Orokbefogado egyed = getTableRow().getItem();
                    editOrokbefogado(egyed);
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

    private void editOrokbefogado(Orokbefogado egyed){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/edit_orokbefogado.fxml"));
        EditOrokbefogadoController controller = fxmlLoader.getController();
        controller.setOrokbefogado(egyed);
    }

    public void addAllat(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/save_allatok.fxml"));
        SaveAllatokController controller = fxmlLoader.getController();
        controller.setAllat(new Allat());
    }

    private void deleteOrokbefogado(Orokbefogado egyed){
        ButtonType buttonTypeNo = new ButtonType("Nem", ButtonBar.ButtonData.NO);
        ButtonType buttonTypeYes = new ButtonType("Igen", ButtonBar.ButtonData.YES);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod: " + egyed.getName(), buttonTypeYes, buttonTypeNo);
        confirm.setTitle("Törlés");
        confirm.setHeaderText("Megerősítés");
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(buttonTypeYes)){
                dao.delete(egyed);
            }
        });
    }

    private void refreshTable(){
        orokbefogadoTable.getItems().setAll(dao.listAll());
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
