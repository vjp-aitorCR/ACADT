/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();
            Document registroEmpleados = implementacion.createDocument(null, "empleados", null);
            //Asignamos la version XML
            registroEmpleados.setXmlVersion("1.0");

            //Creamos el nodo principal
            Element empleado = registroEmpleados.createElement("empleado");
            //Lo añadimos como hijo de empleados
            registroEmpleados.getDocumentElement().appendChild(empleado);
            //Creamos el nodo id
            Element id = registroEmpleados.createElement("id");
            //Nodo texto con el valor id
            Text texto = registroEmpleados.createTextNode("01");
            //Añadimos el valor al nodo
            id.appendChild(texto);
            //Añadimos id a empleado
            empleado.appendChild(id);
            Element nombre = registroEmpleados.createElement("nombre");
            texto = registroEmpleados.createTextNode("Antonio");
            nombre.appendChild(texto);
            empleado.appendChild(nombre);
            Element apellidos = registroEmpleados.createElement("apellido");
            texto = registroEmpleados.createTextNode("Morales");
            apellidos.appendChild(texto);
            empleado.appendChild(apellidos);
            
            //Guardar el documento
            Source origen = new DOMSource(registroEmpleados);
            Result  resultado = new StreamResult(new File("Empleados.xml"));
            Transformer  transformador = TransformerFactory.newInstance().newTransformer();
            transformador.transform(origen, resultado);
            //Mostrar el resultado por salida
            Result salidaEstandar = new StreamResult(System.out);
            transformador.transform(origen, salidaEstandar);

 
        } catch (IllegalArgumentException | ParserConfigurationException | DOMException e) {

            System.out.println("Error: " + e.getMessage());
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Ejercicio01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Ejercicio01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
