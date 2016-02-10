/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebung6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author René
 */
public class Server {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, JAXBException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream("C:\\fileRecieved.xml");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16 * 1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        // create a factory that understands namespaces and validates the XML input
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        // read the XML file
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("C:\\fileRecieved.xml"));
        System.out.println("Check Step Validate");

        boolean validateCheck;
        validateCheck = validateXMLFile(doc);

        // Sending the response back to the client.
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        if (validateCheck) {
            oos.writeObject("ok");
            System.out.println("Message sent to the client is " + "'ok'");
            File xMLtoSerializeAndSave = new File("C:\\fileRecieved.xml");
            serializeXMLAndSaveToHardDisk(xMLtoSerializeAndSave);
        } else {
            oos.writeObject("rejected");
            System.out.println("Message sent to the client is " + "'rejected'");
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }

    private static boolean validateXMLFile(Document doc) throws SAXException {

        // create a SchemaFactory and a Schema
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File("C:\\professorSchema.xsd"));
        Schema schema;
        schema = schemaFactory.newSchema(schemaFile);

        // create a Validator object and validate the XML file
        Validator validator = schema.newValidator();
        try {
            validator.validate(new DOMSource(doc));
        } catch (IOException ex) {
            return false;
        } catch (SAXException ex) {
            ex.printStackTrace();
            return false;
        }
        System.out.println("XML file successfully validated.");
        return true;
    }

    private static void serializeXMLAndSaveToHardDisk(File xMLtoSerializeAndSave) throws JAXBException, FileNotFoundException, IOException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Professor.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        jaxbUnmarshaller.setEventHandler(
                new ValidationEventHandler() {
                    public boolean handleEvent(ValidationEvent event) {
                        throw new RuntimeException(event.getMessage(),
                                event.getLinkedException());
                    }
                });
        Professor entity = (Professor) jaxbUnmarshaller.unmarshal(xMLtoSerializeAndSave);

        System.out.println(entity.getWohnort());

        // Write to disk with FileOutputStream
        FileOutputStream f_out = new FileOutputStream("C:\\HTWEntity.data");

        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

        // Write object out to disk
        obj_out.writeObject(entity);
    }
}
