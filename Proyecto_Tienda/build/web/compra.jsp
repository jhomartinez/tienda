<%-- 
    Document   : compra
    Created on : 29/05/2020, 4:24:02 p. m.
    Author     : root
--%>
<%@page import="clases.Factura"%>
<%@page import="clases.FacturaDetalle"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona USUARIO = (Persona) sesion.getAttribute("usuario");
        String codigo= "";
        String lista= "";
        if (request.getParameter("id") != null) {
            codigo= request.getParameter("id");
            System.out.println("id-front:" + "id");
            //System.out.println("idEvento:[" + idEvento + "]");
            FacturaDetalle[] productos = FacturaDetalle.getListaEnObjetos("id", null);
            for (int i = 0; i < productos.length; i++) {
                FacturaDetalle compra = productos[i];
                lista += "<tr>";
                lista += "<td>" + compra.getId()+ "</td>";
                lista += "<td>" + compra.getIdFactura()+ "</td>";
                lista += "<td>" + compra.getCodigoProducto()+ "</td>";
                lista += "<td>" + compra.getCantidad()+ "</td>";
                lista += "<td>" + compra.getValorUnitario()+ "</td>";
                lista += "<td>" + compra.getDescuento()+ "</td>";
                lista += "<td>";
                lista += "<a href='principal.jsp?CONTENIDO=comprasFormulario.jsp&accion=Modificar&id=" + compra.getId()+ " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=comprasFormulario.jsp&accion=Eliminar&id=" + compra.getId()+ " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=comprasFormulario.jsp&accion=Detalle&id=" + compra.getId()+ " '>D</a> ";
                lista += "</td>";
                lista += "</tr>";
            }
        }

%>

<h3>LISTA DE COMPRAS</h3>

<input type="checkbox">Fecha entre<select name="fecha" onChange="time()"><option>Seleccione la fecha de inicio y fin</option><%=Factura.getListaEnOptions("fecha")%></select><br>
<input type="checkbox">No. Factura <select name="numeroFactura" onChange="submit()"><option>Numero de Factura</option><%=FacturaDetalle.getListaEnOptions("facturaDetalle")%></select><br>
<input type="checkbox">Proveedor <select name="proveedor" onChange="submit()"><option>Seleccione Proveedor</option><%=FacturaDetalle.getListaEnOptions("facturaDetalle")%></select><br>
<button type="submit" class="btn btn-primary" name="buscar" value="buscar"</button>
<p>
    <table border="1">
    <tr>
        <th>Fecha</th><th>No. de Factura</th><th>Proveedor</th><th>Valor Total</th>
        <th><a href="principal.jsp?CONTENIDO=comprasFormulario.jsp">+</a></th>
    </tr>
    <%=lista%>
</table>

<%
    }
%>