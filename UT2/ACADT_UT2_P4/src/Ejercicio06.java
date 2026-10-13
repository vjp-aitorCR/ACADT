/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author alumno
 */
public class Ejercicio06 {

    public static void main(String[] args) {
        //Creamos una lista de libros
        ArrayList<Libro> libros = new ArrayList<Libro>();

        Libro libro1 = new Libro("Don Quijote", "Cervantes", 19.95);
        Libro libro2 = new Libro("El principito", "Saint-Exupery", 12.50);

        libros.add(libro1);
        libros.add(libro2);

        //Serializamos la lista
        String json = serializarLibros(libros);
        //Pasamos el JSON al metodo de deserializacion
        deserializarLibros(json);
    }
    //Metodo para serializar la lista de libros a JSON
    public static String serializarLibros(List<Libro> libros) {
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

        String json = prettyGson.toJson(libros);

        System.out.println("JSON de los libros:");
        System.out.println(json);
        return json;
    }
    //Metodo para deserializar el JSON a una lista de libros
    public static List<Libro> deserializarLibros(String json) {
        Gson gson = new Gson();
        Type tipoLista = new TypeToken<ArrayList<Libro>>() {
        }.getType();
        List<Libro> libros = gson.fromJson(json, tipoLista);
        System.out.println("\nLista de libros deserializada:");
        for (Libro libro : libros) {
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Precio: " + libro.getPrecio());
            System.out.println();
        }
        return libros;
    }
}
