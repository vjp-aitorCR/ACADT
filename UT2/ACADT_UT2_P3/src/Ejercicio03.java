/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author alumno
 */
public class Ejercicio03 {

    public static void main(String[] args) throws IOException {

        System.out.println("Comienza el proceso de creacion del fichero XML ...");

        //Creamos XStream
        XStream xstream = new XStream(new DomDriver("UTF-8"));

        xstream.addPermission(AnyTypePermission.ANY);

        xstream.alias("DatosLibro", Libro.class);
        xstream.alias("Libros", ListaLibros.class);

        xstream.addImplicitCollection(ListaLibros.class, "lista");

        //Creamos la lista de libros
        ListaLibros listaLibros = new ListaLibros();

        listaLibros.add(new Libro("El Quijote", "Miguel de Cervantes", 20.50));
        listaLibros.add(new Libro("1984", "George Orwell", 15.75));
        listaLibros.add(new Libro("Harry Potter", "J.K. Rowling", 18.90));

        try {

            //Serializamos los objetos a XML
            xstream.toXML(
                    listaLibros,
                    new FileOutputStream("Libros.xml")
            );

            System.out.println("Creado fichero XML.....");

            //Deserializamos el fichero XML
            ListaLibros libros;

            libros = (ListaLibros) xstream.fromXML(
                    new FileInputStream("Libros.xml")
            );

            System.out.println("Comienza el proceso de lectura del fichero XML ...");

            System.out.println("Numero de Libros: "
                    + libros.getListaLibros().size());

            List<Libro> lista = libros.getListaLibros();

            ListIterator<Libro> iterator = lista.listIterator();

            while (iterator.hasNext()) {

                Libro libro = iterator.next();

                System.out.println(
                        "Titulo: " + libro.getTitulo()
                        + ", autor: " + libro.getAutor()
                        + ", precio: " + libro.getPrecio()
                );
            }

            System.out.println("Fin del listado");

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();
        }
    }
}