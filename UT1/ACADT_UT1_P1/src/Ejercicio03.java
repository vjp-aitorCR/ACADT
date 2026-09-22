/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
/**
 *
 * @author alumno
 */

public class Ejercicio03 {

    public static void main(String[] args) {

        //Archivo original
        File archivoOriginal = new File("2DAM/AD/P1.txt");

        //Nuevo nombre
        File archivoNuevo = new File("2DAM/AD/practica1.txt");

        //Renombramos el archivo
        if (archivoOriginal.renameTo(archivoNuevo)) {
            System.out.println("Archivo renombrado correctamente.");
        } else {
            System.out.println("No se ha podido renombrar el archivo.");
        }
    }
}

