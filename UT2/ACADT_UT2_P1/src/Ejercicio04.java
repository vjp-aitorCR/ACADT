/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author alumno
 */
public class Ejercicio04 {

    public static void main(String[] args) {

        try {

            //Indicamos el fichero XML
            File fichero = new File("Contactos.xml");

            //Creamos el lector DOM
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            //Leemos el XML
            Document documento = constructor.parse(fichero);

            //Comprobamos que se ha leído
            documento.getDocumentElement().normalize();

            System.out.println("Fichero XML leido correctamente.");
            System.out.println("Elemento principal: " + documento.getDocumentElement().getNodeName());

        } catch (IOException | ParserConfigurationException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
