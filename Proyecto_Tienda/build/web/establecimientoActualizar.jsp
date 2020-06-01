<%-- 
    Document   : establecimientoActualizar
    Created on : 19/05/2020, 8:36:11 a. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%@page import="clases.Establecimiento"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        String mensajeSalida = "";
        Establecimiento establecimiento = new Establecimiento();
        Persona p = new Persona();
        switch (request.getParameter("accion")) {
            case "Adicionar":
                //Registrar administrador
                p.setIdentificacion(request.getParameter("cedula"));
                p.setNombre(request.getParameter("nombre"));
                p.setApellido(request.getParameter("apellido"));
                p.setTelefono(request.getParameter("telefono"));
                p.setDireccion(request.getParameter("direccion"));
                p.setEmail(request.getParameter("email"));
                p.setTipo("A");
                p.setClave(request.getParameter("clave"));
                //Validar que la persona ya exista
                //Falta encriptar la clave
                //p.guardar();
                
                establecimiento.setNit(request.getParameter("nit"));
                establecimiento.setNombre(request.getParameter("nombreEstablecimiento"));
                establecimiento.setDireccion(request.getParameter("direccion"));
                
                            
                if(request.getParameter("latitud") != null &&  !request.getParameter("latitud").isEmpty())
                    establecimiento.setLatitud(request.getParameter("latitud"));
                if(request.getParameter("longitud") != null && !request.getParameter("longitud").isEmpty())
                    establecimiento.setLongitud(request.getParameter("longitud"));
                establecimiento.setTelefono(request.getParameter("telefono"));
                establecimiento.setConfirmacionPedido("s");
                //establecimiento.setIdentificacionAdministrador(p.getIdentificacion());
                establecimiento.guardar();
                mensajeSalida ="<div class='alert alert-success'><p>Los datos han sido guardados corractamente</p></div>";
                break;

            case "Actualizar":
               //Modificar administrador
                p.setIdentificacion(request.getParameter("cedula"));
                p.setNombre(request.getParameter("nombre"));
                p.setApellido(request.getParameter("apellido"));
                p.setTelefono(request.getParameter("telefono"));
                p.setDireccion(request.getParameter("direccion"));
                p.setEmail(request.getParameter("email"));
                //p.setTipo("A");
                //p.setClave(request.getParameter("clave"));
                //Validar que la persona ya exista
                //Falta encriptar la clave
                Persona ant = (Persona) sesion.getAttribute("usuario");
                p.modificar(ant.getIdentificacion());
                
                establecimiento.setNit(request.getParameter("nit"));
                establecimiento.setNombre(request.getParameter("nombre"));
                establecimiento.setDireccion(request.getParameter("direccion"));
                if(request.getParameter("latitud") != null &&  !request.getParameter("latitud").isEmpty())
                    establecimiento.setLatitud(request.getParameter("latitud"));
                if(request.getParameter("longitud") != null && !request.getParameter("longitud").isEmpty())
                    establecimiento.setLongitud(request.getParameter("longitud"));
                establecimiento.setTelefono(request.getParameter("telefono"));
                if (request.getParameter("confirmacionPedido") == null)
                    establecimiento.setConfirmacionPedido("0");
                else
                    establecimiento.setConfirmacionPedido("1");
                        
                System.out.println("Pedido[" + request.getParameter("confirmacionPedido") +  "]");
                establecimiento.setIdentificacionAdministrador(new Persona(p.getIdentificacion()));
                establecimiento.modificar();
                sesion.setAttribute("tienda", establecimiento);
                mensajeSalida ="<div class='alert alert-success'><p>Los datos han sido Modificados corractamente</p></div>";
                break;

            /*case "Eliminar":
                //System.out.println("parametro Id:"+request.getParameter("id"));
                establecimiento.setNit(request.getParameter("nit"));
                establecimiento.eliminar();
                break;*/
        }

%>        
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div><%=mensajeSalida %></div>
    </body>
</html>
<%
   // response.sendRedirect("principal.jsp?CONTENIDO=establecimiento.jsp");
    }

%>
<script type="text/javascript">
    location = "principal.jsp?CONTENIDO=establecimiento.jsp";
</script>