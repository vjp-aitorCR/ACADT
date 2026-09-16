/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
/**
 *
 * @author alumno
 */

public class Ejercicio01 {

    public static void main(String[] args) {

        String ruta = "src/";
        String nombre = "file001.txt";

        File f = new File(ruta + nombre);

        System.out.println("Nombre: " + f.getName());
        System.out.println("Ruta: " + f.getAbsolutePath());
        System.out.println("Directorio padre: " + f.getParent());

        if (f.exists()) {
            System.out.println("¡El fichero existe!");
            System.out.println("Permisos (rwx) => "
                    + f.canRead()
                    + f.canWrite()
                    + f.canExecute());
            System.out.println("Longitud del fichero: "
                    + f.length() + " bytes");
        } else {
            System.out.println("El fichero no existe");
        }
    }
}