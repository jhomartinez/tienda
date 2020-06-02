<%-- 
    Document   : personalActualizar
    Created on : 20/05/2020, 4:45:07 p. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<%
    Persona persona = null;
    String cedula = request.getParameter("cedula");
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String direccion = request.getParameter("direccion");
    String telefono = request.getParameter("telefono");
    String email = request.getParameter("email");
    String clave = request.getParameter("clave");
    String clavec = request.getParameter("clave-confirm");
    String mensaje = "";
    if (!clave.equals(clavec)) {
        mensaje = "Contraseñas no coiciden.";
        response.sendRedirect("../registro.jsp?nuevo=true&mensaje=" + mensaje);
    } else {
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("usuario") == null) {
            persona = new Persona(cedula, nombre, apellido, direccion, telefono, email, clave, "A");
            persona.guardar();
            mensaje = "Usuario administrador creado! por favor inicia sesión";
            response.sendRedirect("../index.jsp?nuevo=true&mensaje=" + mensaje);
        } else {
            switch (request.getParameter("accion")) {
                case "Enviar":
                    persona = new Persona(cedula, nombre, apellido, direccion, telefono, email, clave, "A");
                    persona.guardar();
                    //Devolver que los datos han sido registrados
                    //Redireccionar al inicio de sesion
                    break;
                case "Guardar":
                    persona.setIdentificacion(request.getParameter("identificacion"));
                    persona.setNombre(request.getParameter("nombre"));
                    persona.setApellido(request.getParameter("apellido"));
                    persona.setDireccion(request.getParameter("direccion"));
                    persona.setTelefono(request.getParameter("telefono"));
                    persona.setEmail(request.getParameter("email"));
                    persona.setClave(request.getParameter("clave"));
                    persona.setTipo(request.getParameter("tipo"));
                    persona.modificar("identificacionAnterior");
                    break;

                case "Eliminar":
                    //System.out.println("parametro Id:"+request.getParameter("id"));
                    persona.setIdentificacion(request.getParameter("identificacion"));
                    persona.eliminar();
                    break;

            }
        }
    }

%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=listaPersonal.jsp";
</script>
