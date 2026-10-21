/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio05 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("-- MENU DE CONSULTA --");
            System.out.println("1. Consultar todos los empleados");
            System.out.println("2. Consultar empleado por Numero de empleado (emp_no)");
            System.out.println("3. Consultar empleados con salario superior a uno dado");
            System.out.println("4. Consultar empleados con salario igual o inferior a uno dado");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion:");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    consEmp();
                    break;
                case 2:
                    System.out.println("Introduce el numero de empleado (emp_no):");
                    int emp_no = entrada.nextInt();
                    entrada.nextLine(); // Limpiar buffer
                    consEmpNo(emp_no);
                    break;
                case 3:
                    System.out.println("Introduce el salario límite (se mostrarán salarios > a este):");
                    double salarioSup = entrada.nextDouble();
                    entrada.nextLine();
                    consEmpSalSup(salarioSup);
                    break;
                case 4:
                    System.out.println("Introduce el salario límite (se mostrarán salarios <= a este):");
                    double salarioInf = entrada.nextDouble();
                    entrada.nextLine();
                    consEmpSalInfIgu(salarioInf);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("ERROR: Introduzca un numero valido");
            }
        } while (opcion != 5);

        entrada.close();
    }

    // Metodo para Consultar todos los empleados
    public static void consEmp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM empleados";
            ResultSet resultado = sentencia.executeQuery(sql);

            System.out.println("--- LISTA COMPLETA DE EMPLEADOS ---");

            while (resultado.next()) {
                System.out.println("----------------------------------------");
                System.out.println("Numero de Empleado: " + resultado.getInt("emp_no"));
                System.out.println("Apellido:    " + resultado.getString("apellido"));
                System.out.println("Oficio:      " + resultado.getString("oficio"));
                System.out.println("Dir:         " + resultado.getInt("dir"));
                System.out.println("Fecha Alta:  " + resultado.getDate("fecha_alt"));
                System.out.println("Salario:     " + resultado.getDouble("salario"));

                double comision = resultado.getDouble("comision");
                if (resultado.wasNull()) {
                    System.out.println("Comisión:    NULL");
                } else {
                    System.out.println("Comisión:    " + comision);
                }

                System.out.println("Dept No:     " + resultado.getInt("dept_no"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para consultar los empleados por el campo "emp_no"
    public static void consEmpNo(int emp_no) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

            String sql = "SELECT * FROM empleados WHERE emp_no = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, emp_no);

            ResultSet resultado = sentencia.executeQuery();

            boolean encontrado = false;
            while (resultado.next()) {
                encontrado = true;
                System.out.println("----------------------------------------");
                System.out.println("Numero de Empleado: " + resultado.getInt("emp_no"));
                System.out.println("Apellido:    " + resultado.getString("apellido"));
                System.out.println("Oficio:      " + resultado.getString("oficio"));
                System.out.println("Salario:     " + resultado.getDouble("salario"));
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado ningún empleado con el Numero " + emp_no);
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para consultar empleados con salario superior (AHORA ES EL 3)
    public static void consEmpSalSup(double salarioLimite) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

            String sql = "SELECT apellido, salario FROM empleados WHERE salario > ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setDouble(1, salarioLimite);

            ResultSet resultado = sentencia.executeQuery();

            System.out.println("--- Empleados con salario SUPERIOR a " + salarioLimite + " ---");

            while (resultado.next()) {
                System.out.println("Apellido: " + resultado.getString("apellido")
                        + " | Salario: " + resultado.getDouble("salario"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo consultar empleados con salario igual o inferior (AHORA ES EL 4)
    public static void consEmpSalInfIgu(double salarioLimite) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

            String sql = "SELECT apellido, salario FROM empleados WHERE salario <= ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setDouble(1, salarioLimite);

            ResultSet resultado = sentencia.executeQuery();

            System.out.println("--- Empleados con salario IGUAL O INFERIOR a " + salarioLimite + " ---");

            while (resultado.next()) {
                System.out.println("Apellido: " + resultado.getString("apellido")
                        + " | Salario: " + resultado.getDouble("salario"));
            }

            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
