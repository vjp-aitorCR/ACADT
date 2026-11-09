/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author Personal
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        String url = "jdbc:derby:C:/AD/UT3/DERBY/ejemplo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLEADOS")) {
            while (rs.next()) {
                System.out.println(rs.getInt("EMP_NO") + " - " + rs.getString("APELLIDO"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
