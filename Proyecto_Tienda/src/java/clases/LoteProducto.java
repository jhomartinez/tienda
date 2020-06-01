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
public class LoteProducto {
    private String id;
    private String fechaElaboracion;
    private String fechaVencimiento;
    private String codigoProducto;
    private String stock;
    private String idCompra;
    private String lote;

    public LoteProducto() {
    }

    public LoteProducto(String id, String fechaElaboracion, String fechaVencimiento, String codigoProducto, String stock, String idCompra, String lote) {
        this.id = id;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoProducto = codigoProducto;
        this.stock = stock;
        this.idCompra = idCompra;
        this.lote = lote;
    }

    public LoteProducto(String id) {
        String cadenaSQL = "select fechaElaboracion, fechaVencimiento,codigoProducto,stock,idCompra, lote from LoteProducto where id='" + id + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
        this.id = id;
         this.fechaElaboracion = resultado.getString("fechaElaboracion");
         this.fechaVencimiento = resultado.getString("fechaVencimiento");
         this.codigoProducto = resultado.getString("codigoProducto");
         this.stock = resultado.getString("stock");
         this.idCompra = resultado.getString("idCompra");
         this.lote = resultado.getString("lote");
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

    public String getFechaElaboracion() {
        if(fechaElaboracion==null) return ""; 
        else return fechaElaboracion;
    }

    public void setFechaElaboracion(String fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getFechaVencimiento() {
        if(fechaVencimiento==null) return ""; 
        else return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoProducto() {
        if(codigoProducto==null) return ""; 
        else return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getStock() {
        if(stock==null) return ""; 
        else return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getIdCompra() {
        if(idCompra==null) return ""; 
        else return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getLote() {
        if(lote==null) return ""; 
        else return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "LoteProducto{" + '}';
    }
    
    public boolean guardar() {
        String cadenaSQL = "insert into LoteProducto(id,fechaElaboracion, fechaVencimiento,codigoProducto,stock,idCompra, lote)values('" + this.id
                + "','" + this.fechaElaboracion + "','" + this.fechaVencimiento + "','" + this.codigoProducto + "','" + this.stock + "','" + this.idCompra + "','" + this.lote + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        String cadenaSQL = "update LoteProducto set id='" + this.id + "',fechaElaboracion='" + this.fechaElaboracion
                + "',fechaVencimiento='" + this.fechaVencimiento + "',codigoProducto='" + this.codigoProducto + "',stock='" + this.stock
                + "',idCompra='" + this.idCompra + "',lote='" + this.lote + "'where id='" + id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from LoteProducto where id='" + this.id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public static ResultSet getLista() {
        String cadenaSQL = "Select id,fechaElaboracion, fechaVencimiento,codigoProducto,stock,idCompra, lote from LoteProducto";
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
        String cadenaSQL = "Select id,fechaElaboracion, fechaVencimiento,codigoProducto,stock,idCompra, lote from LoteProducto";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static LoteProducto[] getListaEnObjetos(String filtro, String orden) {
        LoteProducto[] lotes = null;
        ResultSet resultado = LoteProducto.getLista(filtro, orden);
        int cantidadLotes = 0;
         
        try {
            while (resultado.next()) {
                cantidadLotes++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadLotes > 0) {
                lotes = new LoteProducto[cantidadLotes];
                resultado.first();
                int i = 0;
                do {
                    LoteProducto lote = new LoteProducto();
                    lote.setId(resultado.getString("id"));
                    lote.setFechaElaboracion(resultado.getString("fechaElaboracion"));
                    lote.setFechaVencimiento(resultado.getString("fechaVencimiento"));
                    lote.setCodigoProducto(resultado.getString("codigoProducto"));
                    lote.setStock(resultado.getString("stock"));
                    lote.setIdCompra(resultado.getString("idCompra"));
                    lote.setLote(resultado.getString("lote"));
                    lotes[i] = lote;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoteProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lotes;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = LoteProducto.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoteProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
    


        
    
    
    
