/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesGenericas;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galo
 */
public class ConectorBD {
    private String servidor;
    private String puerto;
    private String usuario;
    private String clave;
    private String baseDatos;

    private Connection conexion;//atributo que lleva la conexión de la base de datos
    
    public ConectorBD() {
        Properties propiedades=new Properties();
        try {
            //propiedades.load(new FileInputStream("configuracion.ini"));//toma los valores del archivo y los carga en propiedades
            propiedades.load(this.getClass().getClassLoader().getResourceAsStream("configuracion.ini"));
            servidor=propiedades.getProperty("servidor");//al atributo servidor le asigna el valor configurado en la llave servidor
            puerto=propiedades.getProperty("puerto");
            usuario=propiedades.getProperty("usuario");
            clave=propiedades.getProperty("clave");
            baseDatos=propiedades.getProperty("baseDatos");
        } catch (IOException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cargar el archivo de configuración. "+ ex.getMessage());
        }
        /*servidor="localhost";//al atributo servidor le asigna el valor configurado en la llave servidor
        puerto="3306";
        usuario="sena";
        clave="Sena2019";
        baseDatos="votaciones"; */       
    }
    
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://"+servidor+":"+puerto+"/"+baseDatos,usuario,clave);
            System.out.println("Conectado");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al ubicar el controlador de la base de datos. "+ ex.getMessage());
        } catch (SQLException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al conectarse a la base de datos. "+ex.getMessage());
        }
    }
    
    public void desconectar(){
        try {
            conexion.close();
            System.out.println("Desconectado");
        } catch (SQLException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al desconectarse de la base de datos");
        }
    }

    public Connection getConexion() {
        return conexion;
    }
                
    public static ResultSet consultar(String cadenaSQL){//ejecuta select..
        ConectorBD conector=new ConectorBD();
        conector.conectar();
        try {
            PreparedStatement sentencia=conector.conexion.prepareStatement(cadenaSQL, ResultSet.TYPE_SCROLL_SENSITIVE,0);//prepara la cadena SQL
            ResultSet resultado=sentencia.executeQuery();//ejecuta la cadenaSQL
            //conector.desconectar();//??
            return resultado;            
        } catch (SQLException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al ejecutar la cadena SQL. "+cadenaSQL+"\n"+ex.getMessage());
            conector.desconectar();
            return null;
        }        
    }
    
    public static boolean ejecutarQuery(String cadenaSQL){//ejecuta insert, update, delete
        ConectorBD conector=new ConectorBD();
        conector.conectar();
        try {
            PreparedStatement sentencia=conector.conexion.prepareStatement(cadenaSQL);
            boolean resultado=sentencia.execute();
            conector.desconectar();//??
            return resultado;
        } catch (SQLException ex) {
            //Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al ejecutar la cadena SQL. "+cadenaSQL+"\n"+ex.getMessage());
            conector.desconectar();
            return false;            
        }        
    }
}










