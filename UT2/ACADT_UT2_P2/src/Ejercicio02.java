/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**
 *
 * @author alumno
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        try {

            //Creamos el lector SAX
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser parser = fabrica.newSAXParser();

            //Creamos una lista de empleados
            ArrayList<Empleado> empleados = new ArrayList<>();

            //Creamos el manejador
            ManejadorEmpleados manejador = new ManejadorEmpleados(empleados);

            //Leemos el fichero XML
            parser.parse(new File("empleados.xml"), manejador);

            //Ordenamos los empleados por sueldo
            Collections.sort(empleados, new Comparator<Empleado>() {

                @Override
                public int compare(Empleado empleado1, Empleado empleado2) {

                    return Double.compare(
                            empleado1.getSueldo(),
                            empleado2.getSueldo());
                }
            });

            //Mostramos los empleados
            for (Empleado empleado : empleados) {

                System.out.println(
                        empleado.getNombre() + " "
                        + empleado.getApellidos()
                        + ", sueldo: "
                        + String.format("%.0f", empleado.getSueldo())
                        + "€");
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}


//Manejador SAX
class ManejadorEmpleados extends DefaultHandler {

    private ArrayList<Empleado> empleados;

    private String nombre;
    private String apellidos;
    private double sueldo;

    private String elementoActual;

    //Constructor
    public ManejadorEmpleados(ArrayList<Empleado> empleados) {

        this.empleados = empleados;
    }

    //Cuando empieza una etiqueta
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes atributos) {

        elementoActual = qName;
    }

    //Cuando encontramos texto
    @Override
    public void characters(char[] ch, int inicio, int longitud) {

        String texto = new String(ch, inicio, longitud).trim();

        if (texto.isEmpty()) {
            return;
        }

        if (elementoActual.equals("nombre")) {

            nombre = texto;

        } else if (elementoActual.equals("apellidos")) {

            apellidos = texto;

        } else if (elementoActual.equals("sueldo")) {

            sueldo = Double.parseDouble(texto);
        }
    }

    //Cuando termina una etiqueta
    @Override
    public void endElement(String uri, String localName, String qName) {

        // Cuando termina un empleado, creamos el objeto
        if (qName.equals("empleado")) {

            Empleado empleado = new Empleado(
                    nombre,
                    apellidos,
                    sueldo);

            empleados.add(empleado);
        }

        elementoActual = "";
    }
}
