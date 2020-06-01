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
public class FacturaDetalleImpuesto {
    private String id;
    private String idFacturaDetalle;
    private String idImpuesto;
    private String porcentaje;

    public FacturaDetalleImpuesto() {
    }

    public FacturaDetalleImpuesto(String id, String idFacturaDetalle, String idImpuesto, String porcentaje) {
        this.id = id;
        this.idFacturaDetalle = idFacturaDetalle;
        this.idImpuesto = idImpuesto;
        this.porcentaje = porcentaje;
    }

    public FacturaDetalleImpuesto(String id) {
        String cadenaSQL="select idFacturaDetalle, idImpuesto, porcentaje from FacturaDetalleImpuesto where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
                this.id = id;
                this.idFacturaDetalle=resultado.getString("idFacturaDetalle");
                this.idImpuesto=resultado.getString("idImpuesto");
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

    public String getIdFacturaDetalle() {
        if(idFacturaDetalle==null) return ""; 
        else return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(String idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public String getIdImpuesto() {
        if(idImpuesto==null) return ""; 
        else return idImpuesto;
    }

    public void setIdImpuesto(String idImpuesto) {
        this.idImpuesto = idImpuesto;
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
        return "FacturaDetalleImpuesto{" + '}';
    }
    
    
public boolean guardar(){
        String cadenaSQL="insert into FacturaDetalleImpuesto(idFacturaDetalle, idImpuesto, porcentaje) values('"+this.idFacturaDetalle+"','"+this.idImpuesto+
                "','"+ this.porcentaje+"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update FacturaDetalleImpuesto set id='"+ this.id +"',idFacturaDetalle='"+this.idFacturaDetalle
                +"',idImpuesto='"+this.idImpuesto+"',porcentaje='"+this.porcentaje +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from FacturaDetalleImpuesto where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, idFacturaDetalle, idImpuesto, porcentaje from FacturaDetalleImpuesto";
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
        String cadenaSQL ="Select id, idFacturaDetalle, idImpuesto, porcentaje from FacturaDetalleImpuesto";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static FacturaDetalleImpuesto[] getListaEnObjetos(String filtro, String orden) {
        FacturaDetalleImpuesto[] detalleImpuestos = null;
        ResultSet resultado = FacturaDetalleImpuesto.getLista(filtro, orden);
        int cantidadDetalleImpuestos = 0;
         
        try {
            while (resultado.next()) {
                cantidadDetalleImpuestos++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadDetalleImpuestos > 0) {
                detalleImpuestos = new FacturaDetalleImpuesto[cantidadDetalleImpuestos];
                resultado.first();
                int i = 0;
                do {
                    FacturaDetalleImpuesto detalleImpuesto = new FacturaDetalleImpuesto();
                    detalleImpuesto.setId(resultado.getString("id"));
                    detalleImpuesto.setIdFacturaDetalle(resultado.getString("idFacturaDetalle"));
                    detalleImpuesto.setIdImpuesto(resultado.getString("idImpuesto"));
                    detalleImpuesto.setPorcentaje(resultado.getString("porcentaje"));
                    detalleImpuestos[i] = detalleImpuesto;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDetalleImpuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalleImpuestos;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = FacturaDetalleImpuesto.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaDetalleImpuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
    
