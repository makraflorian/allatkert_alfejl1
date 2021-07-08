package hu.alkfejl.controller;


import hu.alkfejl.App;
import hu.alkfejl.dao.AllatDAO;
import hu.alkfejl.dao.AllatDAOImpl;
import hu.alkfejl.model.Allat;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Base64;



public class SaveAllatokController {

    private Allat allat;
    private AllatDAO dao = new AllatDAOImpl();
//core data
    @FXML
    private TextField name;
    @FXML
    private TextField faj;
    @FXML
    private TextArea bemutat;
    @FXML
    private TextField dateOfBirth;
    @FXML
    private TextField piclist;
    @FXML
    private TextField picstr;
//cimsor
    @FXML
    private Label cimsor;

//errors

    @FXML
    private Label fajErrors;
    @FXML
    private Label dateOfBirthErrors;
    @FXML
    private Button saveBtn;

    public SaveAllatokController() {
    }



    public void setAllat(Allat allat){


        //        regex&validaciok
        saveBtn.disableProperty().bind((faj.textProperty().isEmpty()).or(dateOfBirth.textProperty().isEmpty()).or(dateOfBirthErrors.textProperty().isNotEmpty()));

        faj.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue.isEmpty()) {
                fajErrors.setText("A faj megadása kötelező");
            }
            else{
                fajErrors.setText("");
            }
        });
//^[12][0-9]{3}$
        dateOfBirth.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue.isEmpty()) {
                dateOfBirthErrors.setText("A születési év megadása kötelező");
            } else if(newValue != null && !newValue.matches("^[12][0-9]{3}$")){
                dateOfBirthErrors.setText("Nem megfelelő év formátum");
            }else{
                dateOfBirthErrors.setText("");
            }
        });

//        mezok feltoltese
        this.allat = allat;

        if (allat.getId() <= 0){
            cimsor.setText("Új állat felvitele");
        }
        else{
            cimsor.setText("Állat adatainak szerkesztése");
        }
try {
    if (allat.getPic() == null) {
        //ha nincs kep kivalasztva akkor egy placeholder kepet kodolunk le es toltunk fel a db-be
        //igy weben is megjelenik (máshogy, más útvonallal valamiért nem jó és nem találja a képet)
        File placeholder = new File("src/main/resources/img/animals.jpg");
        byte[] fileContent = FileUtils.readFileToByteArray(placeholder);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        allat.setPic(encodedString);
    }
}catch (java.io.IOException exception){
    exception.printStackTrace();
}
        name.textProperty().bindBidirectional(allat.nameProperty());
        faj.textProperty().bindBidirectional(allat.fajProperty());
        bemutat.textProperty().bindBidirectional(allat.bemutatProperty());
        picstr.textProperty().bindBidirectional(allat.picProperty());
        dateOfBirth.textProperty().bindBidirectional(allat.dateOfBirthProperty());

    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/allatok.fxml");
    }
    @FXML
    public void  onSave(){
        allat = dao.save(allat);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Információ");
        alert.setHeaderText("Siker!");
        alert.setContentText("Az állat adatait sikeresen elmentette.");
        alert.showAndWait();
        App.loadFXML("/fxml/allatok.fxml");
    }

    @FXML
    public void file_chooser() throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){

            byte[] fileContent = FileUtils.readFileToByteArray(selectedFile);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            allat.setPic(encodedString);

            piclist.setText(selectedFile.getName());
        }else{
            System.out.println("Visszavonva");
        }

    }
}
