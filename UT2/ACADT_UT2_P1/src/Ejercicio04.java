/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author alumno
 */
public class Ejercicio04 {

    public static void main(String[] args) {

        try {
            //Creamos el DocumentBuilder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            //Leemos el Document desde el fichero
            Document registroEmpleados = builder.parse(new File("Empleados.xml"));
            //Normalizamos el documento
            registroEmpleados.getDocumentElement().normalize();

            //Mostramos el nombre del elemento raiz
            System.out.println("El elemento raiz es " + registroEmpleados.getDocumentElement().getNodeName());

            //Creamos una lista de todos los nodos empleado
            NodeList empleados = registroEmpleados.getElementsByTagName("empleado");
            //Mostrar el numero de empleados
            System.out.println("Se han encontrado " + empleados.getLength() + " empleados");

            //Recorremos la lista.
            for (int i = 0; i < empleados.getLength(); i++) {

                //Obtenemos el primer nodo de la lista
                Node emple = empleados.item(i);

                //En caso de que ese nodo sea un Elemento
                if (emple.getNodeType() == Node.ELEMENT_NODE) {

                    //Creamos el elemento empleado y leemos su información
                    Element empleado = (Element) emple;

                    System.out.print("ID: "
                            + empleado.getElementsByTagName("id")
                                    .item(0).getTextContent());

                    System.out.print("\tNombre: "
                            + empleado.getElementsByTagName("nombre")
                                    .item(0).getTextContent());

                    System.out.println("\tApellido: "
                            + empleado.getElementsByTagName("apellido")
                                    .item(0).getTextContent());
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
