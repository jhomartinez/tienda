<%-- 
    Document   : listaPersonal
    Created on : 31/05/2020, 11:12:26 p. m.
    Author     : root
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario= (Persona) sesion.getAttribute("usuario");
        ResultSet resultado = Persona.getLista(null, null);
        String lista = "";
        while (resultado.next()) {
            lista += "<tr>";
            lista += "<td>" + resultado.getString("identificacion") + "</td>";
            lista += "<td>" + resultado.getString("nombre") + "</td>";
            lista += "<td>" + resultado.getString("apellido") + "</td>";
            lista += "<td>" + resultado.getString("direccion") + "</td>";
            lista += "<td>" + resultado.getString("telefono") + "</td>";
            lista += "<td>" + resultado.getString("email") + "</td>";
            lista += "<td>" + resultado.getString("tipo") + "</td>";
            lista += "<td>";
            lista += "<a href='principal.jsp?CONTENIDO=personalFormulario.jsp&accion=Modificar&id="+resultado.getString("identificacion")+  " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
            lista += "<a href='principal.jsp?CONTENIDO=personalFormulario.jsp&accion=Eliminar&id=" + resultado.getString("identificacion") + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
            lista += "</td>";
            lista += "</tr>";
        }
%>

<h4><b>LISTA DE PERSONAL<b></h4><p>
<table border="1">
    <tr>
        <th>Identificacion</th><th>Nombres</th><th>Apellidos</th><th>Direccion</th><th>Telefono</th><th>Correo Electronico</th><th>Tipo</th>
        <th><a href="principal.jsp?CONTENIDO=personalFormulario.jsp"><img src='image/addUsuario.png' style='width: 30px; height: 30px;'/></a>
    </tr>
    <%=lista%>
</table>


<%
    }
%>