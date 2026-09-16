/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
/**
 *
 * @author alumno
 */

public class Ejercicio05 {

    public static void main(String[] args) {

        //Carpeta desde la que comenzamos
        File carpeta = new File("2DAM");

        mostrarContenido(carpeta);
    }

    public static void mostrarContenido(File carpeta) {

        //Obtenemos todos los elementos de la carpeta
        File[] elementos = carpeta.listFiles();

        if (elementos != null) {

            //Recorremos todos los elementos
            for (File elemento : elementos) {

                //Mostramos la ruta absoluta
                System.out.println(elemento.getAbsolutePath());

                //Si es una carpeta, volvemos a llamar al método
                if (elemento.isDirectory()) {
                    mostrarContenido(elemento);
                }
            }
        }
    }
}

