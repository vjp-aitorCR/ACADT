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
public class Ejercicio01 {

    public static void main(String[] args) {

        try {

            //Creamos el documento XML
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();

            //Creamos el nodo principal
            Element raiz = documento.createElement("contactos");
            documento.appendChild(raiz);

            //Creamos un contacto de prueba
            Element contacto = documento.createElement("contacto");
            raiz.appendChild(contacto);

            //Creamos los datos del contacto
            Element nombre = documento.createElement("nombre");
            nombre.setTextContent("Aitor");
            contacto.appendChild(nombre);

            Element apellidos = documento.createElement("apellidos");
            apellidos.setTextContent("Calle Rodriguez");
            contacto.appendChild(apellidos);

            Element email = documento.createElement("email");
            email.setTextContent("aitorcalle99@gmail.com");
            contacto.appendChild(email);

            Element telefono = documento.createElement("telefono");
            telefono.setTextContent("640699124");
            contacto.appendChild(telefono);

            //Preparamos la escritura del XML
            TransformerFactory fabricaTransformer = TransformerFactory.newInstance();
            Transformer transformer = fabricaTransformer.newTransformer();

            //Para que el XML quede ordenado
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //Guardamos el documento
            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File("Contactos.xml"));

            transformer.transform(fuente, resultado);

            System.out.println("Fichero Contactos.xml creado correctamente.");

        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
