<%
    String mensaje = "";
    String nuevo = "";
    if (request.getParameter("mensaje") != null) {
        mensaje = request.getParameter("mensaje");
    }
    if (request.getParameter("nuevo") != null) {
        nuevo = request.getParameter("nuevo");
    }
    if (request.getParameter("accion") != null
            && request.getParameter("accion").equals("cerrarSesion")) {
        request.getSession().removeAttribute("tienda");
        request.getSession().removeAttribute("usuario");
        System.out.println("Sesion cerrada");
    }

%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Proyecto_Tienda</title>
        <link rel="stylesheet" href="librerias/css/bootstrap.min.css"/>

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
            h4{
                color: #0080ff;
            }
            nav>a{
                font-family: serif;
                font-size: 18px;
            }
            .imageBack{
                background-image: url("image/index1.jpg");
                height: 500px;
                width: 100%;
                <%--background-color: black;
                background-repeat: no-repeat;
                height: 100%;
                width: 100%;
                position:absolute;
                z-index: 1;--%>
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <img src="image/logoSt1.png" width="90" height="90"/>
            <strong><a class="navbar-brand">SOFTWARE PARA TIENDAS</a></strong><hr>
            <p class="navbar-brand"><i> Para un mejor control de su negocio!</i></p>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon">
                </span>
            </button>
        </nav>
        <div id="menu"> 
            <center>
                <div class="card imageBack">
                    <div class="card" style="width: 50%;">
                        <div class="card-body">
                            <h4 class="card-title">Bienvenido</h4>
                            <%  
                                String notificacion = "<div class='alert alert-danger' role='alert'>" + mensaje + " </div>";
                                String notificacionExito = "<div class='alert alert-success' role='alert'>" + mensaje + " </div>";
                                if (nuevo == null || nuevo == "") {
                                    notificacionExito = "";
                                    if (mensaje == null || mensaje == "") {
                                    notificacion = "";
                                    } else {
                                        %><div> <%=notificacion %></div><%
                                    }
                                } else {
                                    if (mensaje == null || mensaje == "") {
                                    notificacionExito = "";
                                    } else {
                                        %><div> <%=notificacionExito %></div><%
                                    }
                                }
                                    %>
                            <form method="post" action="validar.jsp">
                                <font color="red"></font> 
                                <div class="form-group">
                                    <label for="usuario">Usuario</label>
                                    <input type="text" name="usuario" class="form-control" id="usuario" >
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password"  name="clave" class="form-control" id="password">
                                </div>
                                <button type="submit" class=" btn btn-ttc" >Iniciar Sesión</button>
                                ó <a href="registro.jsp?nuevo=true" class="card-link">Regístrate</a>
                            </form>
                        </div>
                    </div>
            </center>
        </div>

        <script src="librerias/js/jquery-3.5.1.slim.min.js" ></script>
        <script src="librerias/js/popper.min.js"></script>
        <script src="librerias/js/bootstrap.min.js"></script>
    </body>
</html>
