/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

/**
 *
 * @author alumno
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        try {

            //Creamos algunos contactos
            Contacto contacto1 = new Contacto(
                    "Aitor",
                    "Calle Rodriguez",
                    "aitorcalle99@gmail.com",
                    "640699124");

            Contacto contacto2 = new Contacto(
                    "Juan",
                    "Garcia Lopez",
                    "juan@gmail.com",
                    "611222333");

            Contacto contacto3 = new Contacto(
                    "Maria",
                    "Perez Sanchez",
                    "maria@gmail.com",
                    "622333444");

            //Creamos el documento XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();

            Document contactos = implementacion.createDocument(null, "contactos", null);

            //Asignamos la version XML
            contactos.setXmlVersion("1.0");

            //Creamos el contacto 1
            Element contacto = contactos.createElement("contacto");
            contactos.getDocumentElement().appendChild(contacto);

            //Creamos el nodo nombre
            Element nombre = contactos.createElement("nombre");
            Text texto = contactos.createTextNode(contacto1.getNombre());
            nombre.appendChild(texto);
            contacto.appendChild(nombre);

            //Creamos el nodo apellidos
            Element apellidos = contactos.createElement("apellidos");
            texto = contactos.createTextNode(contacto1.getApellidos());
            apellidos.appendChild(texto);
            contacto.appendChild(apellidos);

            //Creamos el nodo email
            Element email = contactos.createElement("email");
            texto = contactos.createTextNode(contacto1.getEmail());
            email.appendChild(texto);
            contacto.appendChild(email);

            //Creamos el nodo telefono
            Element telefono = contactos.createElement("telefono");
            texto = contactos.createTextNode(contacto1.getTelefono());
            telefono.appendChild(texto);
            contacto.appendChild(telefono);


            //Creamos el contacto 2
            contacto = contactos.createElement("contacto");
            contactos.getDocumentElement().appendChild(contacto);

            //Creamos el nodo nombre
            nombre = contactos.createElement("nombre");
            texto = contactos.createTextNode(contacto2.getNombre());
            nombre.appendChild(texto);
            contacto.appendChild(nombre);

            //Creamos el nodo apellidos
            apellidos = contactos.createElement("apellidos");
            texto = contactos.createTextNode(contacto2.getApellidos());
            apellidos.appendChild(texto);
            contacto.appendChild(apellidos);

            //Creamos el nodo email
            email = contactos.createElement("email");
            texto = contactos.createTextNode(contacto2.getEmail());
            email.appendChild(texto);
            contacto.appendChild(email);

            //Creamos el nodo telefono
            telefono = contactos.createElement("telefono");
            texto = contactos.createTextNode(contacto2.getTelefono());
            telefono.appendChild(texto);
            contacto.appendChild(telefono);


            //Creamos el contacto 3
            contacto = contactos.createElement("contacto");
            contactos.getDocumentElement().appendChild(contacto);

            //Creamos el nodo nombre
            nombre = contactos.createElement("nombre");
            texto = contactos.createTextNode(contacto3.getNombre());
            nombre.appendChild(texto);
            contacto.appendChild(nombre);

            //Creamos el nodo apellidos
            apellidos = contactos.createElement("apellidos");
            texto = contactos.createTextNode(contacto3.getApellidos());
            apellidos.appendChild(texto);
            contacto.appendChild(apellidos);

            //Creamos el nodo email
            email = contactos.createElement("email");
            texto = contactos.createTextNode(contacto3.getEmail());
            email.appendChild(texto);
            contacto.appendChild(email);

            //Creamos el nodo telefono
            telefono = contactos.createElement("telefono");
            texto = contactos.createTextNode(contacto3.getTelefono());
            telefono.appendChild(texto);
            contacto.appendChild(telefono);


            //Guardar el documento
            Source origen = new DOMSource(contactos);
            Result resultado = new StreamResult(new File("Contactos.xml"));

            Transformer transformador =
                    TransformerFactory.newInstance().newTransformer();

            //Para que aparezca formateado
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");

            transformador.transform(origen, resultado);

            //Mostrar el resultado por salida
            Result salidaEstandar = new StreamResult(System.out);
            transformador.transform(origen, salidaEstandar);

        } catch (IllegalArgumentException | ParserConfigurationException | DOMException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (TransformerConfigurationException ex) {

            System.out.println("Error: " + ex.getMessage());

        } catch (TransformerException ex) {

            System.out.println("Error: " + ex.getMessage());
        }
    }
}
