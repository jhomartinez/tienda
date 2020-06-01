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
public class Impuesto {
    private String id;
    private String nombre;
    private String porcentaje;

    public Impuesto() {
    }

    public Impuesto(String id, String nombre, String porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public Impuesto(String id) {
       String cadenaSQL="select nombre, porcentaje from Impuesto where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
        
        this.id = id;
        this.nombre=resultado.getString("nombre");
        this.porcentaje=resultado.getString("porcentaje");
                                
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

    public String getPorcentaje() {
        if(porcentaje==null) return ""; 
        else return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Impuesto{" + '}';
    }
    
   public boolean guardar(){
        String cadenaSQL="insert into Impuesto(nombre, porcentaje )values('"+this.nombre+"','"+this.porcentaje +"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update Impuesto set id='"+ this.id +"',nombre='"+this.nombre
                +"',porcentaje='"+this.porcentaje +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from Impuesto where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, nombre, porcentaje from Impuesto";
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
        String cadenaSQL ="Select id, nombre, porcentaje from Impuesto";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static Impuesto[] getListaEnObjetos(String filtro, String orden) {
        Impuesto[] impuestos = null;
        ResultSet resultado = Impuesto.getLista(filtro, orden);
        int cantidadImpuestos = 0;
         
        try {
            while (resultado.next()) {
                cantidadImpuestos++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadImpuestos > 0) {
                impuestos = new Impuesto[cantidadImpuestos];
                resultado.first();
                int i = 0;
                do {
                    Impuesto impuesto = new Impuesto();
                    impuesto.setId(resultado.getString("id"));
                    impuesto.setNombre(resultado.getString("nombre"));
                    impuesto.setPorcentaje(resultado.getString("porcentaje"));
                    impuestos[i] = impuesto;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return impuestos;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = Impuesto.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
    


        
    
    
    
