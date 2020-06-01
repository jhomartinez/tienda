<%-- 
    Document   : inicio
    Created on : 2/05/2020, 3:26:40 p. m.
    Author     : root
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="clases.Establecimiento"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        List<Establecimiento> listaEstablecimientos = new ArrayList<>();
        Establecimiento establecimiento = new Establecimiento();
        listaEstablecimientos = establecimiento.getListaEstablecimientos(usuario);
        if (listaEstablecimientos.isEmpty()) {
            response.sendRedirect("principal.jsp?CONTENIDO=registro/establecimiento.jsp");
        }  else {
%>

<p><i> <b>St Software para tiendas</b> es una aplicacion que permite el registro de informacion basica 
        de su establecimiento para llevar un control detallado de su personal, sus productos, ventas, compras pedidos y clientes
        que le permite obtener informes en tiempo real de la situacion actual de su negocio,
        asi como tambien la gestion de indicadores importantes para la correcta toma de desiciones</i><br>
</p>


<form method="post" action="principal.jsp?CONTENIDO=seleccionInicio.jsp">
    <div class="container">
        <div class="row">Por favor seleccione el establecimiento para continuar:</div><br/>
        <div class="row">
            <div class=" col form-group">
                <label for="selectEst"><b>Establecimiento</b></label>
                <select class="custom-select" id="selectEst" name="selectEst">
                    <option selected value="0">Seleccionar una opción</option>
                    <%                    String listaOption = "";
                        for (int i = 0; i < listaEstablecimientos.size(); i++) {
                            Establecimiento e = listaEstablecimientos.get(i);
                            String options = "<option value='" + e.getNit() + "'>" + e.getNombre() + "</option>";
                    %>
                    <%=options%>
                    <%
                        }
                    %>
                </select>

            </div>            


        </div>
        <div><button type="submit" class="btn btn-primary">Seleccionar</button> </div>    
    </div>

</form>


<%
        }
    }
%>