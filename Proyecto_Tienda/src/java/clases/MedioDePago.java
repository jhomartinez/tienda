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
public class MedioDePago {
    private String id;
    private String nombre;

    public MedioDePago() {
    }

    public MedioDePago(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MedioDePago(String id) {
        String cadenaSQL="select nombre from MedioDePago where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
        
        this.id = id;
        this.nombre=resultado.getString("nombre");
       
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

    public String getNombre() {
       if(nombre==null) return ""; 
       else return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "MedioDePago{" + '}';
    }
    
    public boolean guardar(){
        String cadenaSQL="insert into MedioDePago(nombre)values('"+this.nombre +"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update MedioDePago set id='"+ this.id +"',nombre='"+this.nombre
                +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from MedioDePago where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, nombre from MedioDePago";
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
        String cadenaSQL = "Select id, nombre from MedioDePago";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static MedioDePago[] getListaEnObjetos(String filtro, String orden) {
        MedioDePago[] medios = null;
        ResultSet resultado = MedioDePago.getLista(filtro, orden);
        int cantidadMedios = 0;
         
        try {
            while (resultado.next()) {
                cantidadMedios++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadMedios > 0) {
                medios = new MedioDePago[cantidadMedios];
                resultado.first();
                int i = 0;
                do {
                    MedioDePago medioDePago = new MedioDePago();
                    medioDePago.setId(resultado.getString("id"));
                    medioDePago.setNombre(resultado.getString("nombre"));
                    medios[i] = medioDePago;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedioDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medios;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = MedioDePago.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedioDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    