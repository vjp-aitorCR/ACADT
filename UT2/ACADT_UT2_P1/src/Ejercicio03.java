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
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();

            //Creamos el nodo principal
            Element raiz = documento.createElement("libros");
            documento.appendChild(raiz);

            //Añadimos los libros
            anhadirLibro(documento, raiz, libro1);
            anhadirLibro(documento, raiz, libro2);
            anhadirLibro(documento, raiz, libro3);

            //Guardamos el XML
            TransformerFactory fabricaTransformer = TransformerFactory.newInstance();
            Transformer transformer = fabricaTransformer.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File("Libros.xml"));

            transformer.transform(fuente, resultado);

            System.out.println("Libros.xml creado correctamente.");

        } catch (IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    //Metodo para añadir un libro al XML
    public static void anhadirLibro(Document documento, Element raiz, Libro libro) {

        Element nodoLibro = documento.createElement("libro");

        //El ISBN es un atributo
        nodoLibro.setAttribute("ISBN", libro.getIsbn());

        raiz.appendChild(nodoLibro);

        Element titulo = documento.createElement("titulo");
        titulo.setTextContent(libro.getTitulo());
        nodoLibro.appendChild(titulo);

        Element autor = documento.createElement("autor");
        autor.setTextContent(libro.getAutor());
        nodoLibro.appendChild(autor);

        Element editorial = documento.createElement("editorial");
        editorial.setTextContent(libro.getEditorial());
        nodoLibro.appendChild(editorial);
    }
}
