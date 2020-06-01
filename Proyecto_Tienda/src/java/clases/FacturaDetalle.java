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
public class FacturaDetalle {
    private String id;
    private String idFactura;
    private String codigoProducto;
    private String cantidad;
    private String valorUnitario;
    private String descuento;

    public FacturaDetalle() {
    }

    public FacturaDetalle(String id, String idFactura, String codigoProducto, String cantidad, String valorUnitario, String descuento) {
        this.id = id;
        this.idFactura = idFactura;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.descuento = descuento;
    }

    public FacturaDetalle(String id) {
        String cadenaSQL = "select idFactura, codigoProducto,cantidad,valorUnitario,descuento  from FacturaDetalle where id='" + id + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {      
         this.id = id;
         this.idFactura = resultado.getString("idFactura");
         this.codigoProducto = resultado.getString("codigoProducto");
         this.cantidad = resultado.getString("cantidad");
         this.valorUnitario = resultado.getString("valorUnitario");
         this.descuento = resultado.getString("descuento");
            }
    
    } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }
    }

    public String getId() {
       if(id==null) return ""; 
       else return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFactura() {
        if(idFactura==null) return ""; 
        else return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
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

    public String getValorUnitario() {
        if(valorUnitario==null) return ""; 
        else return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDescuento() {
        if(descuento==null) return ""; 
        else return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" + "id=" + id + ", idFactura=" + idFactura + ", codigoProducto=" + codigoProducto + ", cantidad=" + cantidad + ", valorUnitario=" + valorUnitario + ", descuento=" + descuento + '}';
    }
      
   public boolean guardar() {
        String cadenaSQL = "insert into FacturaDetalle(idFactura, codigoProducto,cantidad,valorUnitario,descuento)values('" + this.idFactura
                + "','" + this.codigoProducto + "','" + this.cantidad + "','" + this.valorUnitario + "','" + this.descuento + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        String cadenaSQL = "update FacturaDetalle set id='" + this.id + "',idFactura='" + this.idFactura+ "',codigoProducto='" + this.codigoProducto
                + "',cantidad='" + this.cantidad + "',valorUnitario='" + this.valorUnitario + "',descuento='" + this.descuento
                + "'where id='" + id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from FacturaDetalle where id='" + this.id + "'";
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
        String cadenaSQL = "Select idFactura, codigoProducto,cantidad,valorUnitario,descuento  from FacturaDetalle";
        return ConectorBD.consultar(cadenaSQL);
    }

    public static FacturaDetalle[] getListaEnObjetos(String filtro, String orden) {
        FacturaDetalle[] compras= null;
        ResultSet resultado = FacturaDetalle.getLista(filtro, orden);
        int cantidadCompras= 0;
        try {
            while (resultado.next()) {
                cantidadCompras++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadCompras > 0) {
                compras = new FacturaDetalle[cantidadCompras];
                resultado.first();
                int i = 0;
                do {
                    FacturaDetalle facturaCompra= new FacturaDetalle();
                    facturaCompra.setId(resultado.getString("id"));
                    facturaCompra.setIdFactura(resultado.getString("idFactura"));
                    facturaCompra.setCodigoProducto(resultado.getString("codigoProducto"));
                    facturaCompra.setCantidad(resultado.getString("cantidad"));
                    facturaCompra.setValorUnitario(resultado.getString("valorUnitario"));
                    facturaCompra.setDescuento(resultado.getString("descuento"));
                    
                    compras[i] = facturaCompra;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
    }

    public ResultSet getResultados() {
        return null;
    }

    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = FacturaDetalle.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
               /*if (predeterminado.equals(resultado.getString("fecha"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("fecha") + "'" + auxiliar + ">" + resultado.getString("fecha") + "</option>";
                if (predeterminado.equals(resultado.getString("numeroFactura"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("numeroFactura") + "'" + auxiliar + ">" + resultado.getString("numeroFactura") + "</option>";
                if (predeterminado.equals(resultado.getString("proveedor"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("proveedor") + "'" + auxiliar + ">" + resultado.getString("proveedor") + "</option>";*/
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
