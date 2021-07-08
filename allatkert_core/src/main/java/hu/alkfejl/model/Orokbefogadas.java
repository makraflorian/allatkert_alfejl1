package hu.alkfejl.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Orokbefogadas {

    private IntegerProperty orokbefogado_id = new SimpleIntegerProperty(this, "orokbefogado_id");
    private IntegerProperty allat_id = new SimpleIntegerProperty(this, "allat_id");
    private ObjectProperty<LocalDate> mikor = new SimpleObjectProperty<>(this, "mikor");
    private StringProperty n_name = new SimpleStringProperty(this, "n_name");
    private StringProperty tipus = new SimpleStringProperty(this, "tipus");
    private IntegerProperty mennyiseg = new SimpleIntegerProperty(this, "mennyiseg");
    private StringProperty gyakorisag = new SimpleStringProperty(this, "gyakorisag");

    private StringProperty ember_name = new SimpleStringProperty(this, "ember_name");
    private StringProperty allat_name = new SimpleStringProperty(this, "allat_name");


    public int getOrokbefogado_id() {
        return orokbefogado_id.get();
    }

    public IntegerProperty orokbefogado_idProperty() {
        return orokbefogado_id;
    }

    public void setOrokbefogado_id(int orokbefogado_id) {
        this.orokbefogado_id.set(orokbefogado_id);
    }

    public int getAllat_id() {
        return allat_id.get();
    }

    public IntegerProperty allat_idProperty() {
        return allat_id;
    }

    public void setAllat_id(int allat_id) {
        this.allat_id.set(allat_id);
    }

    public LocalDate getMikor() {
        return mikor.get();
    }

    public ObjectProperty<LocalDate> mikorProperty() {
        return mikor;
    }

    public void setMikor(LocalDate mikor) {
        this.mikor.set(mikor);
    }

    public String getN_name() {
        return n_name.get();
    }

    public StringProperty n_nameProperty() {
        return n_name;
    }

    public void setN_name(String n_name) {
        this.n_name.set(n_name);
    }

    public String getTipus() {
        return tipus.get();
    }

    public StringProperty tipusProperty() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus.set(tipus);
    }

    public int getMennyiseg() {
        return mennyiseg.get();
    }

    public IntegerProperty mennyisegProperty() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg.set(mennyiseg);
    }

    public String getGyakorisag() {
        return gyakorisag.get();
    }

    public StringProperty gyakorisagProperty() {
        return gyakorisag;
    }

    public void setGyakorisag(String gyakorisag) {
        this.gyakorisag.set(gyakorisag);
    }

    public String getEmber_name() {
        return ember_name.get();
    }

    public StringProperty ember_nameProperty() {
        return ember_name;
    }

    public void setEmber_name(String ember_name) {
        this.ember_name.set(ember_name);
    }

    public String getAllat_name() {
        return allat_name.get();
    }

    public StringProperty allat_nameProperty() {
        return allat_name;
    }

    public void setAllat_name(String allat_name) {
        this.allat_name.set(allat_name);
    }
}
