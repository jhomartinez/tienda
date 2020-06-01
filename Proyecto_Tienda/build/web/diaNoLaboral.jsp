<%-- 
    Document   : diaNoLaboral
    Created on : 18/05/2020, 9:19:41 a. m.
    Author     : root
--%>

<%@page import="clases.DiaNoLaboral"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {

        Persona usuario = (Persona) sesion.getAttribute("usuario");
        ResultSet resultado = DiaNoLaboral.getLista(null, null);
        String lista = "";
        if (resultado != null) {
            while (resultado.next()) {
                lista += "<tr>";
                //lista += "<td>" + resultado.getString("nit") + "</td>";
                if (resultado.getString("mes").equals("01"))
                    lista += "<td>" + "Enero" + "</td>";
                 else if (resultado.getString("mes").equals("02"))
                lista += "<td>" + "Febrero" + "</td>";
                
                
                /*if (resultado.getString("mes").equals("03")){
                lista += "<td>" + "Marzo" + "</td>";
                
                if (resultado.getString("mes").equals("04"));
                lista += "<td>" + "Abril" + "</td>";
                if (resultado.getString("mes").equals("05"));
                lista += "<td>" + "Mayo" + "</td>";
                if (resultado.getString("mes").equals("06"));
                lista += "<td>" + "Junio" + "</td>";
                if (resultado.getString("mes").equals("07"));
                lista += "<td>" + "Julio" + "</td>";
                if (resultado.getString("mes").equals("08"));
                lista += "<td>" + "Agosto" + "</td>";
                if (resultado.getString("mes").equals("09"));
                lista += "<td>" + "Septiembre" + "</td>";
                if (resultado.getString("mes").equals("10"));
                lista += "<td>" + "Octubre" + "</td>";
                if (resultado.getString("mes").equals("11"));
                lista += "<td>" + "Noviembre" + "</td>";
                if (resultado.getString("mes").equals("12"));
                lista += "<td>" + "Diciembre" + "</td>";*/
                lista += "<td>" + resultado.getString("dia") + "</td>";
                lista += "<td>" + resultado.getString("nombre") + "</td>";
                lista += "<td>";
                lista += "<a href='principal.jsp?CONTENIDO=formularioDiaNoLaboral.jsp&accion=Modificar&id=" + resultado.getString("id") + " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=formularioDiaNoLaboral.jsp&accion=Eliminar&id=" + resultado.getString("id") + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                lista += "</td>";
                lista += "</tr>";
                
            }
        }
%>
<center>
    <h4><b>DIAS NO LABORALES</b></h4><br>
    <a href="principal.jsp?CONTENIDO=formularioDiaNoLaboral.jsp" class="btn btn-primary mb-3">Crear Dia no Laboral</a>
    <table border="1">
        <tr>
           <th>Mes</th><th>Dia</th><th>Motivo Especial</th><th>Opciones</th>
        </tr>
        <%=lista%>
    </table>

</center>




<%
    }
%>