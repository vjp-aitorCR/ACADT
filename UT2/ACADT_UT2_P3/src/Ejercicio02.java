/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thoughtworks.xstream.XStream;
/**
 *
 * @author alumno
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //XML que queremos convertir en un objeto
        String xml =
                "<Libro>"
                + "<isbn>239-87-9964-088-4</isbn>"
                + "<titulo>Acceso a Datos</titulo>"
                + "<autor>Alicia Ramos</autor>"
                + "<editorial>Garceta</editorial>"
                + "</Libro>";

        //Creamos XStream
        XStream xstream = new XStream();

        //Indicamos que puede utilizar la clase Libro
        xstream.allowTypes(new Class[]{Libro.class});

        //Convertimos el XML en un objeto
        Libro libro = (Libro) xstream.fromXML(xml);

        //Mostramos los datos del objeto
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Editorial: " + libro.getEditorial());
    }
}