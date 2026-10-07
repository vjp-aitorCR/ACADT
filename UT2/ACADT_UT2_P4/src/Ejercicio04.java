/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

/**
 *
 * @author alumno
 */
public class Ejercicio04 {

    public static void main(String[] args) {
        Empleado emp1 = new Empleado("Pepe", "Lopez", 20,
                Arrays.asList("Gerente", "Jefe de zona"));
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        //Si queremos mostrar el nombre del objeto en el JSON
        SerializeEmpleado s_empleado = new SerializeEmpleado(emp1);
        System.out.println(emp1.toString());
    }
}
