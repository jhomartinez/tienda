<%-- any content can be specified here e.g.: --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String title = "Crea una cuenta con nosotros";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Proyecto Tienda</title>
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
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <img src="image/logoSt1.png" width="90" height="90"/>
            <strong><a class="navbar-brand">SOFTWARE PARA TIENDAS</a></strong><hr>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon">
                </span>
            </button>
        </nav>
        <div id="menu"> 
            <div class="card">
                <div class="card mx-auto" style="width: 50%;">
                    <div class="card-body">
                        <center><h4 class="card-title"><%=title%></h4></center>
                        <span>Registra tu información por favor</span>
                        <div class="container my-3">
                            <jsp:include page="registro/personal.jsp" flush="true"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="librerias/js/jquery-3.5.1.slim.min.js" ></script>
        <script src="librerias/js/popper.min.js"></script>
        <script src="librerias/js/bootstrap.min.js"></script>
    </body>
</html>