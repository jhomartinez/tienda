<%-- 
    Document   : personal.jsp
    Created on : 20/05/2020, 4:07:08 p. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    Persona persona = null;
    String titleBtn = "Enviar";
    String mensaje = "";
    if (request.getParameter("mensaje") != null) {
        mensaje = request.getParameter("mensaje");
    }
    if (request.getParameter("nuevo") != null
            && request.getParameter("nuevo").equals("true")) {
        persona = new Persona();
    } else {
        if (sesion.getAttribute("usuario") != null) {
            titleBtn = "Guardar";
            persona = (Persona) sesion.getAttribute("usuario");
        }
    }
%>

<form action="./registro/personalAdicionar.jsp" method="post" name="form">
    <div class="form-group row">
        <label for="cedula" >Identificación</label>
        <input class="form-control" type="text" id="cedula" name="cedula" value="<%=persona.getIdentificacion()%>">
    </div>
    <div class="form-group row">
        <label for="nombre">Nombres</label>
        <input class="form-control" type="text" id="nombres" name="nombre" value="<%=persona.getNombre()%>">
    </div>
    <div class="form-group row">
        <label for="apellidos">Apellidos</label>
        <input class="form-control" type="text" id="apellidos" name="apellido" value="<%=persona.getApellido()%>">
    </div>
    <div class="form-group row">
        <label for="direccion" >Direccion</label>
        <input class="form-control" type="text" id="direccion" name="direccion" value="<%=persona.getDireccion()%>">
    </div>
    <div class="form-group row">
        <label for="telefono" >Telefono</label>
        <input class="form-control" type="telephone" id="telefono" name="telefono" value="<%=persona.getTelefono()%>">
    </div>
    <div class="form-group row">
        <label for="email" >Correo electrónico</label>
        <input class="form-control" type="email" id="email" name="email" value="<%=persona.getEmail()%>">
    </div>
    <%
        String notificacion = "<div class='alert alert-danger' role='alert'>" + mensaje + " </div>";
        if (mensaje == null || mensaje == "") {
            notificacion = "";

        } else {
    %><div> <%= notificacion%></div><%
        }
    %>
    <div class="form-group row">
        <label for="clave" >Contraseña</label>
        <input class="form-control" type="password" id="clave" name="clave" value="<%=persona.getClave()%>">
    </div>
    <div class="form-group row">
        <label for="clave" >Confirmar contraseña</label>
        <input class="form-control" type="password" id="clave-confirm" name="clave-confirm" value="<%=persona.getClave()%>">
    </div>

    <input class="btn btn-primary" type="submit" name="accion" value="<%=titleBtn%>" >
</form>