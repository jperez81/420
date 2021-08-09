/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;

/**
 *
 * @author José Pérez 
 */
public class ConexionBD {
        //Esta clase contedra los datos de conexion al servidor con la que configuramos la base de datos 
   public static String base = "420";
    public static String url = "jdbc:mysql://localhost:3306/"+base;
    public static String usuario = "root";
    public static String contraseña = "mora2017";
//    
//        CONFIGURACION DEL SERVIDOR PARA CLIENTES
//    public static String base = "querico";
//    public static String url = "jdbc:mysql://192.168.10.80:3306/"+base;
//    public static String usuario = "root2";
//    public static String contraseña = "mora2017";
//    
     
    //micasa
//    public static String base = "querico";
//    public static String url = "jdbc:mysql://192.168.1.80:3306/"+base;
//    public static String usuario = "root2";
//    public static String contraseña = "mora2017";

    

    MetodosSql met = new  MetodosSql();
    
    public static String clase = "com.mysql.jdbc.Driver";

    public static Connection conectar() { //METODO PARA CONECTAR A LA BASE DE DATOS LOCAL

        Connection conexion = null; 
        try {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion Exitosa "); 

        } catch (Exception e) {
            System.out.println("Error de conexion " + e);

        }
        return conexion; //RETORNO DE LA CONEXION
 

    }   

   
}


