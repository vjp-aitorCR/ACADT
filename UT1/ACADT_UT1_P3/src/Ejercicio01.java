/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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
public class Ejercicio01 {

    public static void main(String[] args) {

        // Creamos un unico Scanner para todo el programa
        Scanner entrada = new Scanner(System.in);

        int opcion;

        do {

            // Mostramos el menu
            System.out.println("===== MENU =====");
            System.out.println("1. Introducir alumnos");
            System.out.println("2. Visualizar el fichero");
            System.out.println("3. Visualizar los alumnos con mejor media");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            try {

                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {

                    case 1:
                        introducirAlumnos(entrada);
                        break;

                    case 2:
                        visualizarAlumnos();
                        break;

                    case 3:
                        visualizarMejoresAlumnos();
                        break;

                    case 4:
                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opcion incorrecta.");
                        break;
                }

            } catch (InputMismatchException e) {

                // Si escribimos algo que no es un numero
                System.out.println("Error: debes introducir un numero.");

                // Limpiamos lo que hemos escrito
                entrada.nextLine();

                opcion = -1;
            }

        } while (opcion != 4);
    }

    // Metodo para introducir los alumnos
    public static void introducirAlumnos(Scanner entrada) {

        try {

            // Abrimos el fichero para escribir
            // true significa que añadimos los datos al final
            DataOutputStream fichero = new DataOutputStream(
                    new FileOutputStream("notas.dat", true));

            // Buscamos el siguiente ID
            int id = obtenerSiguienteId();

            boolean continuar = true;

            while (continuar) {

                System.out.println("--- NUEVO ALUMNO ---");

                // Pedimos el nombre
                System.out.print("Nombre: ");
                String nombre = entrada.nextLine();

                // Pedimos los apellidos
                System.out.print("Apellidos: ");
                String apellidos = entrada.nextLine();

                double redes = 0;
                double programacion = 0;

                // Pedimos la nota de Redes
                boolean notaCorrecta = false;

                while (!notaCorrecta) {

                    try {

                        System.out.print("Nota de Redes: ");
                        redes = entrada.nextDouble();

                        // Comprobamos que la nota sea correcta
                        if (redes >= 0 && redes <= 10) {

                            notaCorrecta = true;

                        } else {

                            System.out.println(
                                    "La nota debe estar entre 0 y 10.");
                        }

                    } catch (InputMismatchException e) {

                        System.out.println(
                                "Debes introducir un numero.");

                        entrada.nextLine();
                    }
                }

                // Pedimos la nota de Programacion
                notaCorrecta = false;

                while (!notaCorrecta) {

                    try {

                        System.out.print("Nota de Programacion: ");
                        programacion = entrada.nextDouble();

                        if (programacion >= 0 && programacion <= 10) {

                            notaCorrecta = true;

                        } else {

                            System.out.println(
                                    "La nota debe estar entre 0 y 10.");
                        }

                    } catch (InputMismatchException e) {

                        System.out.println(
                                "Debes introducir un numero.");

                        entrada.nextLine();
                    }
                }

                // Limpiamos el salto de linea
                entrada.nextLine();

                // Guardamos todos los datos en el fichero
                fichero.writeInt(id);
                fichero.writeUTF(nombre);
                fichero.writeUTF(apellidos);
                fichero.writeDouble(redes);
                fichero.writeDouble(programacion);

                System.out.println("Alumno guardado correctamente.");

                // Aumentamos el ID para el siguiente alumno
                id++;

                // Preguntamos si queremos introducir otro
                System.out.print("Quieres introducir otro alumno? (s/n): ");
                String respuesta = entrada.nextLine();

                if (!respuesta.equalsIgnoreCase("s")) {

                    continuar = false;
                }
            }

            // Cerramos el fichero
            fichero.close();

        } catch (FileNotFoundException e) {

            System.out.println("No se puede abrir el fichero.");

        } catch (IOException e) {

            System.out.println("Error al escribir en el fichero.");
        }
    }

    // Metodo para obtener el siguiente ID
    public static int obtenerSiguienteId() {

        int id = 1;

        try {

            // Abrimos el fichero para leer
            DataInputStream fichero = new DataInputStream(
                    new FileInputStream("notas.dat"));

            // Leemos todos los alumnos
            while (true) {

                // Leemos el ID
                id = fichero.readInt() + 1;

                // Leemos los demas datos
                fichero.readUTF();
                fichero.readUTF();
                fichero.readDouble();
                fichero.readDouble();
            }

        } catch (EOFException e) {

            // Hemos llegado al final del fichero

        } catch (FileNotFoundException e) {

            // Si no existe, empezamos en 1
            id = 1;

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
        }

        return id;
    }

    // Metodo para visualizar los alumnos
    public static void visualizarAlumnos() {

        try {

            // Abrimos el fichero para leer
            DataInputStream fichero = new DataInputStream(
                    new FileInputStream("notas.dat"));

            System.out.println("===== ALUMNOS =====");

            while (true) {

                // Leemos los datos
                int id = fichero.readInt();
                String nombre = fichero.readUTF();
                String apellidos = fichero.readUTF();
                double redes = fichero.readDouble();
                double programacion = fichero.readDouble();

                // Calculamos la media
                double media = (redes + programacion) / 2;

                // Mostramos los datos
                System.out.println("-------------------------");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellidos: " + apellidos);
                System.out.println("Redes: " + redes);
                System.out.println("Programacion: " + programacion);
                System.out.println("Media: " + media);
            }

        } catch (EOFException e) {

            // Hemos llegado al final del fichero

        } catch (FileNotFoundException e) {

            System.out.println("El fichero notas.dat no existe.");

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
        }
    }

    // Metodo para visualizar los alumnos con mejor media
    public static void visualizarMejoresAlumnos() {

        double mejorMedia = -1;

        try {

            // Abrimos el fichero para buscar la mejor media
            DataInputStream fichero = new DataInputStream(
                    new FileInputStream("notas.dat"));

            while (true) {

                // Leemos los datos
                fichero.readInt();
                fichero.readUTF();
                fichero.readUTF();

                double redes = fichero.readDouble();
                double programacion = fichero.readDouble();

                // Calculamos la media
                double media = (redes + programacion) / 2;

                // Si es mayor que la anterior, la guardamos
                if (media > mejorMedia) {

                    mejorMedia = media;
                }
            }

        } catch (EOFException e) {

            // Hemos terminado de buscar

        } catch (FileNotFoundException e) {

            System.out.println("El fichero notas.dat no existe.");
            return;

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
            return;
        }

        try {

            // Volvemos a abrir el fichero para mostrar
            // los alumnos que tienen esa media
            DataInputStream fichero = new DataInputStream(
                    new FileInputStream("notas.dat"));

            System.out.println("===== MEJORES ALUMNOS =====");

            while (true) {

                int id = fichero.readInt();
                String nombre = fichero.readUTF();
                String apellidos = fichero.readUTF();
                double redes = fichero.readDouble();
                double programacion = fichero.readDouble();

                double media = (redes + programacion) / 2;

                // Mostramos los que tienen la mejor media
                if (media == mejorMedia) {

                    System.out.println("-------------------------");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Apellidos: " + apellidos);
                    System.out.println("Media: " + media);
                }
            }

        } catch (EOFException e) {

            // Hemos llegado al final

        } catch (FileNotFoundException e) {

            System.out.println("El fichero no existe.");

        } catch (IOException e) {

            System.out.println("Error al leer el fichero.");
        }
    }
}