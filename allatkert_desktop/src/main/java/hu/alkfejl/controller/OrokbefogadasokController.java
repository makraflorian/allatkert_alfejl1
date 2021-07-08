package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.OrokbefogadasDAO;
import hu.alkfejl.dao.OrokbefogadasDAOImpl;
import hu.alkfejl.model.Allat;
import hu.alkfejl.model.Orokbefogadas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OrokbefogadasokController implements Initializable {

    private OrokbefogadasDAO dao = new OrokbefogadasDAOImpl();
    private List<Orokbefogadas> lista;

    // táblázat
    @FXML
    private TableView<Orokbefogadas> orokbefogadasTable;

    //táblázat cols
    @FXML
    private TableColumn<Orokbefogadas, String> ember_name;
    @FXML
    private TableColumn<Orokbefogadas, String> allat_name;
    @FXML
    private TableColumn<Orokbefogadas, String> mikor;

    @FXML
    private TableColumn<Orokbefogadas, String> tipus;
    @FXML
    private TableColumn<Orokbefogadas, Integer> mennyiseg;
    @FXML
    private TableColumn<Orokbefogadas, String> gyakorisag;

    // szures fieldek
    @FXML
    private TextField emberSrc;
    @FXML
    private TextField allatSrc;
    @FXML
    private TextField yearSrc;

    @FXML
    private RadioButton all;
    @FXML
    private RadioButton listIfPenz;
    @FXML
    private RadioButton listIfEledel;

    @FXML
    public void onSrc(){ // örökbefogadó neve / állat szerinti keresés
        List<Orokbefogadas> filtered = lista.stream().filter(orokbefogadas -> orokbefogadas.getEmber_name().toLowerCase(Locale.ROOT).contains(emberSrc.getText().toLowerCase(Locale.ROOT)) && orokbefogadas.getAllat_name().toLowerCase(Locale.ROOT).contains(allatSrc.getText().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        orokbefogadasTable.getItems().setAll(filtered);
    }

//    tamogatas tipusa szerinti szures
    @FXML
    public void onYearSrc(){
        List<Orokbefogadas> filtered = lista.stream().filter(orokbefogadas -> orokbefogadas.getMikor().toString().startsWith(yearSrc.getText())).collect(Collectors.toList());
        orokbefogadasTable.getItems().setAll(filtered);
    }
    @FXML
    public void onTypePenz(){
        List<Orokbefogadas> filtered = lista.stream().filter(orokbefogadas -> orokbefogadas.getTipus().contains(listIfPenz.getText())).collect(Collectors.toList());
        orokbefogadasTable.getItems().setAll(filtered);
    }
    @FXML
    public void onTypeEledel(){
        List<Orokbefogadas> filtered = lista.stream().filter(orokbefogadas -> orokbefogadas.getTipus().contains(listIfEledel.getText())).collect(Collectors.toList());
        orokbefogadasTable.getItems().setAll(filtered);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        orokbefogadasTable.getItems().setAll(dao.listAll());

        ember_name.setCellValueFactory(new PropertyValueFactory<>("ember_name"));
        allat_name.setCellValueFactory(new PropertyValueFactory<>("allat_name"));
        mikor.setCellValueFactory(new PropertyValueFactory<>("mikor"));
        tipus.setCellValueFactory(new PropertyValueFactory<>("tipus"));
        mennyiseg.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));
        gyakorisag.setCellValueFactory(new PropertyValueFactory<>("gyakorisag"));

    }

    @FXML
    private void refreshTable(){
        lista = dao.listAll();
        orokbefogadasTable.getItems().setAll(lista);
    }

    public void addAllat(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/save_allatok.fxml"));
        SaveAllatokController controller = fxmlLoader.getController();
        controller.setAllat(new Allat());
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
