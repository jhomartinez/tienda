/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String valorUnitarioCompra;
    private String valorUnitarioVenta;
    private String stock;
    private String stockMinimo;
    private String stockMaximo;
    private String codigoDeBarras;
    private String productosEnDescuento;
    private String productosPerecederos;
    private String foto;

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, String valorUnitarioCompra, String ValorUnitarioVenta, String stock, String stockMinimo, String stockMaximo, String codigoDeBarras, String productosEnDescuento, String productosPerecederos, String foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorUnitarioCompra = valorUnitarioCompra;
        this.valorUnitarioVenta = valorUnitarioVenta;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.codigoDeBarras = codigoDeBarras;
        this.productosEnDescuento = productosEnDescuento;
        this.productosPerecederos = productosPerecederos;
        this.foto = foto;
    }

    public Producto(String codigo) {
        String cadenaSQL = "select nombre,descripcion,valorUnitarioCompra,valorUnitarioVenta,stock,stockMinimo,stockMaximo,codigoDeBarras,productosEnDescuento,productosPerecederos,foto from Producto where codigo='" + codigo + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.codigo = codigo;
                this.nombre = resultado.getString("nombre");
                this.descripcion = resultado.getString("descripcion");
                this.valorUnitarioCompra = resultado.getString("valorUnitarioCompra");
                this.valorUnitarioVenta = resultado.getString("valorUnitarioVenta");
                this.stock = resultado.getString("stock");
                this.stockMinimo = resultado.getString("stockMinimo");
                this.stockMaximo = resultado.getString("stockMaximo");
                this.codigoDeBarras = resultado.getString("codigoDeBarras");
                this.productosEnDescuento = resultado.getString("productosEnDescuento");
                this.productosPerecederos = resultado.getString("productosPerecederos");
                this.foto = resultado.getString("foto");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }

    }

    public String getCodigo() {
        if (codigo == null) {
            return "";
        } else {
            return codigo;
        }
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        if (nombre == null) {
            return "";
        } else {
            return nombre;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        if (descripcion == null) {
            return "";
        } else {
            return descripcion;
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValorUnitarioCompra() {
        if (valorUnitarioCompra == null) {
            return "";
        } else {
            return valorUnitarioCompra;
        }
    }

    public void setValorUnitarioCompra(String valorUnitarioCompra) {
        this.valorUnitarioCompra = valorUnitarioCompra;
    }

    public String getValorUnitarioVenta() {
        if (valorUnitarioVenta == null) {
            return "";
        } else {
            return valorUnitarioVenta;
        }
    }

    public void setValorUnitarioVenta(String ValorUnitarioVenta) {
        this.valorUnitarioVenta = ValorUnitarioVenta;
    }

    public String getFoto() {
        if (foto == null) {
            return "";
        } else {
            return foto;
        }
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStock() {
        if (stock == null) {
            return "";
        } else {
            return stock;
        }
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockMinimo() {
        if (stockMinimo == null) {
            return "";
        } else {
            return stockMinimo;
        }
    }

    public void setStockMinimo(String stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getStockMaximo() {
        if (stockMaximo == null) {
            return "";
        } else {
            return stockMaximo;
        }
    }

    public void setStockMaximo(String stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public String getCodigoDeBarras() {
        if (codigoDeBarras == null) {
            return "";
        } else {
            return codigoDeBarras;
        }
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getProductosEnDescuento() {
        if (productosEnDescuento == null) {
            return "";
        } else {
            return productosEnDescuento;
        }
    }

    public void setProductosEnDescuento(String productosEnDescuento) {
        this.productosEnDescuento = productosEnDescuento;
    }

    public String getProductosPerecederos() {
        if (productosPerecederos == null) {
            return "";
        } else {
            return productosPerecederos;
        }
    }

    public void setProductosPerecederos(String productosPerecederos) {
        this.productosPerecederos = productosPerecederos;
    }
    
   /*public String getEStado(){
       String estado="Por ejecutar";
       SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
       try {
       Date fechaInicioEvento=formatoFecha.parse(this.fecha+" "+this.horaInicio);
       Date fechaFinalizacionEvento=formatoFecha.parse(this.fecha+" "+this.horaFinalizacion);
       Date fechaActual=new Date();//a la fecha actual le vamos a restar la fecha del evento, si da negativo es un evento futuro
       long actual=fechaActual.getTime();
       long inicio=fechaInicioEvento.getTime();
       long finalizacion=fechaFinalizacionEvento.getTime();
       if(actual>finalizacion)estado="Terminado";
       else{
           if(actual<inicio) estado="Por ejecutar";
           else estado="En ejecucion";
           
         }
       } catch (ParseException ex) {
           System.out.println("Error al convertir fecha. "+this.fecha);
       }
       return estado;*/
       
    @Override
    public String toString() {
        return "Producto{" + '}';
    }

    public boolean guardar() {
        String cadenaSQL = "insert into Producto(codigo,nombre,descripcion,valorUnitarioCompra,valorUnitarioVenta,stock,stockMinimo,StockMaximo,codigoDeBarras,productosEnDescuento,productosPerecederos,foto)values('" + this.codigo
                + "','" + this.nombre + "','" + this.descripcion + "','" + this.valorUnitarioCompra + "','" + this.valorUnitarioVenta + "','" + this.foto + "','" + this.stock + "','" + this.stockMinimo + "','" + this.stockMaximo + "','" + this.codigoDeBarras + "','" + this.productosEnDescuento + "','" + this.productosPerecederos + "')";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        StringBuilder cadenaSQL = new StringBuilder("update Producto set codigo='" + this.codigo + "',nombre='" + this.nombre
                + "',descripcion='" + this.descripcion + "',valorUnitarioCompra='" + this.valorUnitarioCompra + "',valorUnitarioVenta='" + this.valorUnitarioVenta + "', stock='" + this.stock + "',stockMinimo='" + this.stockMinimo + "',stockMaximo='" + this.stockMaximo + "',codigoDeBarras='" + this.codigoDeBarras + "',productosEnDescuento='" + this.productosEnDescuento + "',productosPerecederos='" + this.productosPerecederos + "'where codigo='" + codigo + "'");
        if (!this.foto.isEmpty()) {
            cadenaSQL.append(", foto='" + this.foto + "'");
        }
        return ConectorBD.ejecutarQuery(cadenaSQL.toString());

    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Producto where codigo='" + this.codigo + "'";
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
        String cadenaSQL = "Select codigo,nombre,descripcion,valorUnitarioCompra,valorUnitarioVenta,stock,stockMinimo,stockMaximo,codigoDeBarras,productosEnDescuento,productosPerecederos,foto from Producto";
        return ConectorBD.consultar(cadenaSQL);
    }

    public static Producto[] getListaEnObjetos(String filtro, String orden) {
        Producto[] productos = null;
        ResultSet resultado = Producto.getLista(filtro, orden);
        int cantidadProductos = 0;
         
        try {
            while (resultado.next()) {
                cantidadProductos++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadProductos > 0) {
                productos = new Producto[cantidadProductos];
                resultado.first();
                int i = 0;
                do {
                    Producto producto = new Producto();
                    producto.setCodigo(resultado.getString("codigo"));
                    producto.setNombre(resultado.getString("nombre"));
                    producto.setDescripcion(resultado.getString("descripcion"));
                    producto.setValorUnitarioCompra(resultado.getString("valorUnitarioCompra"));
                    producto.setValorUnitarioVenta(resultado.getString("valorUnitarioVenta"));
                    producto.setStock(resultado.getString("stock"));
                    producto.setStockMinimo(resultado.getString("stockMinimo"));
                    producto.setStockMaximo(resultado.getString("stockMaximo"));
                    producto.setCodigoDeBarras(resultado.getString("codigoDeBarras"));
                    producto.setProductosEnDescuento(resultado.getString("productosEnDescuento"));
                    producto.setProductosPerecederos(resultado.getString("productosPerecederos"));
                    producto.setFoto(resultado.getString("foto"));
                    productos[i] = producto;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    public ResultSet getResultados() {
        return null;
    }

    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = Producto.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("codigo"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("codigo") + "'" + auxiliar + ">" + resultado.getString("codigo") + "</option>";
                if (predeterminado.equals(resultado.getString("codigoDeBarras"))) {
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
                 /*if (predeterminado.equals(resultado.getString("estado"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("estado") + "'" + auxiliar + ">" + resultado.getString("estado") + "</option>";*/
            }

        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
