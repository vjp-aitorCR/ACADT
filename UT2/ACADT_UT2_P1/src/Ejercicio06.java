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
public class Ejercicio06 {

    public static void main(String[] args) {

        try {

            //Fichero que vamos a leer
            File fichero = new File("Libros.xml");

            //Creamos el lector DOM
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            //Leemos el XML
            Document documento = constructor.parse(fichero);

            documento.getDocumentElement().normalize();

            //Buscamos todos los libros
            NodeList libros = documento.getElementsByTagName("libro");

            //Recorremos los libros
            for (int i = 0; i < libros.getLength(); i++) {

                Node nodo = libros.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element libro = (Element) nodo;

                    //Leemos el atributo ISBN
                    String isbn = libro.getAttribute("ISBN");

                    String titulo = libro
                            .getElementsByTagName("titulo")
                            .item(0)
                            .getTextContent();

                    String autor = libro
                            .getElementsByTagName("autor")
                            .item(0)
                            .getTextContent();

                    String editorial = libro
                            .getElementsByTagName("editorial")
                            .item(0)
                            .getTextContent();

                    System.out.println("ISBN: " + isbn);
                    System.out.println("Titulo: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Editorial: " + editorial);
                    System.out.println("------------------------");
                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
