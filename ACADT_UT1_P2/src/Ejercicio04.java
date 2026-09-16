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


public class Ejercicio04 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        File fichero = new File("usuarios.txt");

        int opcion;

        try {

            do {

                System.out.println("===== MENU =====");
                System.out.println("1. Añadir usuario");
                System.out.println("2. Mostrar todos los usuarios");
                System.out.println("3. Mostrar los N primeros usuarios");
                System.out.println("4. Buscar usuario por DNI");
                System.out.println("5. Salir");
                System.out.print("Elige una opciOn: ");

                try {

                    opcion = entrada.nextInt();
                    entrada.nextLine();

                } catch (InputMismatchException e) {

                    System.out.println("Error: debes introducir un numero.");
                    entrada.nextLine();
                    opcion = 0;
                }

                switch (opcion) {

                case 1:

                    //Añadir usuario
                    System.out.print("DNI: ");
                    String dni = entrada.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.print("Apellidos: ");
                    String apellidos = entrada.nextLine();

                    System.out.print("Fecha de nacimiento: ");
                    String fechaNacimiento = entrada.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = entrada.nextLine();

                    //true significa que añadimos al fichero
                    //sin borrar los usuarios anteriores
                    FileWriter fw = new FileWriter(fichero, true);

                    fw.write("DNI: " + dni);
                    fw.write("Nombre: " + nombre);
                    fw.write("Apellidos: " + apellidos);
                    fw.write("Fecha de nacimiento: " + fechaNacimiento);
                    fw.write("Teléfono: " + telefono);
                    fw.write("-------------------------");

                    fw.close();

                    System.out.println("Usuario guardado correctamente.");

                    break;

                case 2:

                    //Mostrar todos los usuarios
                    mostrarTodos(fichero);

                    break;

                case 3:

                    //Mostrar los N primeros
                    try {

                        System.out.print("Cuantos usuarios quieres mostrar? ");
                        int n = entrada.nextInt();
                        entrada.nextLine();

                        mostrarNPrimeros(fichero, n);

                    } catch (InputMismatchException e) {

                        System.out.println("Debes introducir un numero entero.");
                        entrada.nextLine();
                    }

                    break;

                case 4:

                    //Buscar por DNI
                    System.out.print("Introduce el DNI que quieres buscar: ");
                    String dniBuscar = entrada.nextLine();

                    buscarDNI(fichero, dniBuscar);

                    break;

                case 5:

                    System.out.println("Programa terminado.");

                    break;

                default:

                    if (opcion != 0) {
                        System.out.println("Opcion incorrecta.");
                    }

                    break;
                }

            } while (opcion != 5);

        } catch (Exception e) {

            System.out.println("Se ha producido un error inesperado.");
            System.out.println("Mensaje: " + e.getMessage());

        } finally {

        }
    }


    //MOSTRAR TODOS LOS USUARIOS
    public static void mostrarTodos(File fichero) {

        if (!fichero.exists()) {

            System.out.println("No hay usuarios guardados.");
            return;
        }

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(fichero));

            String linea;

            System.out.println("===== TODOS LOS USUARIOS =====");

            while ((linea = br.readLine()) != null) {

                System.out.println(linea);
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
            System.out.println("Mensaje: " + e.getMessage());
        }
    }


    //MOSTRAR LOS N PRIMEROS USUARIOS
    public static void mostrarNPrimeros(File fichero, int n) {

        if (n <= 0) {

            System.out.println("El numero debe ser mayor que 0.");
            return;
        }

        if (!fichero.exists()) {

            System.out.println("No hay usuarios guardados.");
            return;
        }

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(fichero));

            String linea;

            int usuarios = 0;

            boolean esUsuario = false;

            System.out.println("\n===== PRIMEROS USUARIOS =====");

            while ((linea = br.readLine()) != null) {

                //Cada usuario comienza con DNI
                if (linea.startsWith("DNI:")) {

                    usuarios++;

                    esUsuario = true;
                }

                if (usuarios > n) {
                    break;
                }

                System.out.println(linea);
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
            System.out.println("Mensaje: " + e.getMessage());
        }
    }


    // BUSCAR USUARIO POR DNI
    public static void buscarDNI(File fichero, String dniBuscar) {

        if (!fichero.exists()) {

            System.out.println("No hay usuarios guardados.");
            return;
        }

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(fichero));

            String linea;

            boolean encontrado = false;

            System.out.println("===== RESULTADO =====");

            while ((linea = br.readLine()) != null) {

                if (linea.equals("DNI: " + dniBuscar)) {

                    encontrado = true;

                    // Mostramos los datos del usuario
                    System.out.println(linea);

                    for (int i = 0; i < 5; i++) {

                        linea = br.readLine();

                        if (linea != null) {
                            System.out.println(linea);
                        }
                    }

                    break;
                }
            }

            br.close();

            if (!encontrado) {

                System.out.println("No existe ningun usuario con ese DNI.");
            }

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
            System.out.println("Mensaje: " + e.getMessage());
        }
    }
}