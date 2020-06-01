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
public class EntregaDeCaja {
    private String id;
    private String identificacionVendedor;
    private String fecha;
    private String valorEntregado;
    private String valorGeneradoSistema;

    public EntregaDeCaja() {
    }

    public EntregaDeCaja(String id, String identificacionVendedor, String fecha, String valorEntregado, String valorGeneradoSistema) {
        this.id = id;
        this.identificacionVendedor = identificacionVendedor;
        this.fecha = fecha;
        this.valorEntregado = valorEntregado;
        this.valorGeneradoSistema = valorGeneradoSistema;
    }

    public EntregaDeCaja(String id) {
       String cadenaSQL="select identificacionVendedor, fecha, valorEntregado, valorGeneradoSistema from EntregaDeCaja where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
                this.id = id;
                this.identificacionVendedor=resultado.getString("identificacionVendedor");
                this.fecha=resultado.getString("fecha");
                this.valorEntregado=resultado.getString("valorEntregado");
                this.valorGeneradoSistema=resultado.getString("valorGeneradoSistema");
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

    public String getValorEntregado() {
        if(valorEntregado==null) return ""; 
        else return valorEntregado;
    }

    public void setValorEntregado(String valorEntregado) {
        this.valorEntregado = valorEntregado;
    }

    public String getValorGeneradoSistema() {
        if(valorGeneradoSistema==null) return ""; 
        else return valorGeneradoSistema;
    }

    public void setValorGeneradoSistema(String valorGeneradoSistema) {
        this.valorGeneradoSistema = valorGeneradoSistema;
    }

    @Override
    public String toString() {
        return "EntregaDeCaja{" + '}';
    }
    
    
    
    public boolean guardar(){
        String cadenaSQL="insert into EntregaDeCaja(identificacionVendedor,fecha,valorEntregado,valorGeneradoSistema)values('"+this.identificacionVendedor+"','"+ this.fecha+"','"+this.valorEntregado+
                "','"+ this.valorGeneradoSistema+"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update EntregaDeCaja set id='"+ this.id +"',identificacionVendedor='"+this.identificacionVendedor
                +"',fecha='"+this.fecha+"',valorEntregado='"+this.valorEntregado+"',valorGeneradoSistema='"+this.valorGeneradoSistema+"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from EntregaDeCaja where id='" +this.id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public static ResultSet getLista(){
         String cadenaSQL="Select id, identificacionVendedor, fecha, valorEntregado, valorGeneradoSistema from EntregaDeCaja";
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
         String cadenaSQL = "Select id, identificacionVendedor, fecha, valorEntregado, valorGeneradoSistema from EntregaDeCaja";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static EntregaDeCaja[] getListaEnObjetos(String filtro, String orden) {
        EntregaDeCaja[] entregas = null;
        ResultSet resultado = EntregaDeCaja.getLista(filtro, orden);
        int cantidadEntregas = 0;
        try {
            while (resultado.next()) {
                cantidadEntregas++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadEntregas > 0) {
                entregas = new EntregaDeCaja[cantidadEntregas];
                resultado.first();
                int i = 0;
                do {
                    EntregaDeCaja entrega= new EntregaDeCaja();
                    entrega.setId(resultado.getString("id"));
                    entrega.setIdentificacionVendedor(resultado.getString("identificacionVendedor"));
                    entrega.setFecha(resultado.getString("fecha"));
                    entrega.setValorEntregado(resultado.getString("valorEntregado"));
                    entrega.setValorGeneradoSistema(resultado.getString("valorGeneradoSistema"));
                    entregas[i] = entrega;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDeCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entregas;
    }

     public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = EntregaDeCaja.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("id") + "</option>";
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntregaDeCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}

    

    
    
    
