/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author alumno
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        selectEmpDept10();
    }
    
    public static void selectEmpDept10(){
        try{
            //Cargar el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecemos conexion con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
            //Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            //Consulta para el departamento 10
            String sql = "SELECT apellido, oficio, salario FROM empleados WHERE dept_no = 10";
            ResultSet resultado = sentencia.executeQuery(sql);
            
            //Recorremos el resultado
            while (resultado.next()){
                System.out.println("Apellido: " + resultado.getString("apellido"));
                System.out.println("Oficio: " + resultado.getString("oficio"));
                System.out.println("Salario: " + resultado.getDouble("salario"));    
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch(ClassNotFoundException en){
            en.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
