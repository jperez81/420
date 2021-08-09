/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import claseConectar.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author José Pérez
 */
public class MetodosSql {

    //Esta clase contiene todos los metodos de ( busqueda y guardado de Datos )
    // debemos instanciar la clase ConexionBD para establecer conexion con nuestra base de datos
    public static ConexionBD conexion = new ConexionBD();//instanciamos la clase para conectarnos a la base de datos
    public static PreparedStatement sentencia_preparada; //creamos una sentencia preparada 
    public static ResultSet resultado;//esto va a ser el resultado de las consulta que realicemos
    public static String sql;//
    public static int resultado_numero = 0;

 conectar cc = new conectar();
    Connection cn = cc.conexion();

    



    public String buscarNombre(String usuario) { //METODO PARA BUSCAR NOMBRE EN LA BASE DE DATOS POR MEDIO DE SU CORREO
        String busqueda_nombre = null;
        Connection conexion = null;

        try {
            conexion = ConexionBD.conectar();
            String sentencia_buscar = ("SELECT nombre,apellido FROM usuarios WHERE usuario = '" + usuario + "'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery(); //EJECUTA LA SENTANCIA PREPARADA DE BUSCAR Y VALIDAR 
            if (resultado.next()) { //SI RESULTADO ES EL SIGUIENTE: TRAEME ESE NOMBRE Y APELLIDO
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                busqueda_nombre = (nombre + " " + apellido);

            }
            conexion.close(); //CERRAMOS CONEXION

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return busqueda_nombre;
    }

    public String BuscarUsuarioRegistrado(String usuario, String contraseña) { //METODO PARA USUARIO REGISTRADO

        String busqueda_usuario = null;
        Connection conexion = null;

        try {
            conexion = ConexionBD.conectar(); //INSTANCIAR CONEXION DE "CONEXIONBD
            String sentencia_buscar_usuario = ("SELECT nombre,usuario, contraseña FROM usuarios WHERE usuario = '" + usuario + "'&& contraseña = '" + contraseña + "'");
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar_usuario);

            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                busqueda_usuario = "Usuario Registrado!";
            } else {
                busqueda_usuario = " USUARIO INVALIDO!\n"
                        + "Contacte al Administrador";

            }
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error no se encuentra usuario" + e);
        }
        return busqueda_usuario;

    }

    //CREO ESTE METODO PARA QUE BUSQUE CONTRASEÑA EN BASE DE DATOS PARA VALIDAR USUARIOS
    public String ValidarModulo(String contraseña) { //METODO PARA USUARIO REGISTRADO

        String busqueda_usuario = null;
        Connection conexion = null;

        try {
            conexion = ConexionBD.conectar(); //INSTANCIAR CONEXION DE "CONEXIONBD
            String validar = ("SELECT nombre,usuario, contraseña FROM usuarios WHERE contraseña = '" + contraseña + "'");
            sentencia_preparada = conexion.prepareStatement(validar);

            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                busqueda_usuario = "Usuario Registrado!";
            } else {
                busqueda_usuario = " USUARIO INVALIDO!\n"
                        + "Contacte al Administrador";

            }
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error no se encuentra usuario" + e);
        }
        return busqueda_usuario;

    }

}
