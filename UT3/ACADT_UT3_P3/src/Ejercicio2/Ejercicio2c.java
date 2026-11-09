/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.sql.*;

/**
 *
 * @author Personal
 */
public class Ejercicio2c {

    public static void empleadosAntiguedad2Anios() {
        String url = "jdbc:mysql://localhost/empresa";
        String user = "root";
        String pass = "";

        // Procedimiento SIN parámetros → forma {call procedimiento}
        String sql = "{call empleados_antiguedad_2anios()}";

        try (Connection conexion = DriverManager.getConnection(url, user, pass); CallableStatement cst = conexion.prepareCall(sql); ResultSet rs = cst.executeQuery()) {

            boolean hay = false;
            while (rs.next()) {
                hay = true;
                System.out.println(
                        "EMP_NO: " + rs.getInt("EMP_NO")
                        + " | APELLIDO: " + rs.getString("APELLIDO")
                        + " | OFICIO: " + rs.getString("OFICIO")
                        + " | FECHA_ALT: " + rs.getDate("FECHA_ALT")
                        + " | SALARIO: " + rs.getDouble("SALARIO")
                        + " | DEPT_NO: " + rs.getInt("DEPT_NO"));
            }
            if (!hay) {
                System.out.println("No hay empleados con más de 2 años de antigüedad.");
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        empleadosAntiguedad2Anios();
    }
}
