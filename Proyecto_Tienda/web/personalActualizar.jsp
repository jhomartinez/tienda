<%-- 
    Document   : personalActualizar
    Created on : 1/06/2020, 2:59:19 p. m.
    Author     : root
--%>


<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        Persona persona = new Persona();
        switch (request.getParameter("accion")) {
            case "Adicionar":
                persona.setIdentificacion(request.getParameter("identificacion"));
                persona.setNombre(request.getParameter("nombre"));
                persona.setApellido(request.getParameter("apellido"));
                persona.setDireccion(request.getParameter("direccion"));
                persona.setTelefono(request.getParameter("telefono"));
                persona.setEmail(request.getParameter("email"));
                persona.setClave(request.getParameter("clave"));
                persona.setTipo(request.getParameter("tipo"));
                persona.guardar();
                break;

            case "Modificar":
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

        //response.sendRedirect("principal.jsp?CONTENIDO=personal.jsp");
    }

%>
<script type="text/javascript">
    window.location = "principal.jsp?CONTENIDO=listaPersonal.jsp";
</script>