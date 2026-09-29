/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import com.thoughtworks.xstream.XStream;
/**
 *
 * @author alumno
 */
public class Ejercicio03 {

    public static void main(String[] args) {

        try {

            // Creamos un objeto Libro
            Libro libro = new Libro(
                    "239-87-9964-088-4",
                    "Acceso a Datos",
                    "Alicia Ramos",
                    "Garceta");

            // Creamos XStream
            XStream xstream = new XStream();

            // Permitimos utilizar la clase Libro
            xstream.allowTypes(new Class[]{Libro.class});

            // SERIALIZACIÓN
            // Convertimos el objeto en XML
            String xml = xstream.toXML(libro);

            // Guardamos el XML en un fichero
            FileWriter escritor = new FileWriter("libro.xml");
            escritor.write(xml);
            escritor.close();

            System.out.println("Objeto serializado correctamente.");
            System.out.println("Fichero libro.xml creado.");

            // DESERIALIZACIÓN
            // Leemos el fichero XML
            FileReader lector = new FileReader(new File("libro.xml"));

            // Convertimos el XML en un objeto
            Libro libro2 = (Libro) xstream.fromXML(lector);

            lector.close();

            // Mostramos el objeto recuperado
            System.out.println();
            System.out.println("Objeto deserializado:");
            System.out.println("ISBN: " + libro2.getIsbn());
            System.out.println("Titulo: " + libro2.getTitulo());
            System.out.println("Autor: " + libro2.getAutor());
            System.out.println("Editorial: " + libro2.getEditorial());

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}