package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.model.Allat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private void orokbefogadokLap(){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/orokbefogadok.fxml");
        OrokbefogadokController controller = fxmlLoader.getController();
    }

    @FXML
    private void allatokLap(){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/allatok.fxml");
        AllatokController controller = fxmlLoader.getController();
    }

    @FXML
    private void orokbefogadasokLap(){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/orokbefogadasok.fxml");
        OrokbefogadasokController controller = fxmlLoader.getController();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void addAllat(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/save_allatok.fxml"));
        SaveAllatokController controller = fxmlLoader.getController();
        controller.setAllat(new Allat());
    }

    @FXML
    public void onExit(){
        Platform.exit();
    }
}
