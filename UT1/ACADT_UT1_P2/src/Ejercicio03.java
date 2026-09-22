/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 *
 * @author alumno
 */


public class Ejercicio03 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        File fichero = new File("correo.txt");

        try {

            //Pedimos los datos
            System.out.print("Destinatario: ");
            String destinatario = entrada.nextLine();

            System.out.print("Asunto: ");
            String asunto = entrada.nextLine();

            System.out.print("Cuerpo del correo: ");
            String cuerpo = entrada.nextLine();

            //Obtenemos la fecha y hora actuales
            LocalDateTime fechaHora = LocalDateTime.now();

            //Formato de la fecha
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            String fechaFormateada = fechaHora.format(formato);

            //Guardamos los datos en el fichero
            FileWriter fw = new FileWriter(fichero);

            fw.write("----- CORREO ELECTRONICO -----");
            fw.write("Destinatario: " + destinatario);
            fw.write("Asunto: " + asunto);
            fw.write("Fecha y hora de envio: " + fechaFormateada);
            fw.write("Cuerpo: " + cuerpo);

            fw.close();

            System.out.println("Correo almacenado correctamente.");

            //Leemos el fichero
            BufferedReader br = new BufferedReader(new FileReader(fichero));

            String linea;

            System.out.println("----- CORREO ENVIADO -----");

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error al trabajar con el fichero.");
            System.out.println("Mensaje: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Se ha producido un error inesperado.");
            System.out.println("Mensaje: " + e.getMessage());

        } finally {

        }
    }
}