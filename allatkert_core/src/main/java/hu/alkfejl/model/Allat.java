package hu.alkfejl.model;

import javafx.beans.property.*;


public class Allat {

    // nev, fenykep, bemutatkozo szoveg,- nem kotelezo
    //faj, szuletesi ev - kotelezo

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty faj = new SimpleStringProperty(this, "faj");
    private StringProperty pic = new SimpleStringProperty(this, "pic");
    private StringProperty bemutat = new SimpleStringProperty(this, "bemutat");
    private StringProperty dateOfBirth = new SimpleStringProperty(this, "dateOfBirth");

    public String getPic() {
        return pic.get();
    }

    public StringProperty picProperty() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic.set(pic);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFaj() {
        return faj.get();
    }

    public StringProperty fajProperty() {
        return faj;
    }

    public void setFaj(String faj) {
        this.faj.set(faj);
    }

    public String getBemutat() {
        return bemutat.get();
    }

    public StringProperty bemutatProperty() {
        return bemutat;
    }

    public void setBemutat(String bemutat) {
        this.bemutat.set(bemutat);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }
}
