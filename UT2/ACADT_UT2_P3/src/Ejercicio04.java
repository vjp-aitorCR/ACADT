/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author alumno
 */
public class Ejercicio04 {

    public static void main(String[] args) {

        //Creamos XStream
        XStream xstream = new XStream(new DomDriver("UTF-8"));

        xstream.addPermission(AnyTypePermission.ANY);

        xstream.alias("blog", Blog.class);
        xstream.alias("autor", Autor.class);
        xstream.alias("entrada", Entrada.class);

        xstream.addImplicitCollection(Blog.class, "entrada");

        try {

            //Deserializamos el fichero XML
            Blog blog = (Blog) xstream.fromXML(
                    new FileInputStream("blog.xml")
            );

            System.out.println("Autor: "
                    + blog.getAutor().getNombre());

            System.out.println();

            List<Entrada> listaEntradas = blog.getEntrada();

            ListIterator<Entrada> iterator = listaEntradas.listIterator();

            while (iterator.hasNext()) {

                Entrada entrada = iterator.next();

                System.out.println("Titulo: "
                        + entrada.getTitulo());

                System.out.println("Descripcion: "
                        + entrada.getDescripcion());

                System.out.println();
            }

            System.out.println("Fin del listado");

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();
        }
    }
}