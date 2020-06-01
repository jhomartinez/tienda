/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clasesGenericas.ConectorBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Horario {

    private String id;
    private String nit;
    private String dia;
    private String horaApertura;
    private String horaCierre;
    private String tipoHorario;

    public Horario() {
    }

    public Horario(String id, String nit, String dia, String horaApertura, String horaCierre, String tipoHorario) {
        this.id = id;
        this.nit = nit;
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Horario(String id) {
        String cadenaSQL = "select nit, dia, horaApertura, horaCierre,tipoHorario from Horario where id='" + id + "'";
        ResultSet resultado = ConectorBD.consultar(cadenaSQL);
        try {
            if (resultado.next()) {
                this.id = id;
                this.nit = resultado.getString("nit");
                this.dia = resultado.getString("dia");
                this.horaApertura = resultado.getString("horaApertura");
                this.horaCierre = resultado.getString("horaCierre");
                this.tipoHorario = resultado.getString("tipoHorario");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error en la consulta, " + cadenaSQL + "\n" + ex.getMessage());
        }
    }

    public String getId() {
        if (id == null) {
            return "";
        } else {
            return id;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        if (nit == null) {
            return "";
        } else {
            return nit;
        }
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDia() {
        if (dia == null) {
            return "";
        } else {
            return dia;
        }
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraApertura() {
        if (horaApertura == null) {
            return "";
        } else {
            return horaApertura;
        }
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        if (horaCierre == null) {
            return "";
        } else {
            return horaCierre;
        }
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getTipoHorario() {
        if (tipoHorario == null) {
            return "";
        } else {
            return tipoHorario;
        }
    }

    public void setTipoHorario(String tipoHorario) {
        this.tipoHorario = tipoHorario;
    }

    @Override
    public String toString() {
        return "Horario={" + "nit:" + this.getNit() + "\n"
                + "dia:" + this.dia + "\n"
                + "HoraA:" + this.horaApertura + "\n"
                + "HoraC:" + this.horaCierre + "\n"
                + "tipo:" + this.tipoHorario + "}";
    }

    public boolean guardar() {
        String cadenaSQL = "insert into Horario(nit,dia,horaApertura,horaCierre,tipoHorario)values"
                + "('" + this.nit + "','" + this.dia + "','" + this.horaApertura
                + "','" + this.horaCierre + "','" + this.tipoHorario + "')";
        System.out.println("Consulta:[" + cadenaSQL + "]");
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean modificar() {
        String cadenaSQL = "update Horario set dia='" + this.dia + "',horaApertura='" + this.horaApertura + "',horaCierre='" + this.horaCierre + "',tipoHorario='" + this.tipoHorario + "'where id='" + id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public boolean eliminar() {
        String cadenaSQL = "delete from Horario where id='" + this.id + "'";
        return ConectorBD.ejecutarQuery(cadenaSQL);
    }

    public static ResultSet getLista() {
        String cadenaSQL = "Select id, dia, horaApertura, horaCierre, tipoHorario from Horario";
        return ConectorBD.consultar(cadenaSQL);
    }
    
    public static boolean tieneHorarioTipoGeneral(String nit) {
        String cadenaSQL = "Select id, dia, horaApertura, horaCierre, tipoHorario from Horario where nit = '"+ nit +"' and tipoHorario ='general'";
        boolean valor = true;
        try {
            if(ConectorBD.consultar(cadenaSQL).next() == false )
                valor = false;
        } catch (SQLException ex) {
            Logger.getLogger("Exception-Horario-General").log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    public static boolean tieneHorarioTipoFinSemana(String nit) {
        String cadenaSQL = "Select id, dia, horaApertura, horaCierre, tipoHorario from Horario where nit = '"+ nit +"' and tipoHorario ='finSemana'";
        boolean valor = true;
        try {
            if(ConectorBD.consultar(cadenaSQL).next() == false )
                valor = false;
        } catch (SQLException ex) {
            Logger.getLogger("Exception-Horario-Fin de semana").log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public static String getListaEnOptions(String tiendaNit) {
        String lista = "";
        if(!tieneHorarioTipoGeneral(tiendaNit)){
            lista += "<option value='general'>General</option>";
        }
        if(!tieneHorarioTipoFinSemana(tiendaNit)){
            lista += "<option value='finSemana'>Fin de semana</option>";
        }
        lista += "<option value='especial'>Especial</option>";
        return lista;
    }
    
    public static String getListaEnOptionsConThisTipo(String tiendaNit) {
        String lista = "";
        if(!tieneHorarioTipoGeneral(tiendaNit)){
            lista += "<option value='general'>General</option>";
        }
        if(!tieneHorarioTipoFinSemana(tiendaNit)){
            lista += "<option value='finSemana'>Fin de semana</option>";
        }
        lista += "<option value='especial'>Especial</option>";
        return lista;
    }
    
    public static Map<String, Boolean> getDiasHorarioGeneral(){
        Map<String, Boolean> dias = new HashMap<String, Boolean>();
        dias.put("lunes", Boolean.TRUE);
        dias.put("martes", Boolean.TRUE);
        dias.put("miercoles", Boolean.TRUE);
        dias.put("jueves", Boolean.TRUE);
        dias.put("viernes", Boolean.TRUE);
        return dias;
    }
    
    public static Map<String, Boolean> getDiasHorarioFinSemana(){
        Map<String, Boolean> dias = new HashMap<String, Boolean>();
        dias.put("sabado", Boolean.TRUE);
        dias.put("domingo", Boolean.TRUE);
        return dias;
    }

}
