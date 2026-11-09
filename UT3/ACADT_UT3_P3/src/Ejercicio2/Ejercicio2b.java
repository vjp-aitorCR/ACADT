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
public class Ejercicio2b {

    public static void altaEmpleado(int empNo, String apellido, String oficio,
            int dir, String fecha, double salario,
            double comision, int deptNo) {

        String url = "jdbc:mysql://localhost/empresa";
        String user = "root";
        String pass = "";

        // El último parámetro es OUT → se registra con registerOutParameter
        String sql = "{call alta_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conexion = DriverManager.getConnection(url, user, pass); CallableStatement cst = conexion.prepareCall(sql)) {

            // Parámetros IN
            cst.setInt(1, empNo);
            cst.setString(2, apellido);
            cst.setString(3, oficio);
            cst.setInt(4, dir);
            cst.setDate(5, Date.valueOf(fecha));   // fecha en formato "yyyy-MM-dd"
            cst.setDouble(6, salario);
            cst.setDouble(7, comision);
            cst.setInt(8, deptNo);

            // Parámetro OUT (posición 9, tipo VARCHAR)
            cst.registerOutParameter(9, Types.VARCHAR);

            // Ejecutamos el procedimiento
            cst.executeUpdate();

            // Leemos el valor devuelto por el parámetro OUT
            System.out.println(cst.getString(9));

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("EMP_NO: ");
        int empNo = sc.nextInt();
        sc.nextLine();
        System.out.print("APELLIDO: ");
        String apellido = sc.nextLine();
        System.out.print("OFICIO: ");
        String oficio = sc.nextLine();
        System.out.print("DIR: ");
        int dir = sc.nextInt();
        sc.nextLine();
        System.out.print("FECHA_ALT (yyyy-MM-dd): ");
        String fecha = sc.nextLine();
        System.out.print("SALARIO: ");
        double salario = sc.nextDouble();
        System.out.print("COMISION: ");
        double comision = sc.nextDouble();
        System.out.print("DEPT_NO: ");
        int deptNo = sc.nextInt();

        altaEmpleado(empNo, apellido, oficio, dir, fecha, salario, comision, deptNo);
        sc.close();
    }
}
