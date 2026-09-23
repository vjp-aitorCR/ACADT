/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
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

        try{
            //Creamos el fichero binario
            DataOutputStream fichero = new DataOutputStream(
                    new FileOutputStream("tiempo.dat"));
            //Introducimos los datos de los 7 dias
            for (int i = 1; i <= 7; i++){
                System.out.println("--- DIA " + i + " ---");
                //Pedimos la fecha
                System.out.print("Fecha: ");
                String fecha = entrada.nextLine();
                double minima = 0;
                double maxima = 0;
                boolean correcto = false;
                //Pedimos las temperaturas
                while (!correcto){
                    try{
                        System.out.print("Temperatura minima: ");
                        minima = entrada.nextDouble();
                        System.out.print("Temperatura maxima: ");
                        maxima = entrada.nextDouble();
                        //La maxima no puede ser menor que la minima
                        if (maxima >= minima){
                            correcto = true;
                        } 
                        else{
                            System.out.println("La temperatura maxima no puede ser menor que la minima.");
                        }

                    } catch (InputMismatchException e){
                        System.out.println("Debes introducir un numero.");
                        //Limpiamos la entrada incorrecta
                        entrada.nextLine();
                    }
                }

                //Limpiamos el salto de linea
                entrada.nextLine();
                //Guardamos los datos en el fichero
                fichero.writeUTF(fecha);
                fichero.writeDouble(minima);
                fichero.writeDouble(maxima);
            }
            //Cerramos el fichero
            fichero.close();
            System.out.println("Fichero tiempo.dat creado correctamente.");

            //Abrimos el fichero para leer
            DataInputStream lectura = new DataInputStream(
                    new FileInputStream("tiempo.dat"));
            String diaMasCaluroso = "";
            double temperaturaMayor = -999;
            //Leemos los 7 dias
            for (int i = 1; i <= 7; i++) {
                String fecha = lectura.readUTF();
                double minima = lectura.readDouble();
                double maxima = lectura.readDouble();
                // Comparamos las temperaturas maximas
                if (maxima > temperaturaMayor) {
                    temperaturaMayor = maxima;
                    diaMasCaluroso = fecha;
                }
            }
            //Cerramos el fichero
            lectura.close();
            //Mostramos el resultado
            System.out.println("===== DIA MAS CALUROSO =====");
            System.out.println("Fecha: " + diaMasCaluroso);
            System.out.println( "Temperatura maxima: " + temperaturaMayor);

        } catch (FileNotFoundException e){

            System.out.println("No se encuentra el fichero.");

        } catch (IOException e){

            System.out.println(
                    "Error al trabajar con el fichero: "
                    + e.getMessage());

        }
    }
}
