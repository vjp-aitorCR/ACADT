/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author alumno
 */

public class Ejercicio02 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        File fichero = new File("numeros.txt");

        try {

            //Abrimos el fichero para escribir
            FileWriter fw = new FileWriter(fichero);

            int numero;

            System.out.println("Introduce numeros enteros.");
            System.out.println("Introduce 0 para terminar.");

            do {

                try {

                    System.out.print("Numero: ");
                    numero = entrada.nextInt();

                    //Si no es 0, lo guardamos
                    if (numero != 0) {
                        fw.write(numero);
                    }

                } catch (InputMismatchException e) {

                    System.out.println("Error: debes introducir un numero entero.");

                    //Limpiamos la entrada incorrecta
                    entrada.nextLine();

                    numero = -1;
                }

            } while (numero != 0);

            fw.close();

            //Ahora leemos el fichero
            BufferedReader lector = new BufferedReader(new FileReader(fichero));

            String linea;
            int suma = 0;

            while ((linea = lector.readLine()) != null) {

                try {

                    int numeroLeido = Integer.parseInt(linea);
                    suma = suma + numeroLeido;

                } catch (NumberFormatException e) {

                    System.out.println("Se ha encontrado un dato incorrecto en el fichero.");

                }
            }

            lector.close();

            System.out.println("La suma de los numeros es: " + suma);

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
