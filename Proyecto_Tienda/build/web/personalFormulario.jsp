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
<h3><%= accion.toUpperCase()%> <b>LISTA DE PERSONAL</b></h3><br/>
 <form name="formulario" method="post" action="principal.jsp?CONTENIDO=personalActualizar.jsp">
     <div class="form-group row">
        <label for="cedula" >No.Identificación</label>
        <input class="form-control" type="text" name="identificacion" value="<%=persona.getIdentificacion() %>"placeholder="cedula de ciudadania" required>
    </div>
    <div class="form-group row">
        <label for="nombre">Nombres</label>
        <input class="form-control" type="text" name="nombre" value="<%=persona.getNombre()%>"placeholder="primer y segundo nombre" required>
    </div>
    <div class="form-group row">
        <label for="apellidos">Apellidos</label>
        <input class="form-control" type="text" name="apellido" value="<%=persona.getApellido()%> "placeholder="primer y segundo Apellido" required>
    </div>
    <div class="form-group row">
        <label for="direccion" >Direccion</label>
        <input class="form-control" type="text" name="direccion" value="<%=persona.getDireccion()%>"placeholder="direccion de residencia" required>
    </div>
    <div class="form-group row">
        <label for="telefono" >Telefono</label>
        <input class="form-control" type="telephone"  name="telefono" value="<%=persona.getTelefono()%>"placeholder="numero de celular" required>
    </div>
    <div class="form-group row">
        <label for="email" >Correo electrónico</label>
        <input class="form-control" type="email"  name="email" value="<%=persona.getEmail()%>"placeholder="@">
    </div>
    <div class="form-group row">
        <label for="clave" >Clave</label>
        <input class="form-control" type="password"  name="clave" value="<%=persona.getClave()%>"placeholder="8 caracteres">
    </div>
    <div class="form-group row">
        <label for="tipo" >Tipo</label>
        <input class="form-control" type="text"  name="tipo" value="<%=persona.getTipo()%>"placeholder="A:administrador, C:cliente, V:vendedor">
    </div>
        <input class="btn btn-primary" type="submit" name="accion" value="<%=accion%>" >
   
</form> 

<% 
    }
%>
