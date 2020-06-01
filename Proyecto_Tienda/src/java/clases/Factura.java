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
public class Factura {
    private String id;
    private String numeroFactura;
    private String fecha;
    private String descuento;
    private String idPedido;
    private String idMedioDePago;
    private String tipo;
    private String identificacionT;

    public Factura() {
    }

    public Factura(String id, String numeroFactura, String fecha, String descuento, String idPedido, String idMedioDePago, String tipo, String identificacionT) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.descuento = descuento;
        this.idPedido = idPedido;
        this.idMedioDePago = idMedioDePago;
        this.tipo = tipo;
        this.identificacionT = identificacionT;
    }

    public Factura(String id) {
        String cadenaSQL = "select numeroFactura,fecha,descuento,idPedido,idMedioDePago,tipo,identificacionT from Factura where id='" + id + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id = id;
                this.numeroFactura = resultado.getString("numeroFactura");
                this.fecha = resultado.getString("fecha");
                this.descuento = resultado.getString("descuento");
                this.idPedido = resultado.getString("idPedido");
                this.idMedioDePago = resultado.getString("idMedioDePago");
                this.tipo = resultado.getString("tipo");
                this.identificacionT = resultado.getString("identificacionT");
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

    public String getNumeroFactura() {
         if(numeroFactura==null) return ""; 
         else return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFecha() {
      if(fecha==null) return ""; 
      else return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescuento() {
        if(descuento==null) return ""; 
        else return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getIdPedido() {
      if(idPedido==null) return ""; 
      else return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdMedioDePago() {
        if(idMedioDePago==null) return ""; 
        else return idMedioDePago;
    }

    public void setIdMedioDePago(String idMedioDePago) {
        this.idMedioDePago = idMedioDePago;
    }

    public String getTipo() {
         if(tipo==null) return ""; 
         else return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacionT() {
        if(identificacionT==null) return ""; 
        else return identificacionT;
    }

    public void setIdentificacionT(String identificacionT) {
        this.identificacionT = identificacionT;
    }

    @Override
    public String toString() {
        return "Factura{" + '}';
    }

    public boolean guardar() {
        String cadenaSQL = "insert into Factura(id,numeroFactura,fecha,descuento,idPedido,idMedioDePago,tipo,identificacionT)values('" + this.id
                + "','" + this.numeroFactura + "','" + this.fecha + "','" + this.descuento + "','" + this.idPedido + "','" + this.idMedioDePago + "','" + this.tipo + "','" + this.identificacionT + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        String cadenaSQL = "update Factura set id='" + this.id + "',numeroFactura='" + this.numeroFactura
                + "',fecha='" + this.fecha + "',descuento='" + this.descuento + "',idPedido='" + this.idPedido
                + "',idMedioDePago='" + this.idMedioDePago + "',tipo='" + this.tipo + "',identificacionT='" + this.identificacionT  + "'where id='" + id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Factura where id='" + this.id + "'";
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
         String cadenaSQL = "Select id,numeroFactura,fecha,descuento,idPedido,idMedioDePago,tipo,identificacionT from Factura";
        return ConectorBD.consultar(cadenaSQL);
    }

public static Factura[] getListaEnObjetos(String filtro, String orden) {
        Factura[] facturas = null;
        ResultSet resultado = Factura.getLista(filtro, orden);
        int cantidadFacturas = 0;
        try {
            while (resultado.next()) {
                cantidadFacturas++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadFacturas > 0) {
                facturas = new Factura[cantidadFacturas];
                resultado.first();
                int i = 0;
                do {
                    Factura factura = new Factura();
                    factura.setId(resultado.getString("id"));
                    factura.setNumeroFactura(resultado.getString("numeroFactura"));
                    factura.setFecha(resultado.getString("fecha"));
                    factura.setDescuento(resultado.getString("descuento"));
                    factura.setIdPedido(resultado.getString("idPedido"));
                    factura.setIdMedioDePago(resultado.getString("idMedioDePago"));
                    factura.setTipo(resultado.getString("tipo"));
                    factura.setIdentificacionT(resultado.getString("identificacionT"));
                    facturas[i] = factura;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facturas;
    }

        public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = Factura.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
               /* if (predeterminado.equals(resultado.getString("codigoDeBarras"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("codigoDeBarras") + "'" + auxiliar + ">" + resultado.getString("codigoDeBarras") + "</option>";
                if (predeterminado.equals(resultado.getString("nombre"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("nombre") + "'" + auxiliar + ">" + resultado.getString("nombre") + "</option>";
                if (predeterminado.equals(resultado.getString("productosPerecederos"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("productosPerecederos") + "'" + auxiliar + ">" + resultado.getString("productosPerecederos") + "</option>";
                if (predeterminado.equals(resultado.getString("productosEnDescuento"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("productosEnDescuento") + "'" + auxiliar + ">" + resultado.getString("productosEnDescuento") + "</option>";
                 if (predeterminado.equals(resultado.getString("estado"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("estado") + "'" + auxiliar + ">" + resultado.getString("estado") + "</option>";*/
            }

        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}

    
    

