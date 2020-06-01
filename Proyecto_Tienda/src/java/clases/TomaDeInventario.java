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
public class TomaDeInventario {
    private String id;
    private String identificacionAdministrador;
    private String fecha;

    public TomaDeInventario() {
    }

    public TomaDeInventario(String id, String identificacionAdministrador, String fecha) {
        this.id = id;
        this.identificacionAdministrador = identificacionAdministrador;
        this.fecha = fecha;
    }

    public TomaDeInventario(String id) {
         String cadenaSQL="select identificacionAdministrador, fecha from TomaDeInventario where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
        
        this.id = id;
        this.identificacionAdministrador=resultado.getString("identificacionAdministrador");
        this.fecha=resultado.getString("fecha");
                                
                }
        }  catch(SQLException ex)
        {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
        System.out.println("Error en la consulta, "+cadenaSQL+"\n"+ex.getMessage());
        }
    
        }

    public String getId() {
        if(id==null) return ""; 
        else return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificacionAdministrador() {
       if(identificacionAdministrador==null) return ""; 
       else return identificacionAdministrador;
    }

    public void setIdentificacionAdministrador(String identificacionAdministrador) {
        this.identificacionAdministrador = identificacionAdministrador;
    }

    public String getFecha() {
        if(fecha==null) return ""; 
        else return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "TomaDeInventario{" + '}';
    }

 public boolean guardar(){
        String cadenaSQL="insert into TomaDeInventario(identificacionAdministrador, fecha )values('"+this.identificacionAdministrador+"','"+this.fecha +"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update TomaDeInventario set id='"+ this.id +"',identificacionAdministrador='"+this.identificacionAdministrador
                +"',fecha='"+this.fecha +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from TomaDeInventario where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, identificacionAdministrador, fecha from TomaDeInventario";
         return ConectorBD.consultar(cadenaSQL);
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
        String cadenaSQL = "Select id, identificacionAdministrador, fecha from TomaDeInventario";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static TomaDeInventario[] getListaEnObjetos(String filtro, String orden) {
        TomaDeInventario[] tomaInventarios = null;
        ResultSet resultado = TomaDeInventario.getLista(filtro, orden);
        int cantidadTomaInventarios= 0;
         
        try {
            while (resultado.next()) {
                cantidadTomaInventarios++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadTomaInventarios > 0) {
                tomaInventarios = new TomaDeInventario[cantidadTomaInventarios];
                resultado.first();
                int i = 0;
                do {
                    TomaDeInventario toma = new TomaDeInventario();
                    toma.setId(resultado.getString("id"));
                    toma.setIdentificacionAdministrador(resultado.getString("identificacionAdministrador"));
                    toma.setFecha(resultado.getString("fecha"));
                    tomaInventarios[i] = toma;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TomaDeInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tomaInventarios;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = TomaDeInventario.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(TomaDeInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
