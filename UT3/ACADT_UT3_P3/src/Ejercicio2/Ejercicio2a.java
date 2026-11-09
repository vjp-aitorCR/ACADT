/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Personal
 */
public class Ejercicio2a {
    // Método que llama al procedimiento y recorre el ResultSet

    public static void empleadosPorDepartamento(String nomDep) {
        String url = "jdbc:mysql://localhost/empresa";
        String user = "root";
        String pass = "";

        // SQL de llamada: procedimiento con un parámetro de entrada
        String sql = "{call empleados_por_departamento(?)}";

        try (Connection conexion = DriverManager.getConnection(url, user, pass); CallableStatement cst = conexion.prepareCall(sql)) {

            // Asignamos el valor al primer marcador (parámetro IN)
            cst.setString(1, nomDep);

            // Ejecutamos y obtenemos el resultado (el procedimiento hace un SELECT)
            try (ResultSet rs = cst.executeQuery()) {
                boolean hay = false;
                while (rs.next()) {
                    hay = true;
                    System.out.println(
                            "EMP_NO: " + rs.getInt("EMP_NO")
                            + " | APELLIDO: " + rs.getString("APELLIDO")
                            + " | OFICIO: " + rs.getString("OFICIO")
                            + " | SALARIO: " + rs.getDouble("SALARIO")
                            + " | DEPARTAMENTO: " + rs.getString("DNOMBRE"));
                }
                if (!hay) {
                    System.out.println("No hay empleados en el departamento '" + nomDep + "'.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del departamento: ");
        String nomDep = sc.nextLine();

        empleadosPorDepartamento(nomDep);
        sc.close();
    }
}
