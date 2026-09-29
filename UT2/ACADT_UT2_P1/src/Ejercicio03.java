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
public class Ejercicio03 {

    public static void main(String[] args) {

        try {

            //Creamos algunos libros
            Libro libro1 = new Libro(
                    "239-87-9964-088-4",
                    "Acceso a Datos",
                    "Alicia Ramos",
                    "Garceta");

            Libro libro2 = new Libro(
                    "978-84-1234-567-8",
                    "Java desde cero",
                    "Juan Garcia",
                    "Anaya");

            Libro libro3 = new Libro(
                    "978-84-5678-901-2",
                    "Programacion Java",
                    "Maria Perez",
                    "Ra-Ma");

            //Creamos el documento XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();

            Document libros = implementacion.createDocument(null, "libros", null);

            //Asignamos la version XML
            libros.setXmlVersion("1.0");


            //Creamos el libro 1
            Element libro = libros.createElement("libro");

            //El ISBN es un atributo
            libro.setAttribute("ISBN", libro1.getIsbn());

            //Lo añadimos como hijo de libros
            libros.getDocumentElement().appendChild(libro);

            //Creamos el nodo titulo
            Element titulo = libros.createElement("titulo");
            Text texto = libros.createTextNode(libro1.getTitulo());
            titulo.appendChild(texto);
            libro.appendChild(titulo);

            //Creamos el nodo autor
            Element autor = libros.createElement("autor");
            texto = libros.createTextNode(libro1.getAutor());
            autor.appendChild(texto);
            libro.appendChild(autor);

            //Creamos el nodo editorial
            Element editorial = libros.createElement("editorial");
            texto = libros.createTextNode(libro1.getEditorial());
            editorial.appendChild(texto);
            libro.appendChild(editorial);


            //Creamos el libro 2
            libro = libros.createElement("libro");

            //El ISBN es un atributo
            libro.setAttribute("ISBN", libro2.getIsbn());

            //Lo añadimos como hijo de libros
            libros.getDocumentElement().appendChild(libro);

            //Creamos el nodo titulo
            titulo = libros.createElement("titulo");
            texto = libros.createTextNode(libro2.getTitulo());
            titulo.appendChild(texto);
            libro.appendChild(titulo);

            //Creamos el nodo autor
            autor = libros.createElement("autor");
            texto = libros.createTextNode(libro2.getAutor());
            autor.appendChild(texto);
            libro.appendChild(autor);

            //Creamos el nodo editorial
            editorial = libros.createElement("editorial");
            texto = libros.createTextNode(libro2.getEditorial());
            editorial.appendChild(texto);
            libro.appendChild(editorial);


            //Creamos el libro 3
            libro = libros.createElement("libro");

            //El ISBN es un atributo
            libro.setAttribute("ISBN", libro3.getIsbn());

            //Lo añadimos como hijo de libros
            libros.getDocumentElement().appendChild(libro);

            //Creamos el nodo titulo
            titulo = libros.createElement("titulo");
            texto = libros.createTextNode(libro3.getTitulo());
            titulo.appendChild(texto);
            libro.appendChild(titulo);

            //Creamos el nodo autor
            autor = libros.createElement("autor");
            texto = libros.createTextNode(libro3.getAutor());
            autor.appendChild(texto);
            libro.appendChild(autor);

            //Creamos el nodo editorial
            editorial = libros.createElement("editorial");
            texto = libros.createTextNode(libro3.getEditorial());
            editorial.appendChild(texto);
            libro.appendChild(editorial);


            //Guardar el documento
            Source origen = new DOMSource(libros);
            Result resultado = new StreamResult(new File("Libros.xml"));

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
