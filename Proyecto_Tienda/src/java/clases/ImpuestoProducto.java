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
public class ImpuestoProducto {
    private String id;
    private String codigoProducto;
    private String idImpuesto;

    public ImpuestoProducto() {
    }

    public ImpuestoProducto(String id, String codigoProducto, String idImpuesto) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.idImpuesto = idImpuesto;
    }

    public ImpuestoProducto(String id) {
       String cadenaSQL="select codigoProducto, idImpuesto from ImpuestoProducto where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
        
        this.id = id;
        this.codigoProducto=resultado.getString("codigoProducto");
        this.idImpuesto=resultado.getString("idImpuesto");
                                
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

    public String getIdImpuesto() {
        if(idImpuesto==null) return ""; 
        else return idImpuesto;
    }

    public void setIdImpuesto(String idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    @Override
    public String toString() {
        return "ImpuestoProducto{" + '}';
    }
    
    public boolean guardar(){
        String cadenaSQL="insert into ImpuestoProducto(codigoProducto, idImpuesto)values('"+this.codigoProducto+"','"+this.idImpuesto +"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update ImpuestoProducto set id='"+ this.id +"',codigoProducto='"+this.codigoProducto
                +"',idImpuesto='"+this.idImpuesto +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from ImpuestoProducto where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, codigoProducto, idImpuesto from ImpuestoProducto";
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
        String cadenaSQL ="Select id, codigoProducto, idImpuesto from ImpuestoProducto";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static ImpuestoProducto[] getListaEnObjetos(String filtro, String orden) {
        ImpuestoProducto[] impuestoProductos = null;
        ResultSet resultado = ImpuestoProducto.getLista(filtro, orden);
        int cantidadImpuestoProductos = 0;
         
        try {
            while (resultado.next()) {
                cantidadImpuestoProductos++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadImpuestoProductos > 0) {
                impuestoProductos = new ImpuestoProducto[cantidadImpuestoProductos];
                resultado.first();
                int i = 0;
                do {
                   ImpuestoProducto impuestoProducto = new ImpuestoProducto();
                    impuestoProducto.setId(resultado.getString("id"));
                    impuestoProducto.setCodigoProducto(resultado.getString("codigoProducto"));
                    impuestoProducto.setIdImpuesto(resultado.getString("idImpuesto"));
                    impuestoProductos[i] = impuestoProducto;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImpuestoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return impuestoProductos;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = ImpuestoProducto.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImpuestoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
    


        
    
    
    
