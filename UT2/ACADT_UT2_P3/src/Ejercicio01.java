/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author alumno
 */
public class Ejercicio01 {

    public static void main(String[] args) throws IOException {
        File fichero = new File("contactos.dat");
        ListaContactos listaContactos = null;

        try {
            FileInputStream filein = new FileInputStream(fichero);
            ObjectInputStream dataIS = new ObjectInputStream(filein);
            System.out.println("Comienza el proceso de creacion del fichero a XML ...");

            listaContactos = new ListaContactos();
            try {
                while (true) {
                    Contacto contacto = (Contacto) dataIS.readObject();
                    listaContactos.add(contacto);
                }
            } catch (EOFException eoe) {
                System.out.println("Fin del fichero");
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            } finally {
                dataIS.close();
            }

            //Creamos XStream
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            
            xstream.alias("DatosContacto", Contacto.class);
            xstream.alias("Contactos", ListaContactos.class);
            
            xstream.addImplicitCollection(ListaContactos.class, "lista");
            xstream.omitField(Contacto.class, "telefono");
            xstream.toXML(listaContactos, new FileOutputStream("Contactos.xml"));
            
            System.out.println("Creado fichero XML.....");
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        }   
    }
}
