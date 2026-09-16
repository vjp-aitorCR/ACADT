/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
/**
 *
 * @author alumno
 */

public class Ejercicio04 {

    public static void main(String[] args) {

        //Creamos un objeto File que representa la carpeta 2DAM
        File carpeta = new File("2DAM");

        //Obtenemos todos los elementos que contiene
        File[] elementos = carpeta.listFiles();

        //Comprobamos que la carpeta existe
        if (elementos != null) {

            //Mostramos los elementos
            for (File elemento : elementos) {
                System.out.println(elemento.getName());
            }

        } else {
            System.out.println("La carpeta no existe.");
        }
    }
}
