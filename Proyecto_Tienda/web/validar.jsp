<%-- 
    Document   : validar
    Created on : 19/04/2020, 10:36:22 a. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<%
    String usuario= request.getParameter("usuario");
    String clave= request.getParameter("clave");
    System.err.println("usuario:"+ usuario);
    System.err.println("clave:"+ clave);
    Persona persona=Persona.validar(usuario,clave);
    if (persona== null) {
        //no es un usuario valido
        String mensaje="Usuario o Contraseña no valida";
        response.sendRedirect("index.jsp?mensaje=" + mensaje);
    } else {
        //si es un usuario valido
        HttpSession sesion= request.getSession();
        sesion.setAttribute("usuario", persona);
        response.sendRedirect("principal.jsp?CONTENIDO=inicio.jsp");
    }
%>
