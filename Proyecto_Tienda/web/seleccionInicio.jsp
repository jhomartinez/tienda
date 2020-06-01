<%-- 
    Document   : seleccionInicio
    Created on : 26/05/2020, 12:29:03 a. m.
    Author     : root
--%>

<%@page import="clases.Establecimiento"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        
        String tiendaSeleccionada = request.getParameter("selectEst");
        System.out.println("Tienda:"+ tiendaSeleccionada);
        if(!tiendaSeleccionada.equals("0")){
            Establecimiento tienda = new Establecimiento(tiendaSeleccionada);
            sesion.setAttribute("tienda", tienda);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ST Software</title>
    </head>
    <body>
        <h4> Bienvenid@ <%=usuario.getNombre()%> a <%=tienda.getNombre() %></h4> <br>
<p><i> <b><%=tienda.getNombre() %> </b>es el Minimercado donde usted encuentra productos de la canasta familiar, todo lo relacionado con productos de aseo, belleza, 
        granos, bebidas, dulceria, y mucho mas...</i> </p><br>
    </body>
</html>
<%
        }
    }
%>
