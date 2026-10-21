/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class Ejercicio04 {

    public static void main(String[] args) {
        apellidoSalario();

    }

    public static void apellidoSalario() {
        try {
            //Cargar el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecemos conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
            //Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            //Consulta para el departamento 10
            String sql = "SELECT apellido, salario FROM empleados WHERE salario = (SELECT MAX(salario) FROM empleados)";
            ResultSet resultado = sentencia.executeQuery(sql);

            //Recorremos el resultado
            while (resultado.next()) {
                System.out.println("Apellido: " + resultado.getString("apellido"));
                System.out.println("Salario: " + resultado.getDouble("salario"));
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
