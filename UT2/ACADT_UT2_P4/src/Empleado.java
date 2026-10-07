/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
import java.util.List;

public class Empleado {

    private String nombre;
    private String apellidos;
    private int edad;
    private List<String> puestos;

    public Empleado(String nombre, String apellidos, int edad, List<String> cargos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.puestos = cargos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public List<String> getCargos() {
        return puestos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCargos(List<String> cargos) {
        this.puestos = cargos;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", puestos=" + puestos + '}';
    }
    
    
}
