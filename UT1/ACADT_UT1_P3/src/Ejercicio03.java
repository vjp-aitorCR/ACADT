/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author alumno
 */

public class Ejercicio03 {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int opcion;

        do{
            //Mostramos el menu
            System.out.println("===== MENU CONTACTOS =====");
            System.out.println("1. Introducir contacto");
            System.out.println("2. Visualizar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");

            try {

                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {
                    case 1:
                        introducirContacto();
                        break;
                    case 2:
                        visualizarContactos();
                        break;
                    case 3:
                        System.out.print("Introduce nombre o apellido a buscar: ");
                        String cadena = entrada.nextLine();
                        buscarContacto(cadena);
                        break;
                    case 4:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                        break;
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Debes introducir un numero.");

                entrada.nextLine();

                opcion = -1;
            }

        } while (opcion != 4);
    }
    //Metodo para introducir contactos
    public static void introducirContacto() {
        Scanner entrada = new Scanner(System.in);
        //Creamos una lista para los contactos
        ArrayList<Contacto> contactos = leerLista();
        System.out.println("--- NUEVO CONTACTO ---");
        //Pedimos los datos
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();
        System.out.print("Telefono: ");
        String telefono = entrada.nextLine();
        //Creamos el contacto
        Contacto contacto = new Contacto(nombre,apellidos,email,telefono);
        //Añadimos el contacto a la lista
        contactos.add(contacto);
        //Guardamos la lista en el fichero
        guardarLista(contactos);
        System.out.println("Contacto guardado correctamente.");
    }
    //Metodo para leet la lista
    public static ArrayList<Contacto> leerLista(){
        ArrayList<Contacto> contactos = new ArrayList<>();
        try{
            //Abrimos el fichero
            ObjectInputStream fichero = new ObjectInputStream(
                    new FileInputStream("contactos.dat"));
            //Leemos la lista completa
            contactos = (ArrayList<Contacto>) fichero.readObject();
            fichero.close();
        } catch (FileNotFoundException e){
            // Si no existe, devolvemos una lista vacia
        } catch (IOException e){
            System.out.println("Error al leer el fichero.");
        } catch (ClassNotFoundException e){
            System.out.println("No se encuentra la clase Contacto.");
        }
        return contactos;
    }
    //Metodo para guardar la lista
    public static void guardarLista(ArrayList<Contacto> contactos){
        try{
            //Abrimos el fichero para escribir
            ObjectOutputStream fichero = new ObjectOutputStream(
                    new FileOutputStream("contactos.dat"));
            //Guardamos la lista
            fichero.writeObject(contactos);
            fichero.close();
        } catch (FileNotFoundException e){
            System.out.println("No se puede crear el fichero.");
        } catch (IOException e){
            System.out.println("Error al guardar los contactos.");
        }
    }
    //Metodo para visualizar los contactos
    public static void visualizarContactos(){
        //Leemos todos los contactos
        ArrayList<Contacto> contactos = leerLista();
        System.out.println("===== CONTACTOS =====");
        if (contactos.isEmpty()){
            System.out.println("No hay contactos.");
        } else {
            //Recorremos la lista
            for (Contacto contacto : contactos){
                System.out.println("-------------------------");
                System.out.println(contacto);
            }
        }
    }
    //Metodo para buscar contacto
    public static void buscarContacto(String cadena){
        //Leemos los contactos
        ArrayList<Contacto> contactos = leerLista();
        boolean encontrado = false;
        //Convertimos la busqueda a minusculas
        cadena = cadena.toLowerCase();
        System.out.println("===== RESULTADOS =====");
        //Recorremos todos los contactos
        for (Contacto contacto : contactos) {
            //Convertimos nombre y apellidos a minusculas
            String nombre = contacto.nombre.toLowerCase();
            String apellidos = contacto.apellidos.toLowerCase();
            //Buscamos la cadena en nombre o apellidos
            if (nombre.contains(cadena) || apellidos.contains(cadena)) {
                System.out.println("-------------------------");
                System.out.println(contacto);
                encontrado = true;
            }
        }
        //Si no hemos encontrado nada
        if (!encontrado){
            System.out.println("No se ha encontrado ningun contacto.");
        }
    }
}