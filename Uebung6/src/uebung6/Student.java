package uebung6;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
public class Student implements Serializable {

    String vorname;
    String nachname;
    String matrikelnummer;
    String semester;
    String fachrichtung;
    String student;
    String strasse;
    String hausnummer;
    String postleitzahl;
    String stadt;

    @XmlElement(name = "Student")
    public void setStudent(String student) {
        this.student = student;
    }

    @XmlElement(name = "Vorname")
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @XmlElement(name = "Nachname")
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @XmlElement(name = "Matrikelnummer")
    public void setMatrikelnummer(String matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }

    @XmlElement(name = "Semester")
    public void setSemester(String semester) {
        this.semester = semester;
    }

    @XmlElement(name = "Fachrichtung")
    public void setFachrichtung(String fachrichtung) {
        this.fachrichtung = fachrichtung;
    }

    @XmlElement(name = "Strasse")
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    @XmlElement(name = "Hausnummer")
    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    @XmlElement(name = "Postleitzahl")
    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    @XmlElement(name = "Stadt")
    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    public String getSemester() {
        return semester;
    }

    public String getFachrichtung() {
        return fachrichtung;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getStadt() {
        return stadt;
    }
    
    public String getWohnort(){
        return strasse +  " " + hausnummer +  " " + postleitzahl +  " " + stadt;
    }
}
