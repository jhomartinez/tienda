<%-- 
    Document   : horariosFormulario
    Created on : 20/05/2020, 3:12:58 p. m.
    Author     : root
--%>

<%@page import="clases.Horario"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona USUARIO = (Persona) sesion.getAttribute("usuario");
        String accion="Adicionar";
        Horario horario=new Horario();
          if(request.getParameter("id")==null){
              //no llega id entonces va a adicionar
          } else {
              //llega id entonces va a modificar o eliminar segun la opcion
               accion=request.getParameter("accion");
               horario=new Horario(request.getParameter("id"));
          }

       
%>
<h3><%= accion.toUpperCase()%> HORARIO</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=horarioActualizar.jsp">
    <table border="0">
        <tr><th>Nit(*)</th><td><input type="text" name="nit" value="<%=horario.getNit()%>" required></td></tr>   
        <tr><th>Horario(*)</th><td><input type="text" name="tipoHorario" size="50" maxlength="50" value="<%=horario.getTipoHorario()%>" placeholder="tipo de horario" required></td></tr>    
        <tr><th>Hora de Apertura(*)</th><td><input type="time" name="horaApertura" value="<%=horario.getHoraApertura()%>" required></td></tr>    
       <tr><th>Hora de Cierre(*)</th><td><input type="time" name="horaCierre" value="<%=horario.getHoraCierre()%>" required></td></tr>    
        <tr><th>Dia(*)</th><td><input type="text" name="dia" value="<%=horario.getDia() %>" required></td></tr>    
       
    </table>
     <input type="hidden" name="id" value="<%=horario.getId()%>">
    <input type="submit" name="accion" value="<%=accion%>"><p>
</form>

<%    }
%>
