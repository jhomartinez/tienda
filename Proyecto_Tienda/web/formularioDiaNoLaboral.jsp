<%-- 
    Document   : adicionarDiaNoLaboral
    Created on : 23/05/2020, 10:46:53 a. m.
    Author     : root
--%>

<%@page import="clases.DiaNoLaboral"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        String accion = "Adicionar";
        DiaNoLaboral diaNoLaboral = new DiaNoLaboral();
        if (request.getParameter("id") == null) {
            //no llega id entonces va a adicionar
        } else {
            //llega id entonces va a modificar o eliminar segun la opcion
            accion = request.getParameter("accion");
            diaNoLaboral = new DiaNoLaboral(request.getParameter("id"));
        }

%>
<h3><%= accion.toUpperCase()%>DIA NO LABORAL</h3><br>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=diaNoLaboralActualizar.jsp">

    <div class="form-group row">
        <label for="fecha" class="col-2 col-form-label">Fecha</label>
        <div class="col-10">
            <input class="form-control" type="date" id="fecha" name="fecha">
        </div>
    </div>
    <div class="form-group row">
        <label for="motivo" class="col-2 col-form-label">Motivo Especial</label>
        <div class="col-10">
            <input class="form-control" type="text" id="motivo" name="nombre">
        </div>
    </div>

    <button type="submit" class="btn btn-primary" name="accion" value="<%=accion%>"><%=accion%></button>
</form>     






<%
    }
%>