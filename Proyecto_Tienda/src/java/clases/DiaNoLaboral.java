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
public class DiaNoLaboral {
    private String id;
    private String nit;
    private String mes;
    private String dia;
    private String nombre;

    public DiaNoLaboral() {
    }

    public DiaNoLaboral(String id, String nit, String mes, String dia, String nombre) {
        this.id = id;
        this.nit = nit;
        this.mes = mes;
        this.dia = dia;
    }

    public DiaNoLaboral(String id) {
         String cadenaSQL="select nit, mes, dia, nombre from DiaNoLaboral where id='"+id+"'";
          ResultSet resultado=ConectorBD.consultar(cadenaSQL);
        try
        {
            if(resultado.next())
            {
                this.id = id;
                this.nit=resultado.getString("nit");
                this.mes=resultado.getString("mes");
                this.dia=resultado.getString("dia");
                this.nombre=resultado.getString("nombre");
                
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

    public String getNit() {
        if(nit==null) return ""; 
        else return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getMes() {
       if(mes==null) return ""; 
       else return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
       if(dia==null) return ""; 
       else return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNombre() {
         if(nombre==null) return ""; 
       else return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    @Override
    public String toString() {
        return "DiaNoLaboral{" + '}';
    }
    
    
    
    public boolean guardar(){
        String cadenaSQL="insert into DiaNoLaboral(nit,mes,dia, nombre)values('"+this.nit+"',"+this.mes+
                ","+ this.dia+",'"+ this.nombre+"')";
         return ConectorBD.ejecutarQuery(cadenaSQL);
    }
     public boolean modificar(){
        String cadenaSQL="update DiaNoLaboral set id='"+ this.id +"',nit='"+this.nit
                +",mes="+this.mes+",dia="+this.dia +"',nombre='"+this.nombre +"'where id='"+id+"'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
}
     public boolean eliminar(){
        String cadenaSQL="delete from DiaNoLaboral where id='" +this.id+"'";
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
        String cadenaSQL="Select id, nit, mes, dia, nombre from DiaNoLaboral";
        return ConectorBD.consultar(cadenaSQL);
    }
     public static DiaNoLaboral[] getListaEnObjetos(String filtro, String orden) {
        DiaNoLaboral[] diasNoLaborales = null;
        ResultSet resultado = DiaNoLaboral.getLista(filtro, orden);
        int cantidadDiasNoLaborales = 0;
        try {
            while (resultado.next()) {
                cantidadDiasNoLaborales++;//cantidadCandidatos=cantidadCandidatos+1
            }
            if (cantidadDiasNoLaborales > 0) {
                diasNoLaborales = new DiaNoLaboral[cantidadDiasNoLaborales];
                resultado.first();
                int i = 0;
                do {
                    DiaNoLaboral diaNoLaboral = new DiaNoLaboral();
                    diaNoLaboral.setId(resultado.getString("id"));
                    diaNoLaboral.setNit(resultado.getString("nit"));
                    diaNoLaboral.setMes(resultado.getString("mes"));
                    diaNoLaboral.setDia(resultado.getString("dia"));                
                    diasNoLaborales[i] = diaNoLaboral;
                    i++;
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiaNoLaboral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diasNoLaborales;
    }

     
    public static String getListaEnOptions(String predeterminado) {
        //<option value="1">Opcion 1</option><option value="2" selected>Opcion 2</option>...
        String lista = "";
        ResultSet resultado = DiaNoLaboral.getLista(null, null);
        try {
            while (resultado.next()) {
                String auxiliar = "";
                if (predeterminado.equals(resultado.getString("id"))) {
                    auxiliar = "selected";
                }
                lista += "<option value='" + resultado.getString("id") + "'" + auxiliar + ">" + resultado.getString("nit") + "</option>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiaNoLaboral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
     
}
