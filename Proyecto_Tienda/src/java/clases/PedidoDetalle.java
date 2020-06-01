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
public class PedidoDetalle {
    private String id;
    private String codigoPedido;
    private String codigoProducto;
    private String cantidad;

    public PedidoDetalle() {
    }

    public PedidoDetalle(String id, String codigoPedido, String codigoProducto, String cantidad) {
        this.id = id;
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }

    public PedidoDetalle(String id) {
         String cadenaSQL="select codigoPedido, codigoProducto, cantidad from PedidoDetalle where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
                this.id = id;
                this.codigoPedido=resultado.getString("codigoPedido");
                this.codigoProducto=resultado.getString("codigoProducto");
                this.cantidad=resultado.getString("cantidad");
                
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

    public String getCodigoPedido() {
       if(codigoPedido==null) return ""; 
       else return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getCodigoProducto() {
        if(codigoProducto==null) return ""; 
        else return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCantidad() {
        if(cantidad==null) return ""; 
        else return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PedidoDetalle{" + '}';
    }
    
    public boolean guardar(){
        String cadenaSQL="insert into PedidoDetalle(codigoPedido, codigoProducto, cantidad)values('"+this.codigoPedido+"','"+this.codigoProducto+
                "','"+ this.cantidad+"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update PedidoDetalle set id='"+ this.id +"',codigoPedido='"+this.codigoPedido
                +"',codigoProducto='"+this.codigoProducto+"',cantidad='"+this.cantidad +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from PedidoDetalle where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, codigoPedido, codigoProducto, cantidad from PedidoDetalle";
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
        String cadenaSQL = "Select id, codigoPedido, codigoProducto, cantidad from PedidoDetalle";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static PedidoDetalle[] getListaEnObjetos(String filtro, String orden) {
        PedidoDetalle[] pedidosD = null;
        ResultSet resultado = PedidoDetalle.getLista(filtro, orden);
        int cantidadPedidosD= 0;
         
        try {
            while (resultado.next()) {
                cantidadPedidosD++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadPedidosD > 0) {
                pedidosD = new PedidoDetalle[cantidadPedidosD];
                resultado.first();
                int i = 0;
                do {
                    PedidoDetalle pedidoDetalle = new PedidoDetalle();
                    pedidoDetalle.setId(resultado.getString("id"));
                    pedidoDetalle.setCodigoPedido(resultado.getString("codigoPedido"));
                    pedidoDetalle.setCodigoProducto(resultado.getString("codigoProducto"));
                    pedidoDetalle.setCantidad(resultado.getString("cantidad"));
                    
                    pedidosD[i] = pedidoDetalle;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidosD;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = PedidoDetalle.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
    
    
    


