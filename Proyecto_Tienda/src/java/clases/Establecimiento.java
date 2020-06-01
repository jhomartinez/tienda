/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class Establecimiento {

    private String nit;
    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;
    private String telefono;
    private String confirmacionPedido;
    private Persona identificacionAdministrador;

    public Establecimiento() {
    }

    public Establecimiento(String nit, String nombre, String direccion, String latitud, String longitud, String telefono, String confirmacionPedido, Persona identificacionAdministrador) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
        this.confirmacionPedido = confirmacionPedido;
        this.identificacionAdministrador = identificacionAdministrador;
    }

    public Establecimiento(String nit) {
        String cadenaSQL = "select nombre,direccion,latitud,longitud,telefono,confirmacionPedido,identificacionAdministrador from Establecimiento where nit='" + nit + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.nit = nit;
                this.nombre = resultado.getString("nombre");
                this.direccion = resultado.getString("direccion");
                this.latitud = resultado.getString("latitud");
                this.longitud = resultado.getString("longitud");
                this.telefono = resultado.getString("telefono");
                this.confirmacionPedido = resultado.getString("confirmacionPedido");
                this.identificacionAdministrador = new Persona(resultado.getString("identificacionAdministrador"));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }

    }
    
    public List<Establecimiento> getListaEstablecimientos(Persona usuario){
        StringBuilder consulta = new StringBuilder();
        List<Establecimiento> lista = new ArrayList<>();
        consulta.append("select * from Establecimiento where identificacionAdministrador='"+ usuario.getIdentificacion() +"'");
        try {
            ResultSet resultado = ConectorBD.consultar(consulta.toString());
            if(resultado == null){
                throw new NullPointerException("No hay establecimientos para el usuario");
            }
            lista = crearEstablecimiento(resultado);
        } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + "\n" + ex.getMessage());
        } catch (NullPointerException e){
            lista = null;
        }
        return lista;
    }
    
    public List<Establecimiento> crearEstablecimiento(ResultSet consulta) throws SQLException, NullPointerException{
        List<Establecimiento> lista = new ArrayList<>();
        if (consulta.next()) {
            Establecimiento est = new Establecimiento(consulta.getString("nit"),
                    consulta.getString("nombre"),
                    consulta.getString("direccion"),
                    consulta.getString("latitud"),
                    consulta.getString("longitud"),
                    consulta.getString("telefono"),
                    consulta.getString("confirmacionPedido"),
                    new Persona(consulta.getString("identificacionAdministrador")));
            lista.add(est);
        }
        return lista;
    }

    public String getNit() {
        if (nit == null) {
            return "";
        } else {
            return nit;
        }
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        if (nombre == null) {
            return "";
        } else {
            return nombre;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        if (direccion == null) {
            return "";
        } else {
            return direccion;
        }
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        if (latitud == null) {
            return "";
        } else {
            return latitud;
        }
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        if (longitud == null) {
            return "";
        } else {
            return longitud;
        }
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        if (telefono == null) {
            return "";
        } else {
            return telefono;
        }
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConfirmacionPedido() {
        if (confirmacionPedido == null) {
            return "";
        } else {
            return confirmacionPedido;
        }
    }

    public void setConfirmacionPedido(String confirmacionPedido) {
        this.confirmacionPedido = confirmacionPedido;
    }

    public Persona getIdentificacionAdministrador() {
        return this.identificacionAdministrador;
    }

    public void setIdentificacionAdministrador(Persona identificacionAdministrador) {
        this.identificacionAdministrador = identificacionAdministrador;
    }

    @Override
    public String toString() {
        return "Establecimiento{" + '}';
    }

    public boolean guardar() {
        StringBuffer cadenaSQL = new StringBuffer();
        cadenaSQL.append("insert into Establecimiento(nit,nombre,direccion");
        if (this.latitud != null) {
            cadenaSQL.append(",latitud");
        }
        if (this.longitud != null) {
            cadenaSQL.append(",longitud");
        }
        cadenaSQL.append(",telefono,confirmacionPedido,identificacionAdministrador)values('" + this.nit + "','" + this.nombre + "','" + this.direccion + "'");
        if (this.latitud != null) {
            cadenaSQL.append("," + this.latitud);
        }
        if (this.longitud != null) {
            cadenaSQL.append("," + this.longitud);
        }
        cadenaSQL.append(",'" + this.telefono + "','" + this.confirmacionPedido + "','" + this.identificacionAdministrador + "')");
        return ConectorBD.ejecutarQuery(cadenaSQL.toString());
    }

    public boolean modificar() {
        String cadenaSQL = "update Establecimiento set nit='" + this.nit + "',nombre='" + this.nombre
                + "',direccion='" + this.direccion + "',latitud='" + this.latitud + "',longitud='" + this.longitud
                + "',telefono='" + this.telefono + "',confirmacionPedido='" + this.confirmacionPedido + "',identificacionAdministrador='" + this.identificacionAdministrador.getIdentificacion() + "'where nit='" + nit + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Establecimiento where nit='" + this.nit + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista() {
        String cadenaSQL = "Select nit, nombre, direccion, latitud, longitud, telefono, confirmacionPedido, identificacionAdministrador from Establecimiento";
        return ConectorBD.consultar(cadenaSQL);
    }

    public ResultSet getResultados() {
        return null;
    }

}
