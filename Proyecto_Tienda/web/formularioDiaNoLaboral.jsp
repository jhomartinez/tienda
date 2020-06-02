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
        Persona USUARIO = (Persona) sesion.getAttribute("usuario");
        String accion = "Adicionar ";
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
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=diaNoLaboralAcualizar.jsp">

    <div class="form-group">
       <%-- <label for="Seleccione Mes">Mes</label>
        <select class="custom-select" id="mes" name="mes">
            <option selected value="0">Seleccionar una opción</option>
            <option value="01" >Enero</option>
            <option value="02">Febrero</option>
            <option value="03">Marzo</option>
            <option value="04">Abril</option>
            <option value="05">Mayo</option>
            <option value="06">Junio</option>
            <option value="07">Julio</option>
            <option value="08">Agosto</option>
            <option value="09">Septiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
        </label>--%>
    </div>
    <div class="col-md-3 mb-3">
        <label for="dia">Fecha</label>
        <input type="date" class="form-control" nombre="fecha" required>
        <label for="nombre">Motivo Especial</label>
        <input type="text" class="form-control" id="motivoEspecial">
    </div>

    <button type="submit" class="btn btn-primary" name="accion" value="<%=accion%>"><%=accion%></button>
</form>     






    <%
        }
    %>