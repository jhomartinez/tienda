<%-- 
    Document   : personalFormulario
    Created on : 20/05/2020, 4:30:42 p. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario= (Persona) sesion.getAttribute("usuario");
        String accion="Adicionar";
        Persona persona=new Persona();
          if(request.getParameter("id")==null){
              //no llega id entonces va a adicionar
          } else {
              //llega id entonces va a modificar o eliminar segun la opcion
               accion=request.getParameter("accion");
               persona=new Persona(request.getParameter("id"));
          }

%>
<h3><%= accion.toUpperCase()%> LISTA DE PERSONAL</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=personalActualizar.jsp">
    <table border="0">
        <tr><th>Identificacion(*)</th><td><input type="text" name="identificacion" size="50" maxlength="50" value="<%=persona.getIdentificacion() %>" placeholder="Nombre del evento" required></td></tr>    
        <tr><th>Nombres(*)</th><td><input type="text" name="nombre" value="<%=persona.getNombre()%>" required></td></tr>    
        <tr><th>Apellidos(*)</th><td><input type="text" name="apellido" value="<%=persona.getApellido()%>" required></td></tr>    
        <tr><th>Direccion(*)</th><td><input type="text" name="direccion" value="<%=persona.getDireccion()%>" required></td></tr>    
        <tr><th>Telefono(*)</th><td><input type="telephone" name="telefono" value="<%=persona.getTelefono()%>" required></td></tr>    
        <tr><th>Correo Electronico</th><td><input type="email" name="email" value="<%=persona.getEmail()%>"></td></tr>    
        <tr><th>Clave(*)</th><td><input type="password" name="clave" value="<%=persona.getClave()%>" required></td></tr>    
        <tr><th>Tipo</th><td><input type="text" name="tipo" value="<%=persona.getTipo()%>"></td></tr>    
    </table>
     <input type="submit" name="accion" value="<%=accion%>"></input>
</form>

<% 
    }
%>
