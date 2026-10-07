/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Arrays;
import com.google.gson.Gson;
/**
 *
 * @author alumno
 */
public class Ejercicio01 {
    public static void main(String[] args){
        Empleado emp1 = new Empleado("Pepe", "Lopez", 20, Arrays.asList("Gerente", "Jefe de zona"));
        Gson gson = new Gson();
        System.out.println(gson.toJson(emp1));
    }
}
