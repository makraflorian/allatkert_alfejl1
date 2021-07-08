package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.OrokbefogadoDAO;
import hu.alkfejl.dao.OrokbefogadoDAOImpl;
import hu.alkfejl.model.Orokbefogado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class EditOrokbefogadoController{

    private Orokbefogado orokbefogado;
    private OrokbefogadoDAO dao = new OrokbefogadoDAOImpl();

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private Label nameErrors;
    @FXML
    private Label emailErrors;
    @FXML
    private Button saveBtn;

    public EditOrokbefogadoController() {
    }

    public void setOrokbefogado(Orokbefogado egyed) {

//        regex&validaciok
        saveBtn.disableProperty().bind(name.textProperty().isEmpty()
                .or(email.textProperty().isEmpty()).or(emailErrors.textProperty().isNotEmpty()));

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue.isEmpty()){
                nameErrors.setText("Név megadása kötelező");
            }
            else{
                nameErrors.setText("");
            }
        });

        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue.isEmpty()){
                emailErrors.setText("E-mail cím megadása kötelező");
            } else if(newValue != null && !newValue.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
                emailErrors.setText("Nem megfelelő e-mail formátum");
            }
            else{
                emailErrors.setText("");
            }
        });

//        mezok feltoltese
        this.orokbefogado = egyed;
        name.textProperty().bindBidirectional(orokbefogado.nameProperty());
        email.textProperty().bindBidirectional(orokbefogado.emailProperty());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/orokbefogadok.fxml");
    }
    @FXML
    public void  onSave(){
        orokbefogado = dao.edit(orokbefogado);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Információ");
        alert.setHeaderText("Siker!");
        alert.setContentText("Az örökbefogadó adatait sikeresen módosította.");
        alert.showAndWait();
        App.loadFXML("/fxml/orokbefogadok.fxml");
    }

}
