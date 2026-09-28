/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**
 *
 * @author alumno
 */

public class Ejercicio01 {

    public static void main(String[] args) {

        try {

            //Creamos el lector SAX
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser parser = fabrica.newSAXParser();

            //Leemos el fichero XML
            parser.parse(new File("empleados.xml"), new ManejadorSAX());

        } catch (IOException | ParserConfigurationException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}

//Clase que controla la lectura del XML
class ManejadorSAX extends DefaultHandler {

    //Cuando encontramos el inicio de una etiqueta
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atributos) {

        System.out.println("Inicio: " + qName);
    }

    //Cuando encontramos texto
    @Override
    public void characters(char[] ch, int inicio, int longitud) {

        String texto = new String(ch, inicio, longitud).trim();

        if (!texto.isEmpty()) {
            System.out.println("Texto: " + texto);
        }
    }

    //Cuando encontramos el final de una etiqueta
    @Override
    public void endElement(String uri, String localName, String qName) {

        System.out.println("Fin: " + qName);
    }
}