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
public class Ejercicio05 {

    public static void main(String[] args) {

        try {

            //Fichero que vamos a leer
            File fichero = new File("Contactos.xml");

            //Creamos el lector DOM
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            //Leemos el fichero
            Document documento = constructor.parse(fichero);

            documento.getDocumentElement().normalize();

            //Buscamos todos los nodos contacto
            NodeList contactos = documento.getElementsByTagName("contacto");

            //Recorremos los contactos
            for (int i = 0; i < contactos.getLength(); i++) {

                Node nodo = contactos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element contacto = (Element) nodo;

                    String nombre = contacto
                            .getElementsByTagName("nombre")
                            .item(0)
                            .getTextContent();

                    String apellidos = contacto
                            .getElementsByTagName("apellidos")
                            .item(0)
                            .getTextContent();

                    String email = contacto
                            .getElementsByTagName("email")
                            .item(0)
                            .getTextContent();

                    String telefono = contacto
                            .getElementsByTagName("telefono")
                            .item(0)
                            .getTextContent();

                    System.out.println("Nombre: " + nombre);
                    System.out.println("Apellidos: " + apellidos);
                    System.out.println("Email: " + email);
                    System.out.println("Telefono: " + telefono);
                    System.out.println("------------------------");
                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
