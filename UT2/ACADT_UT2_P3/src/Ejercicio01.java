/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thoughtworks.xstream.XStream;

/**
 *
 * @author alumno
 */
public class Ejercicio01 {

    public static void main(String[] args) {
        //Creamos un objeto Libro
        Libro libro = new Libro(
                "239-87-9964-088-4",
                "Acceso a Datos",
                "Alicia Ramos",
                "Garceta");

        //Creamos XStream
        XStream xstream = new XStream();

        //Indicamos que puede utilizar la clase Libro
        xstream.allowTypes(new Class[]{Libro.class});

        //Convertimos el objeto a XML
        String xml = xstream.toXML(libro);

        //Mostramos el XML
        System.out.println(xml);
    }
}
