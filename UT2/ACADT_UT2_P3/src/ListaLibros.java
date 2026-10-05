/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alumno
 */
public class ListaLibros {

    private List<Libro> lista = new ArrayList<>();

    public void add(Libro libro) {
        lista.add(libro);
    }

    public List<Libro> getListaLibros() {
        return lista;
    }
}