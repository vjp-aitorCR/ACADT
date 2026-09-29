/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;

    // Constructor
    public Libro(String isbn, String titulo, String autor, String editorial) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }

    // Getter del ISBN
    public String getIsbn() {
        return isbn;
    }

    // Getter del titulo
    public String getTitulo() {
        return titulo;
    }

    // Getter del autor
    public String getAutor() {
        return autor;
    }

    // Getter de la editorial
    public String getEditorial() {
        return editorial;
    }
}
