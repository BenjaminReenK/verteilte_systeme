package uebung6;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Professor")
public class Professor implements Serializable {

    String vorname;
    String nachname;
    String personalnummer;
    String geburtsdatum;
    String fachbereich;
    String professor;
    String strasse;
    String hausnummer;
    String postleitzahl;
    String stadt;

    @XmlElement(name = "Professor")
    public void setStudent(String student) {
        this.professor = professor;
    }

    @XmlElement(name = "Vorname")
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @XmlElement(name = "Nachname")
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @XmlElement(name = "Personalnummer")
    public void setPersonalnummer(String personalnummer) {
        this.personalnummer = personalnummer;
    }

    @XmlElement(name = "Geburtsdatum")
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @XmlElement(name = "Fachbereich")
    public void setFachbereich(String fachrichtung) {
        this.fachbereich = fachbereich;
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

    public String getPersonalnummer() {
        return personalnummer;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getFachbereich() {
        return fachbereich;
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
