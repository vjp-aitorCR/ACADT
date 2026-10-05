/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ListIterator;
/**
 *
 * @author alumno
 */
public class Ejercicio02 {

    public static void main(String[] args) {
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("DatosContactos", Contacto.class);
        xstream.alias("Contactos", ListaContactos.class);
        xstream.addImplicitCollection(ListaContactos.class, "lista");
        
        try{
            ListaContactos contactos;
            contactos = (ListaContactos) xstream.fromXML(new FileInputStream("Contactos.xml"));
            System.out.println("Numero de Contactos: " + contactos.getListaContactos().size());
            List<Contacto> listaContactos = contactos.getListaContactos();
            
            ListIterator<Contacto> iterator = listaContactos.listIterator();
            while (iterator.hasNext()){
                Contacto c = (Contacto) iterator.next();
                System.out.println(
                        "Nombre: " + c.getNombre()
                        + ", apellidos: " + c.getApellidos()
                        + ", email: " + c.getEmail());
            }
            System.out.println("Fin del listado");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}