/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.IOException;
/**
 *
 * @author alumno
 */

public class Ejercicio02 {

    public static void main(String[] args) throws IOException {

        //Crear carpeta principal 2DAM
        File carpeta2DAM = new File("2DAM");
        carpeta2DAM.mkdir();

        //Crear las carpetas AD y PSP
        File carpetaAD = new File("2DAM/AD");
        File carpetaPSP = new File("2DAM/PSP");

        carpetaAD.mkdir();
        carpetaPSP.mkdir();

        //Crear archivos dentro de AD
        File p1 = new File("2DAM/AD/P1.txt");
        File ud1AD = new File("2DAM/AD/UD1.txt");
        File ud2AD = new File("2DAM/AD/UD2.txt");

        p1.createNewFile();
        ud1AD.createNewFile();
        ud2AD.createNewFile();

        //Crear archivos dentro de PSP
        File ud1PSP = new File("2DAM/PSP/UD1.txt");
        File ud2PSP = new File("2DAM/PSP/UD2.txt");

        ud1PSP.createNewFile();
        ud2PSP.createNewFile();

        //Crear misNotas.txt dentro de 2DAM
        File misNotas = new File("2DAM/misNotas.txt");
        misNotas.createNewFile();

        System.out.println("Estructura creada correctamente.");
    }
}
