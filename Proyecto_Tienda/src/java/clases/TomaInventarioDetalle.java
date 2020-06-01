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
public class TomaInventarioDetalle {
    private String id;
    private String codigoProducto;
    private String idTomaDeInventario;
    private String cantidadEncontrada;
    private String cantidadSistema;

    public TomaInventarioDetalle() {
    }

    public TomaInventarioDetalle(String id, String codigoProducto, String idTomaDeInventario, String cantidadEncontrada, String cantidadSistema) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.idTomaDeInventario = idTomaDeInventario;
        this.cantidadEncontrada = cantidadEncontrada;
        this.cantidadSistema = cantidadSistema;
    }

    public TomaInventarioDetalle(String id) {
        String cadenaSQL="select codigoProducto, idTomaDeInventario, cantidadEncontrada, cantidadSistema from TomaInventarioDetalle where id='"+id+"'";
        ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
                this.id = id;
                this.codigoProducto=resultado.getString("codigoProducto");
                this.idTomaDeInventario=resultado.getString("idTomaDeInventario");
                this.cantidadEncontrada=resultado.getString("cantidadEncontrada");
                this.cantidadSistema=resultado.getString("cantidadSistema");
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

    public String getCodigoProducto() {
        if(codigoProducto==null) return ""; 
        else return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getIdTomaDeInventario() {
        if(idTomaDeInventario==null) return ""; 
        else return idTomaDeInventario;
    }

    public void setIdTomaDeInventario(String idTomaInventario) {
        this.idTomaDeInventario = idTomaInventario;
    }

    public String getCantidadEncontrada() {
       if(cantidadEncontrada==null) return ""; 
       else return cantidadEncontrada;
    }

    public void setCantidadEncontrada(String cantidadEncontrada) {
        this.cantidadEncontrada = cantidadEncontrada;
    }

    public String getCantidadSistema() {
        if(cantidadSistema==null) return ""; 
        else return cantidadSistema;
    }

    public void setCantidadSistema(String cantidadSistema) {
        this.cantidadSistema = cantidadSistema;
    }

    @Override
    public String toString() {
        return "TomaInventarioDetalle{" + '}';
    }
    
     public boolean guardar(){
        String cadenaSQL="insert into TomaInventarioDetalle(codigoProducto, idTomaDeInventario, cantidadEncontrada, cantidadSistema)values('"+this.codigoProducto+"','"+ this.idTomaDeInventario+"','"+this.cantidadEncontrada+
                "','"+ this.cantidadSistema+"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update TomaInventarioDetalle set id='"+ this.id +"',codigoProducto='"+this.codigoProducto
                +"',idTomaDeInventario='"+this.idTomaDeInventario+"',cantidadEncontrada='"+this.cantidadEncontrada+"',cantidadSistema='"+this.cantidadSistema+"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from TomaInventarioDetalle where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id,codigoProducto, idTomaDeInventario, cantidadEncontrada, cantidadSistema from TomaInventarioDetalle";
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
        String cadenaSQL = "Select id,codigoProducto, idTomaDeInventario, cantidadEncontrada, cantidadSistema from TomaInventarioDetalle";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static TomaInventarioDetalle[] getListaEnObjetos(String filtro, String orden) {
        TomaInventarioDetalle[] tomaInventariosDetalle = null;
        ResultSet resultado = TomaInventarioDetalle.getLista(filtro, orden);
        int cantidadTomaInventariosDetalle = 0;
         
        try {
            while (resultado.next()) {
                cantidadTomaInventariosDetalle++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadTomaInventariosDetalle > 0) {
                tomaInventariosDetalle = new TomaInventarioDetalle[cantidadTomaInventariosDetalle];
                resultado.first();
                int i = 0;
                do {
                    TomaInventarioDetalle tomaInventarioD = new TomaInventarioDetalle();
                    tomaInventarioD.setId(resultado.getString("id"));
                    tomaInventarioD.setCodigoProducto(resultado.getString("codigoProducto"));
                    tomaInventarioD.setIdTomaDeInventario(resultado.getString("idTomaDeInventario"));
                    tomaInventarioD.setCantidadEncontrada(resultado.getString("cantidadEncontrada"));
                    tomaInventarioD.setCantidadSistema(resultado.getString("cantidadSistema"));
                    tomaInventariosDetalle[i] = tomaInventarioD;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TomaInventarioDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tomaInventariosDetalle;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = TomaInventarioDetalle.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(TomaInventarioDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    

