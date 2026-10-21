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
public class Ejercicio02 {
    public static void main(String[] args) {
        selectMySQL();
    }
    //Metodo para entrar y leer la BD
    public static void selectMySQL() {
        try {
            //Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establecemos la conexión con la BD
            Connection conexion
                    = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");

            //Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos";
            ResultSet resultado = sentencia.executeQuery(sql);

            //Recorremos el resultado para visualizar cada fila
            //Se hace un bucle mientras haya registros, se van visualizando
            while (resultado.next()) {
                System.out.println(resultado.getInt(1)
                        + " " + resultado.getString(2)
                        + " " + resultado.getString(3));
            }

            resultado.close(); //Cerrar ResultSet
            sentencia.close(); //Cerrar Statement
            conexion.close(); //Cerrar conexión

        } catch (ClassNotFoundException en) {
            en.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
