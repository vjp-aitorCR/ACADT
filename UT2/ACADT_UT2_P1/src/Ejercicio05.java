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

            //Creamos el DocumentBuilder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            //Leemos el Document desde el fichero
            Document contactos = builder.parse(new File("Contactos.xml"));

            //Normalizamos el documento
            contactos.getDocumentElement().normalize();

            //Mostramos el nombre del elemento raiz
            System.out.println("El elemento raiz es "
                    + contactos.getDocumentElement().getNodeName());

            //Creamos una lista de todos los nodos contacto
            NodeList listaContactos =
                    contactos.getElementsByTagName("contacto");

            //Mostrar el numero de contactos
            System.out.println("Se han encontrado "
                    + listaContactos.getLength() + " contactos");

            //Recorremos la lista
            for (int i = 0; i < listaContactos.getLength(); i++) {

                //Obtenemos el primer nodo de la lista
                Node cont = listaContactos.item(i);

                //En caso de que ese nodo sea un Elemento
                if (cont.getNodeType() == Node.ELEMENT_NODE) {

                    //Creamos el elemento contacto y leemos su información
                    Element contacto = (Element) cont;

                    System.out.print("Nombre: "
                            + contacto.getElementsByTagName("nombre")
                                    .item(0).getTextContent());

                    System.out.print("\tApellidos: "
                            + contacto.getElementsByTagName("apellidos")
                                    .item(0).getTextContent());

                    System.out.print("\tEmail: "
                            + contacto.getElementsByTagName("email")
                                    .item(0).getTextContent());

                    System.out.println("\tTelefono: "
                            + contacto.getElementsByTagName("telefono")
                                    .item(0).getTextContent());
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}