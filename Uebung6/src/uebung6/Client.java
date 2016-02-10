/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung6;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author René
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Socket socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 4444);

        //create the XML File
        createXMLFileStudent();
        // createXMLFileProfessor();

        File file = new File("C:\\file.xml");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        
            socket.shutdownOutput();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.skip(Long.MAX_VALUE);
        String confirmation = (String) ois.readObject();
        System.out.println("from server : " + confirmation);
        
        out.close();
        in.close();
        socket.close();
    }

    private static void createXMLFileStudent() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("HTW");
            doc.appendChild(rootElement);

            // eintrag element
            Element eintrag = doc.createElement("Student");
            rootElement.appendChild(eintrag);

            // matrikelnummer element
            Element matrikelnummer = doc.createElement("Matrikelnummer");
            matrikelnummer.appendChild(doc.createTextNode("s0539752"));
            eintrag.appendChild(matrikelnummer);

            // vornamen elements
            Element vorname = doc.createElement("Vorname");
            vorname.appendChild(doc.createTextNode("Rene"));
            eintrag.appendChild(vorname);

            // nachnamen elements
            Element nachname = doc.createElement("Nachname");
            nachname.appendChild(doc.createTextNode("Schumann"));
            eintrag.appendChild(nachname);

            Element wohnort = doc.createElement("Wohnort");
            eintrag.appendChild(wohnort);

            Element strasse = doc.createElement("Strasse");
            strasse.appendChild(doc.createTextNode("Glienicker Strasse"));
            wohnort.appendChild(strasse);

            Element hausnummer = doc.createElement("Hausnummer");
            hausnummer.appendChild(doc.createTextNode(" 15"));
            wohnort.appendChild(hausnummer);

            Element plz = doc.createElement("Postleitzahl");
            plz.appendChild(doc.createTextNode(" 12557"));
            wohnort.appendChild(plz);

            Element stadt = doc.createElement("Stadt");
            stadt.appendChild(doc.createTextNode(" Berlin"));
            wohnort.appendChild(stadt);

            // fachrichtung elements
            Element fachrichtung = doc.createElement("Fachrichtung");
            fachrichtung.appendChild(doc.createTextNode("Angewandte Informatik"));
            eintrag.appendChild(fachrichtung);

            // fachrichtung elements
            Element semester = doc.createElement("Semester");
            semester.appendChild(doc.createTextNode("105"));
            eintrag.appendChild(semester);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    private static void createXMLFileProfessor() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("HTW");
            doc.appendChild(rootElement);

            // eintrag element
            Element eintrag = doc.createElement("Professor");
            rootElement.appendChild(eintrag);

            // vornamen elements
            Element vorname = doc.createElement("Vorname");
            vorname.appendChild(doc.createTextNode("Rene"));
            eintrag.appendChild(vorname);

            // nachnamen elements
            Element nachname = doc.createElement("Nachname");
            nachname.appendChild(doc.createTextNode("Schumann"));
            eintrag.appendChild(nachname);

            // geburtsdatum element
            Element geburtsdatum = doc.createElement("Geburtsdatum");
            geburtsdatum.appendChild(doc.createTextNode("05.05.1980"));
            eintrag.appendChild(geburtsdatum);

            Element wohnort = doc.createElement("Wohnort");
            eintrag.appendChild(wohnort);

            Element strasse = doc.createElement("Strasse");
            strasse.appendChild(doc.createTextNode("Glienicker Strasse"));
            wohnort.appendChild(strasse);

            Element hausnummer = doc.createElement("Hausnummer");
            hausnummer.appendChild(doc.createTextNode("15"));
            wohnort.appendChild(hausnummer);

            Element plz = doc.createElement("Postleitzahl");
            plz.appendChild(doc.createTextNode("12557"));
            wohnort.appendChild(plz);

            Element stadt = doc.createElement("Stadt");
            stadt.appendChild(doc.createTextNode("Berlin"));
            wohnort.appendChild(stadt);

            // fachbereich elements
            Element fachbereich = doc.createElement("Fachbereich");
            fachbereich.appendChild(doc.createTextNode("Informatik"));
            eintrag.appendChild(fachbereich);

            // personalnummer elements
            Element personalnummer = doc.createElement("Personalnummer");
            personalnummer.appendChild(doc.createTextNode("123456"));
            eintrag.appendChild(personalnummer);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
