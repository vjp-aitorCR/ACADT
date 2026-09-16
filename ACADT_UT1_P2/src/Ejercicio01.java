/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author alumno
 */

public class Ejercicio01 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        //Nombre del fichero
        File fichero = new File("usuario.txt");

        try {
            //Pedimos los datos al usuario
            System.out.print("Introduce tu DNI: ");
            String dni = entrada.nextLine();

            System.out.print("Introduce tu nombre: ");
            String nombre = entrada.nextLine();

            System.out.print("Introduce tus apellidos: ");
            String apellidos = entrada.nextLine();

            System.out.print("Introduce tu fecha de nacimiento: ");
            String fechaNacimiento = entrada.nextLine();

            System.out.print("Introduce tu telefono: ");
            String telefono = entrada.nextLine();
            
            //Abrimos el fichero para escribir
            try ( 
                FileWriter fw = new FileWriter(fichero)) {
                
                fw.write("DNI: " + dni + "\n");
                fw.write("Nombre: " + nombre + "\n");
                fw.write("Apellidos: " + apellidos + "\n");
                fw.write("Fecha de nacimiento: " + fechaNacimiento + "\n");
                fw.write("Telefono: " + telefono + "\n");
            }

            System.out.println("Datos guardados correctamente");
            
            //Abrimos el fichero para leerlo
            try (
                    BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                
                System.out.println("----- DATOS DEL USUARIO -----");
                
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al trabajar con el fichero.");
            System.out.println("Mensaje: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Se ha producido un error inesperado.");
            System.out.println("Mensaje: " + e.getMessage());
        }
    }
}

