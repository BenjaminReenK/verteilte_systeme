package uebung6;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HTW")
public class HTW implements Serializable{

    String vorname;
    String nachname;
    String matrikelnummer;
    String semester;
    String fachrichtung;
    String wohnort;

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

    @XmlElement(name = "Wohnort")
    public void setWohnort(String wohnort) {
        this.wohnort = wohnort;
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

    public String getWohnort() {
        return wohnort;
    }
}
