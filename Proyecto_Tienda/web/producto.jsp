<%-- 
    Document   : producto
    Created on : 21/05/2020, 8:10:47 a. m.
    Author     : root
--%>
<%@page import="clases.FacturaDetalle"%>
<%@page import="clases.Producto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="clases.Persona"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp?mensaje=Acceso no autorizado");
    } else {
        Persona usuario = (Persona) sesion.getAttribute("usuario");
        String codigo= "";
        String lista= "";
        if (request.getParameter("codigo") != null) {
            codigo= request.getParameter("codigo");
            System.out.println("codigo-front:" + codigo);
            //System.out.println("idEvento:[" + idEvento + "]");
            Producto[] productos = Producto.getListaEnObjetos(codigo, null);
            for (int i = 0; i < productos.length; i++) {
                Producto producto = productos[i];
                lista += "<tr>";
                lista += "<td>" + producto.getCodigo() + "</td>";
                lista += "<td>" + producto.getNombre() + "</td>";
                lista += "<td>" + producto.getDescripcion() + "</td>";
                int valorUnitarioCompra=Integer.parseInt(producto.getValorUnitarioCompra());
                lista += "<td>" + valorUnitarioCompra + "</td>";
                int valorUnitarioVenta=Integer.parseInt(producto.getValorUnitarioVenta());
                lista += "<td>" + valorUnitarioVenta + "</td>";
                int stock=Integer.parseInt(producto.getStock());
                lista += "<td>" + stock + "</td>";
                int stockMinimo=Integer.parseInt(producto.getStockMinimo());
                lista += "<td>" +  stockMinimo + "</td>";
                int stockMaximo =Integer.parseInt(producto.getStockMaximo());
                lista += "<td>" + stockMaximo + "</td>";
                lista += "<td>" + producto.getCodigoDeBarras() + "</td>";
                lista += "<td>" + producto.getProductosEnDescuento() + "</td>";
                lista += "<td>" + producto.getProductosPerecederos() + "</td>";
                lista += "<td><img src='documentos/fotos/" + producto.getFoto() + "' width='50' heigth='50'/></td>";
                lista += "<td>";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Modificar&codigo=" + producto.getCodigo() + " '><img src='image/icon_edit.png' style='width: 25px; height: 25px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Eliminar&codigo=" + producto.getCodigo() + " '><img src='image/icon_delete.png' style='width: 30px; height: 30px;'/></a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Kardex&codigo=" + producto.getCodigo() + " '>K</a> ";
                lista += "<a href='principal.jsp?CONTENIDO=productosFormulario.jsp&accion=Lote&codigo=" + producto.getCodigo() + " '>L</a> ";
                lista += "</td>";
                lista += "</tr>";
            }
        }

%>

<h3>LISTA DE PRODUCTOS</h3>
<form name="formulario" method="post" action="principal.jsp?CONTENIDO=producto.jsp">
<input type="checkbox">Codigo<select name="codigo" onChange="submit()"><option>Seleccione Codigo del producto</option><%=Producto.getListaEnOptions("codigo")%></select><br>
<input type="checkbox">Codigo de barras <select name="codigoDeBarras" onChange="submit()"><option>Seleccione Codigo de barras</option><%=Producto.getListaEnOptions("codigoDeBarras")%></select><br>
<input type="checkbox">Nombre del producto <select name="nombre" onChange="submit()"><option>Seleccione el nombre del producto</option><%=Producto.getListaEnOptions("nombre")%></select><br>
<input type="checkbox">Productos Perecederos <select name="productosPerecederos" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("productosPerecederos")%></select><br>
<input type="checkbox">Productos con descuento <select name="productosEnDescuento" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("productosEnDescuento")%></select><br>
<%--<input type="checkbox">Estado <select name="estado" onChange="submit()"><option>Seleccione </option><%=Producto.getListaEnOptions("estado")%></select><br> 
<button type="submit" class="btn btn-primary" name="buscar" value="buscar"</button>--%>
</form><br/>
   <table border="1">
    <tr>
        <th>Foto</th><th>Nombre del producto</th><th>Costo Uni.</th><th>Precio Venta</th><th>Stock</th><th>Stock Min.</th><th>Stock Max.</th><th>Codigo de barras</th><th>Con descuento</th><th>Perecederos</th> <%--<th>Estado</th>--%>
        <th><a href="principal.jsp?CONTENIDO=productosFormulario.jsp">+</a></th>
    </tr>
    <%=lista%>
</table>


<%
    }
%>
