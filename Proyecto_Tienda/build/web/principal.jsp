<%-- 
    Document   : principal
    Created on : 19/04/2020, 10:36:06 a. m.
    Author     : root
--%>

<%@page import="clases.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona USUARIO = (Persona) sesion.getAttribute("usuario");
        String menu = "";
        menu += "<li class='nav-link'><a class='nav-link' href='principal.jsp?CONTENIDO=inicio.jsp' title='Pagina de inicio'>Inicio</a></li>";
        switch (USUARIO.getTipo()) {
            case "A":
                menu += "<li class='nav-link dropdown'>"
                        + "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                        + "Establecimiento"
                        + "</a>"
                        + "<div class='dropdown-menu' aria-labelledby='navbarDropdown'>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=registro/establecimiento.jsp'>Datos del Establecimiento</a>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=horario.jsp'>Horario De Atencion</a>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=diaNoLaboral.jsp'>Dias No Laborales</a>"
                        + "</div>"
                        + "</li> ";
                /* menu += "<li class='nav-link dropdown'>"
                        + "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                        + "Usuarios"
                        + "</a>"
                        + "<div class='dropdown-menu' aria-labelledby='navbarDropdown'>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=personal.jsp?tipo=A' title='Datos Personal'>Administrador</a>"
                        + "<a class='dropdown-item'href='principal.jsp?CONTENIDO=personal.jsp?tipo=V' title='Datos Personal'>Vendedor</a>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=personal.jsp?tipo=C' title='Datos Personal'>Cliente</a>"
                        + "</div>"
                        + "</li> ";*/
                menu += "<li class='nav-link'><a class='nav-link' href='principal.jsp?CONTENIDO=listaPersonal.jsp' title='Datos Personal'>Personal</a></li>";
                menu += "<li class='nav-link'><a class='nav-link' href='principal.jsp?CONTENIDO=producto.jsp' title='Productol'>Productos</a></li>";
                menu += "<li class='nav-link'><a class='nav-link' href='principal.jsp?CONTENIDO=compra.jsp' title='Datos Personal'>Compras</a></li>";
                menu += "<li class='nav-link'><a class='nav-link' href='#' title='Datos Personal'>Ventas</a></li>";
                menu += "<li class='nav-link'><a class='nav-link' href='#' title='Datos Personal'>Pedidos</a></li>";
                menu += "<li class='nav-link dropdown'>"
                        + "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                        + "Otros"
                        + "</a>"
                        + "<div class='dropdown-menu' aria-labelledby='navbarDropdown'>"
                        + "<a class='dropdown-item' href='#'>Reportes</a>"
                        + "<a class='dropdown-item' href='#'>Indicadores</a>"
                        + "<div class='dropdown-divider'></div>"
                        + "<a class='dropdown-item' href='#'>Entrega de caja</a>"
                        + "<a class='dropdown-item' href='#'>Toma de inventario</a>"
                        + "</div>"
                        + "</li> ";
                break;
            case "V":
                
                menu += "<li class='nav-link dropdown'>"
                        + "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                        + "Usuarios"
                        + "</a>"
                        + "<div class='dropdown-menu' aria-labelledby='navbarDropdown'>"
                        + "<a class='dropdown-item'href='principal.jsp?CONTENIDO=personal.jsp' title='Datos Personal?tipo=V'>Vendedor</a>"
                        + "<a class='dropdown-item' href='principal.jsp?CONTENIDO=personal.jsp' title='Datos Personal?tipo=C'>Cliente</a>"
                        + "</div>"
                        + "</li> ";
                menu += "<li class='nav-link'><a href='href='principal.jsp?CONTENIDO=ventas.jsp''>Ventas</a></li>";//menu=menu+"";

                break;
            case "C":
                menu += "<li class='nav-link'><a href='principal.jsp?CONTENIDO=personal.jsp?tipo=C' title='Datos Personal' title='Gestionar pedidos'>Cliente</a></li>";
                menu += "<li class='nav-link'><a href='a' title='Gestionar pedidos'>Pedidos</a></li>";
                break;
        }
        menu += "<li class='nav-link'><a class='nav-link' href='index.jsp?accion=cerrarSesion' title='salir del programa'>Salir</a></li>";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Tienda Mercapasto</title>
        <!--        <link rel="stylesheet" href="EstiloPrincipal.css"/>-->
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="librerias/css/bootstrap.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" ><img src="image/logoSt1.png" width="60" height="60"/></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">

                    <%= menu%>


                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </ul>                              
            </div>
        </nav>

        <div id="banner">
            <center><h2><b><i>TIENDA MERCAPASTO</i></b></h2></center>
        </div>
        <div class="container-fluid">
            <div class="row">

                <div class="col-sm-11">
                    <div id="contenido" class="bg-secondary"><jsp:include page='<%=request.getParameter("CONTENIDO")%>' flush="true" /></div>
                </div>
                <div class="col-sm-1" >
                    <div id="panel"class="bg-secondary">Panel</div>
                </div>
            </div>
        </div>
        <script src="librerias/js/jquery-3.5.1.slim.min.js"></script>
        <script src="librerias/js/popper.min.js"></script>
        <script src="librerias/js/bootstrap.min.js"></script>
        <script src="librerias/js/eventos.js"></script>
    </body>
</html>

<%
    }
%>