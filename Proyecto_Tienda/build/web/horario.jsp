<%-- 
    Document   : horario
    Created on : 18/05/2020, 8:09:19 a. m.
    Author     : root
--%>

<%@page import="clases.Horario"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        ResultSet resultado = Horario.getLista();
        //Contiene la lista del horario activo
        String listaHorarioActiva = "";
        if (resultado != null) {
            while (resultado.next()) {
                //else if (resultado.getString("tipoHorario").equals("especial"))
                if (resultado.getString("dia").equals("true")) {
                    //Horario activo
                    listaHorarioActiva += "<tr>";
                    String tipo = resultado.getString("tipoHorario").equals("general") ? "General"  : "Fin de Semana" ;
                    listaHorarioActiva += "<td>" + tipo + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaApertura") + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaCierre") + "</td>";
                    if (resultado.getString("tipoHorario").equals("general")) {
                        listaHorarioActiva += "<td>" + "L - V" + "</td>";
                    } else {
                        listaHorarioActiva += "<td>" + "S - D" + "</td>"; 
                    }
                    listaHorarioActiva += "<td>Activo</td>";
                    listaHorarioActiva += "<td>";
                    listaHorarioActiva += "<a href='principal.jsp?CONTENIDO=adicionarHorario.jsp&accion=Modificar&id=" + resultado.getString("id") + " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                    listaHorarioActiva += "<a href='principal.jsp?CONTENIDO=horarioActualizar.jsp&accion=Eliminar&id=" + resultado.getString("id") + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                    listaHorarioActiva += "</td>";
                    listaHorarioActiva += "</tr>";
                } else if(resultado.getString("dia").equals("false")) {
                    //Horario inactivo
                    listaHorarioActiva += "<tr>";
                    String tipo = resultado.getString("tipoHorario").equals("general") ? "General"  : "Fin de Semana" ;
                    listaHorarioActiva += "<td>" + tipo + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaApertura") + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaCierre") + "</td>";
                    if (resultado.getString("tipoHorario").equals("general")) {
                        listaHorarioActiva += "<td>" + "L - V" + "</td>";
                    } else {
                        listaHorarioActiva += "<td>" + "S - D" + "</td>";
                    }
                    listaHorarioActiva += "<td>Desactivado</td>";
                    listaHorarioActiva += "<td>";
                    listaHorarioActiva += "<a href='principal.jsp?CONTENIDO=adicionarHorario.jsp&accion=Modificar&id=" + resultado.getString("id") + " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                    listaHorarioActiva += "<a href='principal.jsp?CONTENIDO=horarioActualizar.jsp&accion=Eliminar&id=" + resultado.getString("id") + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                    listaHorarioActiva += "</td>";
                    listaHorarioActiva += "</tr>";
                }
                //Horario especial
                if (resultado.getString("tipoHorario").equals("especial")) {
                    listaHorarioActiva += "<tr>";
                    listaHorarioActiva += "<td>" + "Especial" + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaApertura") + "</td>";
                    listaHorarioActiva += "<td>" + resultado.getString("horaCierre") + "</td>";
                    if(resultado.getString("dia").equals("L"))
                        listaHorarioActiva += "<td>" + "Lunes" + "</td>";
                    else if(resultado.getString("dia").equals("M"))
                        listaHorarioActiva += "<td>" + "Martes" + "</td>";
                    else if(resultado.getString("dia").equals("Mi"))
                        listaHorarioActiva += "<td>" + "Miercoles" + "</td>";
                    else if(resultado.getString("dia").equals("J"))
                        listaHorarioActiva += "<td>" + "Jueves" + "</td>";
                    else if(resultado.getString("dia").equals("V"))
                        listaHorarioActiva += "<td>" + "Viernes" + "</td>";
                    else if(resultado.getString("dia").equals("S"))
                        listaHorarioActiva += "<td>" + "Sabado" + "</td>";
                    else if(resultado.getString("dia").equals("D"))
                        listaHorarioActiva += "<td>" + "Domingo" + "</td>";
                    listaHorarioActiva += "<td>Activo</td>";
                    listaHorarioActiva += "<td>";
                    listaHorarioActiva += "<div style='width: 30px; height: 20px; display:inline-block; '></div>";
                    listaHorarioActiva += "<a href='principal.jsp?CONTENIDO=horarioActualizar.jsp&accion=Eliminar&id=" + resultado.getString("id") + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                    listaHorarioActiva += "</td>";
                    listaHorarioActiva += "</tr>";
                }
            }
        } else {
            //<adicionarHorario>
        }
%>

<h4><b>HORARIO<b></h4>
            <a href="principal.jsp?CONTENIDO=adicionarHorario.jsp" class="btn btn-primary mb-3">Crear horario</a>
            <table class="table" border="1">
                <tr>
                    <th>Horario</th><th>Hora de Entrada</th><th>Hora de Salida</th><th>Dia</th><th>Estado</th><th>Acciones</th>
                </tr>
                <%=listaHorarioActiva%>
            </table>
<%
                }

%>
