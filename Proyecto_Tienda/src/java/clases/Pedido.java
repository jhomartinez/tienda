package clases;


import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Pedido {
    private String codigo;
    private String identificacionVendedor;
    private String fecha;
    private String cantidad;
    private String estado;
    private String identificacionCliente;

    public Pedido() {
    }

    public Pedido(String codigo, String identificacionVendedor, String fecha, String cantidad, String estado, String identificacionCliente) {
        this.codigo = codigo;
        this.identificacionVendedor = identificacionVendedor;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.estado = estado;
        this.identificacionCliente = identificacionCliente;
    }

    public Pedido(String codigo) {
        String cadenaSQL = "select identificacionVendedor, fecha,cantidad,estado,identificacionCliente  from Pedido where codigo='" + codigo + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {      
         this.codigo = codigo;
         this.identificacionVendedor = resultado.getString("identificacionVendedor");
         this.fecha = resultado.getString("fecha");
         this.cantidad = resultado.getString("cantidad");
         this.identificacionCliente = resultado.getString("identificacionCliente");
            }
    
    } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }
    }

    public String getCodigo() {
        if(codigo==null) return ""; 
        else return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacionVendedor() {
        if(identificacionVendedor==null) return ""; 
        else return identificacionVendedor;
    }

    public void setIdentificacionVendedor(String identificacionVendedor) {
        this.identificacionVendedor = identificacionVendedor;
    }

    public String getFecha() {
        if(fecha==null) return ""; 
        else return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        if(cantidad==null) return ""; 
        else return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
       if(cantidad==null) return ""; 
       else return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdentificacionCliente() {
        if(identificacionCliente==null) return ""; 
        else return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    @Override
    public String toString() {
        return "Pedido{" + '}';
    }
    
   public boolean guardar() {
        String cadenaSQL = "insert into Pedido(codigo,identificacionVendedor, fecha,cantidad,estado,identificacionCliente)values('" + this.codigo
                + "','" + this.identificacionVendedor + "','" + this.fecha + "','" + this.cantidad + "','" + this.estado + "','" + this.identificacionCliente + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        String cadenaSQL = "update Pedido set codigo='" + this.codigo + "',identificacionVendedor='" + this.identificacionVendedor+ "',fecha='" + this.fecha
                + "',cantidad='" + this.cantidad + "',estado='" + this.estado + "',identificacionCliente='" + this.identificacionCliente
                + "'where codigo='" + codigo + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Pedido where codigo='" + this.codigo + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);

    }

    public static ResultSet getLista() {
        String cadenaSQL = "Select codigo,identificacionVendedor, fecha,cantidad,estado,identificacionCliente from Pedido";
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
        String cadenaSQL =  "Select codigo,identificacionVendedor, fecha,cantidad,estado,identificacionCliente from Pedido";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static Pedido[] getListaEnObjetos(String filtro, String orden) {
        Pedido[] pedidos = null;
        ResultSet resultado = Pedido.getLista(filtro, orden);
        int cantidadPedidos = 0;
         
        try {
            while (resultado.next()) {
                cantidadPedidos++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadPedidos > 0) {
                pedidos = new Pedido[cantidadPedidos];
                resultado.first();
                int i = 0;
                do {
                    Pedido pedido = new Pedido();
                    pedido.setCodigo(resultado.getString("codigo"));
                    pedido.setIdentificacionVendedor(resultado.getString("identificacionVendedor"));
                    pedido.setFecha(resultado.getString("fecha"));
                    pedido.setCantidad(resultado.getString("cantidad"));
                    pedido.setEstado(resultado.getString("estado"));
                    pedido.setIdentificacionCliente(resultado.getString("identificacionCliente"));
                    pedidos[i] = pedido;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }

    public ResultSet getResultados() {
        return null;
    }
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = Pedido.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("codigo"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("codigo") + "'" + auxiliar + ">" + resultado.getString("codigo") + "</option>";
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}


    
    
