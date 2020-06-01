<%-- 
    Document   : establecimiento
    Created on : 16/05/2020, 6:53:04 p. m.
    Author     : root
--%>
<%@page import="clases.Establecimiento"%>
<%@page import="clases.Persona"%>
<%
    String mensaje = "";
    if (request.getParameter("mensaje") != null) {
        mensaje = request.getParameter("mensaje");
    }

    String notificacion = "<div class='alert alert-danger' role='alert'>" + mensaje + " </div>";
    if (mensaje == null || mensaje == "") {
        notificacion = "";

    } else {
%><div> <%= notificacion%></div><%
        }

        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
        } else {
            Persona usuario = (Persona) sesion.getAttribute("usuario");
            String accion = "Adicionar";
            Establecimiento tiendaSeleccionada = (Establecimiento) session.getAttribute("tienda");
            System.out.println("Tienda:" + tiendaSeleccionada.getNit());
            Establecimiento establecimiento = new Establecimiento();
            if (tiendaSeleccionada == null) {
                //no llega id entonces va a adicionar
            } else {
                //llega id entonces va a modificar o eliminar segun la opcion
                accion = "Actualizar";
                establecimiento = new Establecimiento(request.getParameter("Nit"));
            }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Proyecto_Tienda</title>
        <style>
            .btn-ttc,
            .btn-ttc:hover,
            .btn-ttc:active {
                color: white;
                text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
                border-radius: 20px;
                padding: 0px 20px;
                background-color: #ff6000;
            }
        </style>
    </head>
    <body>
        <h4><b>DATOS DEL ESTABLECIMIENTO</b></h4><br>
        <form name="formulario" method="post" action="principal.jsp?CONTENIDO=establecimientoActualizar.jsp" >
            <div class="form-group">
                <table border="0">
                    <tr><th>Nit (*)</th><td><input type="text" name="nit" size="30" maxlength="30"  placeholder="nit" required value="<%=tiendaSeleccionada.getNit()%>"></td></tr> 
                    <tr><th>Nombre (*)</th><td><input type="text" name="nombreEstablecimiento" size="30" maxlength="30"  placeholder="nombre de la tienda" required value="<%=tiendaSeleccionada.getNombre()%>"></td></tr>    
                    <tr><th>Direccion (*)</th><td><input type="text" name="direccion"  size="30" maxlength="30"  placeholder="direccion" required value="<%=tiendaSeleccionada.getDireccion()%>"></td></tr>    
                    <tr><th>Telefono (*)</th><td><input type="text" name="telefono"  size="30" maxlength="30"  placeholder="No.telefono" required value="<%=tiendaSeleccionada.getTelefono()%>"></td></tr>   
                    <tr><th>Latitud </th><td><input type="text" name="latitud" size="30" value="<%=tiendaSeleccionada.getLatitud()%>"></th></tr>
                    <tr><th>Longitud </th><td><input type="text" name="longitud" size="30" value="<%=tiendaSeleccionada.getLongitud()%>"></th></tr>
                </table><br>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="confirmarPedido" name="confirmacionPedido" >
                    <label class="form-check-label" for="confirmarPedido"><b>Confirmar Pedido</></b></label>
                </div><br>
                <h4><b>DATOS DEL ADMINISTRADOR</b></h4><br>
                <table border="0">
                    <tr><th>Identificación(*)</th><td><input type="text" name="cedula" size="30" maxlength="30"  placeholder="1085" required value="<%=usuario.getIdentificacion()%>"></td></tr> 
                    <tr><th>Nombres(*)</th><td><input type="text" name="nombre" size="30" maxlength="30"  placeholder="Nombres Completos" required value="<%=usuario.getNombre()%>"></td></tr> 
                    <tr><th>Apellidos(*)</th><td><input type="text" name="apellido" size="30" maxlength="30"  placeholder="Primer y Segundo Apellido" required value="<%=usuario.getApellido()%>"></td></tr>    
                    <tr><th>Direccion(*)</th><td><input type="text" name="direccion"  size="30" maxlength="30"  placeholder="direccion" required value="<%=usuario.getDireccion()%>"></td></tr>    
                    <tr><th>Telefono(*)</th><td><input type="text" name="telefono"  size="30" maxlength="30"  placeholder="No. telefono" required value="<%=usuario.getTelefono()%>"></td></tr>    
                    <tr><th>Correo Electronico</th><td><input type="text" name="email"  size="30" maxlength="30"  placeholder="email" value="<%=usuario.getEmail()%>"></td></tr>    
                    <tr><th>Clave(*)</th><td><input type="password" name="clave"  size="30" maxlength="30"  placeholder="minimo 6 caracteres" required value="<%=usuario.getClave()%>"></td></tr>   
                    <tr><th>Tipo(*)</th><td><input type="char" name="tipo" size="30" maxlength="30"  placeholder="A administrador" required value="<%=usuario.getTipo()%>"></td></tr>   
                </table><br>
                <button type="submit" class="btn btn-primary" name="accion" value="<%=accion%>"><%=accion%></button>
            </div>
        </form>
        <form action="#" name="formulario" method="post">
            <input type="hidden" name="cedula" value="<%=usuario.getIdentificacion()%>">
            <tr><th>Clave(*)</th><td><input type="password" name="clave"  size="30" maxlength="30"  placeholder="minimo 6 caracteres" required value="<%=usuario.getClave()%>"></td></tr>
        </form>
    </body>
</html>

<script>
    if (navigator.geolocation)
        navigator.geolocation.getCurrentPosition(presentarUbicacion, presentarErrorLocalizacion);
    else
        alert('No Tiene soportegeolocalizacion');

    function presentarErrorLocalizacion() {
        alert('Ha ocurrido un problema al obtener la localizacion');
    }
    function presentarUbicacion(ubicacion) {
        var longitud = ubicacion.coords.longitude;
        var latitud = ubicacion.coords.latitude;
        document.formulario.latitud.value = latitud;
        document.formulario.longitud.value = longitud;

    }
</script>
<%
    }
%>