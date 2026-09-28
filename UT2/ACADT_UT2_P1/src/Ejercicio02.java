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
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();

            //Creamos el nodo principal
            Element raiz = documento.createElement("contactos");
            documento.appendChild(raiz);

            //Añadimos los contactos
            anhadirContacto(documento, raiz, contacto1);
            anhadirContacto(documento, raiz, contacto2);
            anhadirContacto(documento, raiz, contacto3);

            //Guardamos el XML
            TransformerFactory fabricaTransformer = TransformerFactory.newInstance();
            Transformer transformer = fabricaTransformer.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File("Contactos.xml"));

            transformer.transform(fuente, resultado);

            System.out.println("Contactos.xml creado correctamente.");

        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    //Metodo para añadir un contacto al XML
    public static void anhadirContacto(Document documento, Element raiz, Contacto contacto) {

        Element nodoContacto = documento.createElement("contacto");
        raiz.appendChild(nodoContacto);

        Element nombre = documento.createElement("nombre");
        nombre.setTextContent(contacto.getNombre());
        nodoContacto.appendChild(nombre);

        Element apellidos = documento.createElement("apellidos");
        apellidos.setTextContent(contacto.getApellidos());
        nodoContacto.appendChild(apellidos);

        Element email = documento.createElement("email");
        email.setTextContent(contacto.getEmail());
        nodoContacto.appendChild(email);

        Element telefono = documento.createElement("telefono");
        telefono.setTextContent(contacto.getTelefono());
        nodoContacto.appendChild(telefono);
    }
}
