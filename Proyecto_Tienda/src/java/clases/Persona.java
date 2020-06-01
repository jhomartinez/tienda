/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Persona {

    private String identificacion;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String clave;
    private String tipo;

    public Persona() {
    }

    public Persona(String identificacion, String nombre, String apellido, String direccion, String telefono, String email, String clave, String tipo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.tipo = tipo;
    }

    public Persona(String identificacion) {
        String cadenaSQL = "select nombre,apellido,direccion,telefono,email,clave,tipo from Persona where identificacion='" + identificacion + "'";
        System.out.println("Consulta["+ cadenaSQL+"]");
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                System.out.println("Persona["+ resultado.toString()+"]");
                this.identificacion = identificacion;
                this.nombre = resultado.getString("nombre");
                this.apellido = resultado.getString("apellido");
                this.direccion = resultado.getString("direccion");
                this.telefono = resultado.getString("telefono");
                this.email = resultado.getString("email");
                this.clave = resultado.getString("clave");
                this.tipo = resultado.getString("tipo");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }

    }

    public String getIdentificacion() {
        if (identificacion == null) {
            return "";
        } else {
            return identificacion;
        }
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getApellido() {
        if (apellido == null) {
            return "";
        } else {
            return apellido;
        }
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEmail() {
        if (email == null) {
            return "";
        } else {
            return email;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        if (clave == null) {
            return "";
        } else {
            return clave;
        }
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        if (tipo == null) {
            return "";
        } else {
            return tipo;
        }
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

    public boolean guardar() {
        String clave = this.clave;
        if (clave.length() < 32) {
            clave = "md5('" + clave + "')";
        } else {
            clave = "'" + clave + "'";
        }
        String cadenaSQL = "insert into Persona(identificacion,nombre,apellido,direccion,telefono,email,clave,tipo)values('" + this.identificacion
                + "','" + this.nombre + "','" + this.apellido + "','" + this.direccion + "','" + this.telefono + "','" + this.email + "'," + clave + ",'" + this.tipo + "')";
        System.out.println("Query:["+ cadenaSQL +"]");
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar(String identificacionAnterior) {
        String clave = this.clave;
        if (clave.length() < 32) {
            this.clave = "md5('" + clave + "')";
        } else {
            clave = "'" + clave + "'";
        }
        String cadenaSQL = "update Persona set identificacion='" + this.identificacion + "',nombre='" + this.nombre
                + "',apellido='" + this.apellido + "',direccion='" + this.direccion + "',telefono='" + this.telefono
                + "',email='" + this.email +"',clave='" + clave + "',tipo='" + this.tipo + "' where identificacion='" + identificacion + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Persona where identificacion='" + this.identificacion + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public static ResultSet getLista(String filtro, String orden) {
        if (filtro == null) {
            filtro = "";
        } else {
            filtro = " where " + filtro;
        }
        if (orden == null) {
            orden = "";
        } else {
            orden = " order by " + orden;
        }
        String cadenaSQL = "Select identificacion, nombre, apellido, direccion, telefono, email, clave, tipo from Persona";
        return ConectorBD.consultar(cadenaSQL);
    }

    public static Persona[] getListaEnObjetos(String filtro, String orden) {
        Persona[] personas = null;
        ResultSet resultado = Persona.getLista(filtro, orden);
        int cantidadPersonas = 0;
        try {
            while (resultado.next()) {
                cantidadPersonas++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadPersonas > 0) {
                personas = new Persona[cantidadPersonas];
                resultado.first();
                int i = 0;
                do {
                    Persona persona = new Persona();
                    persona.setIdentificacion(resultado.getString("ididentificacion"));
                    persona.setNombre(resultado.getString("nombre"));
                    persona.setApellido(resultado.getString("apellido"));
                    persona.setDireccion(resultado.getString("direccion"));
                    persona.setTelefono(resultado.getString("telefono"));
                    persona.setEmail(resultado.getString("email"));
                    persona.setClave(resultado.getString("clave"));
                    persona.setTipo(resultado.getString("tipo"));

                    personas[i] = persona;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }

    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = Persona.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("nombre") + "</option>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static Persona validar(String usuario, String clave) {
        String cadenaSQL = "select nombre,apellido,direccion, telefono,email,clave,tipo from Persona where identificacion='" + usuario + "'and clave=md5('" + clave + "')";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        Persona persona = null;
        try {
            if (resultado.next()) {
                persona = new Persona();
                persona.setIdentificacion(usuario);
                persona.setNombre(resultado.getString("nombre"));
                persona.setApellido(resultado.getString("apellido"));
                persona.setDireccion(resultado.getString("direccion"));
                persona.setTelefono(resultado.getString("telefono"));
                persona.setEmail(resultado.getString("email"));
                persona.setClave(resultado.getString("clave"));
                persona.setTipo(resultado.getString("tipo"));
            }
        } catch (SQLException ex) {

            System.out.println("Error al validar el usuario." + cadenaSQL + "\n" + ex.getMessage());
        }
        return persona;
    }

}
